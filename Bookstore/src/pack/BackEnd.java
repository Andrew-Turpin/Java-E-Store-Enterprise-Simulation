package pack;
//This class will handle the back end operation such as reading the data in,
//finding the given book in the data, processing the order, discount, subtotal, invoice, etc.
//most computation will be done ehre for the projects back end that the user wont see
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BackEnd {
	//we have our book properties that we can make an array list out of and just parse the invertory text then send it to the respective field
	
	//array list decleration of "books" that will be used by our reader
		private ArrayList<Bookproperties> booklist;
		
		
		DecimalFormat df = new DecimalFormat("#.##");

		
		private final String INP="inventory.txt";
		
		
		//our first step for the backend is to read in our data set of books
	
	//this method will be the inventory reader using file/buffer reader
	private void parser() {
		//instantiate out arraylist of books
		booklist=new ArrayList<Bookproperties>();
		//setting variable for the input file for ease 
		//String inp="inventory.txt";
		//use buffered reader which requires a file reader aswell
		BufferedReader buffer=null;
		FileReader filer=null;
		try {
			//file reader needed a try catch block to not throw a compile error
			filer = new FileReader(INP);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			//with the filereader stream set up we can start the buffered reader
			buffer =new BufferedReader(filer);
			//temp string to check when we reach end of document
			String temp;
			while((temp=buffer.readLine())!=null) {
				//create a temp book index with 3 fields, book id, name, and price to parse through
				String[]tempbook=new String[3];
				Bookproperties currbook = new Bookproperties();
				//split data by commas
				tempbook=temp.split(",",3);
				//now send the data to the respective property field(parse as int or float where nesecary
				currbook.setID(Integer.valueOf(tempbook[0]));
				currbook.setTitle(tempbook[1]);
				currbook.setCost(Float.valueOf(tempbook[2]));
				//append this book to our list
				booklist.add(currbook);

				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//we need to "start" the program to read the file, so we need to initialize our Invoice bill
	private Bill bill;
	boolean flag=false;
	public BackEnd() {
		this.bill=new Bill();
		//I only want to read the inventory once, so i'll use a flag starting at false, to read the file, then set it to true so it doesn't read again
		if(!flag) {//if the flag is false, read the file
			parser();//call file reading method
			flag=true;//set flag true
		}
	}
	
	//small search loop to find the given book
	public Bookproperties Search(int ID) {
		
		//for loop, for this book, go through the arraylist of book ids, if they are equal return it
		for(Bookproperties temp:booklist) {
			if(temp.getID()==ID) {
				return temp;
			}
		}
		return new Bookproperties();
	}
	
	
	
	//simple method to deterimine the level of discount based on num of certain book ordered with if else statements
	//if x books, return y discount, else if more return more discount etc
	//the return needs to be a float for later calculations
	private float percentOff(Bookproperties book,int amount) {
	
		
		//our return type will be the float value of the subtotal with the percentage calculated
		float OG=book.getCost()*(float)amount;
		float price=OG;
		if(amount>=15) {
			OG=OG * 0.2f;
			price=price-OG;
		}
		else if(amount>=10&&amount<=14) {
			OG=OG*0.15f;
			price=price-OG;
		}
		else if(amount>=5&&amount<=9) {
			OG=OG*0.10f;
			price=price-OG;
		}
		else if(amount>=1&&amount<=4) {
		}
		else
			return 0f;
		
		BigDecimal bd=new BigDecimal(price).setScale(2,RoundingMode.HALF_UP);
		return bd.floatValue(); 
	}
	
	//another if else statement to return the numerical value of the percent off just determined by the book amount for the view order box
	//this can just return a simple int as we just need to print "15" if it's 15 percent off
	private int viewOrderPercent(int amount) {
		if(amount>=15) {
			
			return 20;
		}
		else if(amount>=10&&amount<=14) {
			return 15;
		}
		else if(amount>=5&&amount<=9) {
			return 10;
		}
		else if(amount>=1&&amount<=4) {
			return 0;
		}
		else
			return 0;
	}
	
	
	//method to display the book info in the gui panel
	public void Bookinfogui(Bookproperties booklist, int amount) {
		//this is one long setter to set the text output for the text field in the correct formatting like in the project documentation, but we need to call several different methods to get the result, and sometimes just append
		//them as a string in the case of numbers
		booklist.setBookInfo(String.valueOf(booklist.getID() + " " + booklist.getTitle() + " $" + String.valueOf(booklist.getCost()) + " " + String.valueOf(amount)+" "+String.valueOf(viewOrderPercent(amount))+"%  $"+String.valueOf(percentOff(booklist,amount))));
	}
	//similar to the above method we need another method to display the cost in the gui window panel
	public float showCost(Bookproperties booklist, int amount) {
		//this is calculated with the total cost and accounting for the discount
		return percentOff(booklist,amount) + bill.getCurrentTotal();
	}
	
	//Method to detail what gets displayed when the user hits vieworder button
	public String View() {
		//I'm going to create a blank string to start out with, and as each order is rolled in it will just append it to the string for display
		String appendTo=" ";
		//item number displayer
		int itemnum=1;
		//now make a simple for look starting with each processed item till the end of list
		for(ShoppingCart cart:bill.getCart()) {
			//apend string with the number of the item, and its info
			appendTo=appendTo+String.valueOf(itemnum)+ " "+cart.getBook().getBookInfo();
			//after add a new line for the next book
			appendTo=appendTo+"\n";
			//increase the item number by one since we're going to the next listed item
			itemnum=itemnum+1;
			//end loop
		}
		//return the string
		return appendTo;
	}
	
	//method to throw the order into the invoice statement (for the process item button)
	public void ProcessButton(int amount,int CartAmount,Bookproperties book) {
		//create the instance of the cart for setting
		ShoppingCart cart=new ShoppingCart();
		//set the current book, amount of said book, price, and discount into the array list
		cart.setBook(book);
		cart.setNumOfBooks(amount);
		cart.setPercentOff(Float.valueOf(viewOrderPercent(amount)));

		cart.setTotalPrice(percentOff(book,amount));
		//we can now append the bill with our processed book
		this.bill.addToCart(cart);
		
		//with the book and amount of said book we can  calculate the current running total, so call that method
		this.bill.setCurrentTotal(percentOff(book,amount));
		//we can also calculate the overall total with the tax rate
		this.bill.setEndTotal((this.bill.getCurrentTotal()*0.06f)+this.bill.getCurrentTotal());
		//add total cart item amount to the order amount in the bill (NumOfItems-Bill class)
				this.bill.setNumOfItems(CartAmount);
	}
	
	
	//method for the finish order button to duisplay the proper information according to the assignment guidelines
	public String Finishbuttondisplay() {
		String appendTo=" ";
		 appendTo=appendTo+"Date: ";
		//we need to set the date before it gets printed
		 this.bill.setStamp();
		 //print date, num of items line, and fields line
		 appendTo+=this.bill.getTimeofOrder()+"\n"+"\n"+" Number of line  Items: "+this.bill.getNumOfItems()+"\n"+"\n"+" Item#/ID/Title/Price/Qty/Disc%/Subtotal:"+"\n"+"\n";
		 //print subtotal, tax rate, and tax amount lines
		 float tax=Float.valueOf((float) (this.bill.getCurrentTotal()*0.06));
			df.format(tax);
			float roundedtax= (float) ((float)Math.round(tax*100.0)/100.0);
		 appendTo+=View()+"\n"+"\n"+"Order Subtotal: "+this.bill.getCurrentTotal()+"\n\n"+"Tax-Rate: 6%"+"\n"+"Tax Amount: "+roundedtax;
		 //print order total and thank you message
		 float finaltotal=Float.valueOf((float)(this.bill.getCurrentTotal()+(this.bill.getCurrentTotal()*0.06)));
		 float roundedfinaltotal= (float) ((float)Math.round(finaltotal*100.0)/100.0);
		 appendTo+="\n\nOrder Total: "+ roundedfinaltotal+"\nThanks for shopping at Ye Olde Book Shoppe!";
		 
		 
		
		
		
		return appendTo;
	}
	
	
	//And the final back end action is to write to the output file transactions.txt
	//using a file write and buffer writer method I pulled from a old CS2 assignment and help from google
	public void Output() {
		
		FileWriter writer=null;
		String outfile="transactions.txt";
		try {
			writer=new FileWriter(outfile,true);
		} catch (IOException e) {
			System.out.print("Output file print error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//use a for loop much like the reader, but this time to print out the specified info in the project instructions
		//from index 0 to the end of the cart on the bill invoice print it's info
		for(ShoppingCart cart:this.bill.getCart()) {
			
			try {
				//one long write line to write the order time stamp, the book id, title, price, num of saif book, percentage off if any, total price, and time of order for said book
				writer.write(this.bill.getOrderTimeStamp()+","+cart.getBook().getID()+","+cart.getBook().getTitle()+","+cart.getBook().getCost()+","+cart.getNumOfBooks()+","+cart.getPercentOff()+", "+cart.getTotalPrice()+","+this.bill.getTimeofOrder());
				writer.write("\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.print("printing error");
				e.printStackTrace();
			}
		}
		
		//close the file and buffer writers
		
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.print(" error closing file writer");

			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	

}
