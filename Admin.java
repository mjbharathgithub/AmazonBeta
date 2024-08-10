import java.util.*;
class Admin {
    //Instance variables
    public HashMap<Seller, List<Product>> inventory = new HashMap<>(); // used to remove a individual invventory or seller
    public HashMap<Seller, List<Product>> requests=new HashMap<>(); // used to approve requests!
    public HashMap<String, Customer>customers=new HashMap<>(); 
    public HashMap<String, Seller>sellers=new HashMap<>(); 

    String adminPass = "amazon321";
    public boolean passCheck(String s){
        return s.equals(adminPass);
    }
    public void request(Seller s, Product p){
        // used to place request to the admin (to add it into inventory) by seller....
        List<Product> temp;
        if(requests.containsKey(s)){
            temp=requests.get(s); 
        }
        else{
            temp=new ArrayList<>();
        }
        temp.add(p);
        requests.put(s, temp);
        System.out.println("REQUEST PLACED SUCCESSFULLY !\n");
    }

    public void displayRequest(){
        for(Seller s: requests.keySet()){
            System.out.println("SELLER : "+s.sellerName);
            List<Product> temp = requests.get(s);
            int index = 1;
            for(Product p:temp){
                System.out.println(index+".\tPRODUCT : "+p.product_name+"\tPRICE : "+p.price+"\tQUANTITY : "+p.quantity);
            }
            System.out.println();
        }
        
    }
    public void displayRequest(Seller s){
            if(inventory.containsKey(s)){
                System.out.println("SELLER : "+s.sellerName);
            List<Product> temp = requests.get(s);
            int index = 1;
            for(Product p:temp){
                System.out.println(index+".\tPRODUCT : "+p.product_name+"\tPRICE : "+p.price+"\tQUANTITY : "+p.quantity);
            }
            }
            else System.out.println("YOU HAVE NO APPROVED PRODUCTS!");
    }
    public Seller findSeller(String sell,boolean isRequest){
        if(isRequest){
            for(Seller s:requests.keySet()){
                if(s.sellerName.equals(sell)) return s;
            }
            return null;
        }
        else{
            for(String s:sellers.keySet()){
                if(s.equals(sell)) return sellers.get(s);
            }
            return null;
        }
    }
    public Product prdFinder(Seller sell,String ap,boolean isRequest){
        if(isRequest){
            List<Product>temp = requests.get(sell);
            for(Product p:temp){
                if(p.product_name.equals(ap))return p;
            }
            return null;
        }
        else {
            List<Product>temp = inventory.get(sell);
            for(Product p:temp){
                if(p.product_name.equals(ap))return p;
            }
            return null;
        }
    }
    public Customer findCustomer(String cus){
        if(customers.containsKey(cus))return customers.get(cus);
        return null;
    }
    public void addSeller(String seller,Seller obj){
        sellers.put(seller,obj);
    }
    public void addCustomer(String cus,Customer obj){
        customers.put(cus,obj);
    }
    public void addInventory(Seller s,Product prd){
       List<Product> temp = inventory.getOrDefault(s,new ArrayList<>());
       temp.add(prd);
       inventory.put(s,temp);
       
       temp = requests.get(s);
       if (temp != null) {
           temp.remove(prd);
           if (temp.isEmpty()) {
               requests.remove(s);
           }
       }

       s.addProduct(prd);
       addSeller(s.sellerName,s);
        System.out.println("INVENTORY UPDATED SUCCESSFULLY !\n");
    }
    
    public void displayInventory(){
        for(Seller s : inventory.keySet()){
            List<Product> temp = inventory.get(s);
            System.out.println("SELLER : "+s.sellerName);
            int index = 1;
            for(Product p:temp){
                System.out.println(index+".\tPRODUCT : "+p.product_name+"\tPRICE : "+p.price);
                index++;
            }
            System.out.println();
        }
        
    }
    
    public void removeProduct(Seller s, int product_index){
        if(inventory.containsKey(s)){
            List<Product> temp = inventory.get(s);
            Product p = temp.get(product_index-1);
            temp.remove(p);
            if(temp.isEmpty()){
                inventory.remove(s);
            }
            else inventory.put(s, temp);
            s.products.remove(p.product_name);
            
            System.out.println("PRODUCT : "+p.product_name+" IS REMOVED SUCCESSFULLY !\n");
        }
        else{
            System.out.println("SELLER DOESN'T EXIST !\n");
        }
    }
    
    public void removeSeller(Seller s){
        if(requests.containsKey(s))requests.remove(s);
        inventory.remove(s);
        System.out.println("SELLER "+s.sellerName+" REMOVED SUCCESSFULLY!\n");
    }
    public void order(String cusName,List<Product> arr){
        Customer tmp = customers.get(cusName);
        for(Product tp :arr){
            Seller s = sellers.get(tp.sellerName);
            s.addTransaction(cusName,tp,1);
            tmp.addTransaction(tp,1);
            sellers.put(tp.sellerName,s);
            tmp.removeFromCart(tp.product_name);
        }
        System.out.println("ORDER PLACED SUCCESSFULLY!");
        customers.put(cusName,tmp);

    }
}