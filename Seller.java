import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Seller{
    String sellerName;
    HashMap<String,Product> products=new HashMap<>();
    List<String> transactionHistory=new ArrayList<>();

    Seller(String sellerName){
        this.sellerName=sellerName;
    }

    public void addProduct(Product p){
        products.put(p.product_name,p);
    }

    public void getProductByName(String productName){
        if(products.containsKey(productName)){
            Product p=products.get(productName);
            System.out.println("PRODUCT NAME : "+p.product_name);
            System.out.println("PRODUCT PRICE : "+p.price);
            System.out.println("PRODUCT NAME : "+p.quantity);
        }else{
            System.out.println("PRODUCT DOES NOT EXIST!");
        }
    }

    public HashMap<String,Product> getProducts(){
        return products;
    }

    public void addTransaction(String customerName,Product p,int quantity){
        transactionHistory.add(customerName+" "+p.product_name+" "+p.price+" "+quantity);
    }

    public void displayHistory(){
        int cnt=0;
        for(String s:transactionHistory){
            System.out.println(++cnt+" "+s);
        }
    }
    

}