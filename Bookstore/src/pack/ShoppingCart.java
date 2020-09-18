package pack;
//this class creates the structure for the  shopping cart of the user, 
//sets up the quantity and total price fields, and sets up the discount that was described in the project documentation 

public class ShoppingCart {
	//number of books wanted
private int numOfBooks;
//discount percentage variable

private float percentOff;
//total price holder
private float totalPrice;
//given book in shopping cart
private Bookproperties Book;

//auto generated getters/setters for above variables
public int getNumOfBooks() {
	return numOfBooks;
}
public void setNumOfBooks(int numOfBooks) {
	this.numOfBooks = numOfBooks;
}
public float getPercentOff() {
	return percentOff;
}
public void setPercentOff(float percentOff) {
	this.percentOff = percentOff;
}
public float getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(float totalPrice) {
	this.totalPrice = totalPrice;
}
public Bookproperties getBook() {
	return this.Book;
}
public void setBook(Bookproperties book) {
	Book = book;
}

}
