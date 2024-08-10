import java.util.Scanner;

public class AmazonBeta {
    
    public static void main(String args[]){
        Admin adminObj = new Admin();
        Scanner sc = new Scanner(System.in);
        boolean userFlag = true;
        while(userFlag){
            System.out.println("ENTER YOUR IDENTITY: \n1.ADMIN \n2.SELLER \n3.CUSTOMER ");
            int choosen = sc.nextInt();
            sc.nextLine();
            if(choosen == 1){
                System.out.println("ENTER ADMIN PASSWORD: ");
                String pass = sc.nextLine();
                if(adminObj.passCheck(pass)){
                    boolean adminFlag = true;
                    while (adminFlag){
                        System.out.println("\n1.PENDING REQUESTS \n2.APPROVE REQUEST \n3.VIEW INVENTORY \n4.REMOVE SELLER \n5.REMOVE PRODUCT");
                        int adminChoosen = sc.nextInt();
                        sc.nextLine();
                        if(adminChoosen == 1){
                            adminObj.displayRequest();
                        }
                        else if(adminChoosen ==2){
                            System.out.println("ENTER THE SELLER NAME FOR APPROVAL: ");
                            String as = sc.nextLine();
                            System.out.println("ENTER THE PRODUCT NAME FOR APPROVAL: ");
                            String ap = sc.nextLine();
                            Seller seller = adminObj.findSeller(as,true);
                            
                            if(seller != null){
                                Product prd = adminObj.prdFinder(seller,ap,true);
                                if(prd!=null){
                                    adminObj.addInventory(seller, prd);
                                }
                                else System.out.println("PRODUCT NOT FOUND IN THE REQUESTS!");
                                
                            }
                            else System.out.println("SELLER NOT FOUND FOR APPROVAL!");
                        }
                        else if(adminChoosen == 3){
                            adminObj.displayInventory();
                        }
                        else if(adminChoosen==4){
                            System.out.println("ENTER SELLERNAME: ");
                            String sname = sc.nextLine();
                            Seller seller = adminObj.findSeller(sname,false);
                            if(seller != null){
                                adminObj.removeSeller(seller);
                            }
                            else System.out.println("SELLER NOT FOUND!");
                        }
                        else if(adminChoosen == 5){
                            System.out.println("ENTER SELLER NAME: ");
                            String sname = sc.nextLine();
                            System.out.println("ENTER PRODUCT ID: ");
                            int  id = sc.nextInt();
                            sc.nextLine();
                            Seller seller = adminObj.findSeller(sname,false);
                            if(seller != null){
                                adminObj.removeProduct(seller, id);
                            }
                            else System.out.println("SELLER NOT FOUND!");
                            
                        }
                        System.out.println("DO YOU WANT TO CONTINUE AS ADMIN (Y/N)? ");
                        String conti = sc.nextLine();
                        adminFlag = conti.toLowerCase().equals("y");
                    }
                    System.out.println("LOGGED OUT SUCCESSFULLY FROM ADMIN!");
                }
                else System.out.println("WRONG ADMIN PASSWORD!");
            }
            else if(choosen ==2){
                System.out.println("ENTER SELLER ID: ");
                String sellername = sc.nextLine();
                Seller thisSeller = adminObj.findSeller(sellername,false);
                if(thisSeller == null){
                    thisSeller = new Seller(sellername);
                    adminObj.addSeller(sellername,thisSeller);
                    System.out.println("HURRAY!,NEW ACCOUNT FOR YOU HAS BEEN CREATED.\nGIVE REQUEST TO ADD PRODUCTS!");
                }
                else System.out.println("WELCOME BACK "+sellername+"!");
                boolean sellerFlag = true;
                while(sellerFlag){
                    System.out.println("\n1.MY PRODUCTS \n2.ADD PRODUCT \n3.TRANSACTIONS HISTORY");
                    int sellerChoosen = sc.nextInt();
                    sc.nextLine();
                    if(sellerChoosen == 1){
                        adminObj.displayRequest(thisSeller);
                    }
                    else if(sellerChoosen ==2){
                        System.out.println("ENTER NAME OF THE PRODUCT: ");
                        String prod = sc.nextLine();
                        System.out.println("ENTER QUANTITY: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        System.out.println("ENTER PRICE: ");
                        int price = sc.nextInt();
                        sc.nextLine();
                        adminObj.request(thisSeller,new Product(prod,thisSeller.sellerName,price,quantity));
                    }
                    else if(sellerChoosen ==3){
                        thisSeller.displayHistory();
                    }
                    System.out.println("DO YOU WANT TO CONTINUE AS SELLER (Y/N)? ");
                    String conti = sc.nextLine();
                    sellerFlag = conti.toLowerCase().equals("y");
                }
                System.out.println("LOGGED OUT SUCCESSFULLY FROM SELLER!");
            }
            else if(choosen ==3){
                System.out.println("ENTER YOUR NAME : ");
                String cusName = sc.nextLine();
                Customer thisCus = adminObj.findCustomer(cusName);
                if(thisCus == null){
                    thisCus = new Customer(cusName);
                    adminObj.addCustomer(cusName, thisCus);
                    System.out.println("HURRAY!,NEW ACCOUNT FOR YOU HAS BEEN CREATED.\n-> SHOP FROM THE TOPPEST!");
                }
                else System.out.println("WELCOME BACK "+cusName+"!");
                boolean cusFlag = true;
                while(cusFlag){
                    System.out.println("\n1.PRODUCTS \n2.MY CART \n3.CLEAR CART \n4.REMOVE FROM CART \n5.PLACE ORDER \n6.ORDER HISTORY");
                    int cusChoosen = sc.nextInt();
                    sc.nextLine();
                    if(cusChoosen ==1){
                        adminObj.displayInventory();
                        System.out.println("PRESS 0 -> SHOP \n1 -> EXIT");
                        int n= sc.nextInt();
                        sc.nextLine();
                        while(n==0){
                            System.out.println("ENTER SELLER NAME OF THE PRODUCT: ");
                            String s = sc.nextLine();
                            System.out.println("ENTER PRODUCT NAME: ");
                            String prd = sc.nextLine();
                            Product p = adminObj.prdFinder(adminObj.sellers.get(s),prd,false);
                            if(p!=null){
                                thisCus.addToCart(p);
                            }
                            else System.out.println("THE PRODUCT THAT YOU HAVE ENTERED IS INCORRECT!");
                            
                            System.out.println("PRESS 0 -> SHOP \n1 -> EXIT");
                            n= sc.nextInt();
                            sc.nextLine();
                        }
                    }
                    else if(cusChoosen == 2){
                        thisCus.viewCart();
                    }
                    else if(cusChoosen == 3){
                        thisCus.clearCart();
                    }
                    else if(cusChoosen == 4){
                        System.out.println("ENTER YOUR PRODUCT TO BE REMOVED : ");
                        String remPrd = sc.nextLine();
                        thisCus.removeFromCart(remPrd);
                    }
                    else if(cusChoosen ==5){
                        System.out.println("ARE YOU SURE TO PLACE ORDER (Y/N) ?");
                        String sure = sc.nextLine();
                        if(sure.toLowerCase().equals("y")){
                            int price = thisCus.getCartPrice();
                            System.out.println("ONE STEP BEFORE ORDER PLACEMENT! \n PAYABLE AMOUNT : INR."+price+"/-");
                            System.out.println("ENTER AMOUNT TO BE PAID :");
                            int amount = sc.nextInt();
                            sc.nextLine();
                            if(amount ==price){
                                adminObj.order(cusName,thisCus.placeOrder());
                            }
                            else System.out.println("INVALID AMOUNT ENTERED!");
                        }
                    }
                    if(cusChoosen ==6){
                        thisCus.displayHistory();
                    }
                    System.out.println("DO YOU WANT TO CONTINUE AS CUSTOMER (Y/N)? ");
                    String conti = sc.nextLine();
                    cusFlag = conti.toLowerCase().equals("y");
                }
                System.out.println("LOGGED OUT SUCCESSFULLY!");
            }
            
            System.out.println("DO YOU WANT TO CONTINUE (Y/N)? ");
            String conti = sc.nextLine();
            userFlag = conti.toLowerCase().equals("y");
        }    
        sc.close();
    }
}
