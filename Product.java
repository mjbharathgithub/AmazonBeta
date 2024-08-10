
public class Product {
	public String product_name;
	public int price,quantity;
	public String sellerName;
	
	public Product(String product_name,String seller,int price,int quantity) {
		sellerName = seller;
		this.product_name=product_name;
		this.price=price;
		this.quantity=quantity;
	}
	

	}


