package pack;
//All properties of the book object are defined in this class

import java.text.DecimalFormat;

public class Bookproperties {
	//data from the input sheet parced into these catagories
	private int ID;
	private String Title;
	private float Cost;
	
//create a productinfo string for display back on the GUI
	private String BookInfo;
	DecimalFormat df = new DecimalFormat("#.##");
	
	//with all our fields we need to make a getters/setters for the book object

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public float getCost() {
		
		return Cost;
	}
	public void setCost(float cost) {
		
		Cost = cost;
		df.format(Cost);
	}
	public String getBookInfo() {
		return BookInfo;
	}
	public void setBookInfo(String bookInfo) {
		BookInfo = bookInfo;
	} 
	
	//we also need to make a constructor (good practice)
	public Bookproperties() {
		
	}
	
}
