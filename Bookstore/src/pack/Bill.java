package pack;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;


//this bill class will handle everything needed for the invoice window and accompanying properties 
//as well as handeling the date output in the fee invoice like the example
public class Bill {
	//date variables needed to produce invoice text
	private String TimeofOrder;
	private String OrderTimeStamp;
	
	
	DecimalFormat df = new DecimalFormat("#.##");

	
	//we need variables for the total amount of books in the order
	private int NumOfItems=0;
	//also variables for the current total and overall total after the order if finished
	private float EndTotal=0;
	private float CurrentTotal=0;
	
	//An array list is the easiest data structure to deal with for the items in the cart for the order as the books are already in an array list
	private ArrayList<ShoppingCart> Cart;
	
	//Bill constructor
	public Bill() {
		//initialize the array list of our cart with the Shopping cart class attributes
		this.Cart=new ArrayList<ShoppingCart>();
	}

	
	//getters and setters for the data feilds
	public String getTimeofOrder() {
		return this.TimeofOrder;
	}


	public String getOrderTimeStamp() {
		return this.OrderTimeStamp;
	}
	
	public int getNumOfItems() {
		return this.NumOfItems;
	}

	public void setNumOfItems(int numOfItems) {
		this.NumOfItems = numOfItems;
	}

	public float getEndTotal() {
		return this.EndTotal;
	}

	public void setEndTotal(float endTotal) {
		

		this.EndTotal = endTotal;

	}

	public float getCurrentTotal() {
		return this.CurrentTotal;
	}

	public void setCurrentTotal(float currentTotal) {
		//add the += to add each books price
		this.CurrentTotal += currentTotal;
		
	}

	public ArrayList<ShoppingCart> getCart() {
		return this.Cart;
	}
//add appendage to order
	public void addToCart(ShoppingCart Cart) {
		this.Cart.add(Cart);
	}
	
	//I had to google how to use a date setting method and this was the easiest solution I found
	public void setStamp() {
		//create our date variable
		Date date=new Date();
		//format our date variable (according to project document)
		
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy KK:mm:ss a 'EDT' ");
		//sdf.setTimeZone(TimeZone.getTimeZone("EDT"));
		
		DateFormat form=sdf;
		//set the current time of order to the date format
		this.TimeofOrder=sdf.format(date);
		
		SimpleDateFormat Ots= new SimpleDateFormat("ddMMyyyyKKmm");
		this.OrderTimeStamp=Ots.format(date);
	}







	
	
	

}
