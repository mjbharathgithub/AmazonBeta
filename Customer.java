import java.util.*;

class Customer{
    String CustomerName = "";
public Customer(String name ){
    CustomerName = name;
}
    List<Product> cart = new ArrayList<>();
    List<String> transactionHistory = new ArrayList<>();
    
    public void addToCart(Product product) {
    	this.cart.add(product);//add each product to cart
    	System.out.println(product.product_name+" IS ADDED TO YOUR CART");
    	
		
	}
    
    public void removeFromCart(String productName) {
        if (this.cart.isEmpty()) {
            System.out.println("CANNOT REMOVE ITEMS FROM AN EMPTY CART");
            return;
        }
    
        for (Product product : this.cart) {
            if (product.product_name.equals(productName)) {
                cart.remove(product);
                System.out.println(productName + " IS REMOVED FROM YOUR CART");
                return;
            }
        }
        System.out.println(productName + " IS NOT FOUND IN YOUR CART");
        
    }
    
    
    public void viewCart() {
        int total=0;
        System.out.println("YOUR CART");
        for(Product product:cart){
            System.out.println("PRODUCT NAME : " + product.product_name +
                                                         "\nPRICE : " + product.price +
                                                         "\nQUANTITY : " + 1);
                                                         total+=product.price;
        }
        System.out.println("YOU HAVE "+cart.size()+" ITEMS IN YOUR CART\nTOTAL : "+total+" INR");                                                 
    }

    public int getCartPrice(){
        int total=0;n
        for(Product product:cart){
            total+=product.price;
        }
        return total;
    }

    public void clearCart() {
    	this.cart.clear();
        System.out.println("YOUR CART IS EMPTY NOW!");
		
	}
    public List<Product> placeOrder() {
    	return this.cart;//return all the product added when orer placed
		}
    
    public void addTransaction(Product p,int quantity){
            transactionHistory.add(p.product_name+" from "+p.sellerName+" with the Price: "+p.price+"("+quantity+")");
        }
    
    public void displayHistory(){
            int cnt=0;
            for(String s:transactionHistory){
                System.out.println(++cnt+" "+s);
            }
        }
}