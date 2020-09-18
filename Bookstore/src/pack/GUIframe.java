/* Name: Andrew Turpin
 Course: CNT 4714 – Summer 2020
 Assignment title: Project 1 – Event-driven Enterprise Simulation
 Date: Sunday May 31, 2020
*/
package pack;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUIframe extends JFrame {

	private static JPanel contentPane;
	private static JTextField itemsinorderfield;
	private static JTextField IDfield;
	private static JTextField Quantityfield;
	private static int CON;
	private static Bookproperties book;
	private static BackEnd backend;
	private static JTextField Infofield;
	private static JTextField Subtotalfield;
	public static JButton processb;
	public static JButton confirmb;
	public static JLabel lblNewLabel_1;
	public static JLabel lblNewLabel_2;
	public static JLabel lblNewLabel_3;
	public static JLabel lblNewLabel_4;


	private static JPanel confirmpanel;
	private static JPanel viewpanel;
	private static JPanel finishpanel;
	public static JPanel notfoundpanel;
	private static GUIframe frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		//new GUIframe().start();
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				CON=1;
				try {
					frame = new GUIframe();
					
					frame.start();
					frame.setVisible(true);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIframe() {
		//window builder auto generated code
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Ye Olde Book Store");
		
		JLabel lblNewLabel = new JLabel("Enter number of items in this order:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(20, 29, 218, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Book ID for item #"+String.valueOf(CON)+":");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(20, 64, 218, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter quantity for item #"+String.valueOf(CON)+":");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(20, 99, 218, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Item #"+String.valueOf(CON)+" info:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(20, 140, 218, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Order subtotal for"+String.valueOf(CON)+"item(s):");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(20, 175, 218, 24);
		contentPane.add(lblNewLabel_4);
		
		itemsinorderfield = new JTextField();
		itemsinorderfield.setBounds(277, 31, 363, 20);
		contentPane.add(itemsinorderfield);
		itemsinorderfield.setColumns(10);
		
		IDfield = new JTextField();
		//ID text box for input
		IDfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			//when the key id is typed
			public void keyTyped(KeyEvent e) {
				//if the typed string isn't empty
				if((e.getKeyCode()==KeyEvent.VK_ENTER)&&!IDfield.getText().isEmpty()) {
					//search for that id, if it's the ID in the inventory sheet display it
					book=backend.Search(Integer.valueOf(IDfield.getText()));
							if(!Quantityfield.getText().isEmpty())
								backend.Bookinfogui(book, Integer.valueOf(Quantityfield.getText()));
							else
								//else show null
								backend.Bookinfogui(book, 1);
							//generate the text for the text field in the GUI
							generator(book);
					
				}
			}
		});
		IDfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
			@Override
			public void focusLost(FocusEvent a) {
				//once the user clicks out of that id box check the string
				if(!IDfield.getText().isEmpty()) {
					//search that said string compared to the book list inventory text
					book=backend.Search(Integer.valueOf(IDfield.getText()));
					//as long as it's not empty, call the Bookgui method to format the output
					if(!Quantityfield.getText().isEmpty())
						backend.Bookinfogui(book, Integer.valueOf(Quantityfield.getText()));
					else
						//else call it to output null
						backend.Bookinfogui(book, 1);
					//call the text generator method to show in the gui output field
					generator(book);
					
				}
			}
		});
		IDfield.setBounds(277, 66, 363, 20);
		contentPane.add(IDfield);
		IDfield.setColumns(10);
		
		Quantityfield = new JTextField();
		Quantityfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			//on the release of key, make sure the value is an integer (Had to google how to do this) search for that book, then set the inputted integer to it's amount value
			public void keyReleased(KeyEvent e) {
				boolean charchecker=false;
				if(Character.isDigit(e.getKeyChar())) {
					charchecker=true;
				}
				//if the flag set is tru then search for the book and throw it into the UI
				if (charchecker==true) {
					book=backend.Search(Integer.valueOf(IDfield.getText()));
					backend.Bookinfogui(book, Integer.valueOf(Quantityfield.getText()));
					generator(book);

				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		Quantityfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
			@Override
			//for the quantity field
			public void focusLost(FocusEvent e) {
				//if both the id and quantity field are not empty call the find book and set info methods,
					//then auto generate the text for the field in the GUI
				if(!IDfield.getText().isEmpty()&&Quantityfield.getText().isEmpty()) {
					book=backend.Search(Integer.valueOf(IDfield.getText()));
					backend.Bookinfogui(book, Integer.valueOf(Quantityfield.getText()));
					generator(book);

				}
			}
		});
		Quantityfield.setBounds(277, 101, 363, 20);
		contentPane.add(Quantityfield);
		Quantityfield.setColumns(10);
		
		JButton processb = new JButton("Process Item #"+String.valueOf(CON)+":");
		
		processb.setBounds(10, 224, 173, 23);
		contentPane.add(processb);
		
		processb.setEnabled(false);
		
		//confirm button action
		JButton confirmb = new JButton("Confirm Item #"+String.valueOf(CON)+":");
		confirmb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//show pane showing it was accepted
				
				JOptionPane.showMessageDialog(confirmpanel, "Item #"+String.valueOf(CON)+"accepted");
				//disable the appropriate gui fields like in the document
				itemsinorderfield.setEditable(false);
				processb.setEnabled(true);
				confirmb.setEnabled(false);
				
			}
		});
		confirmb.setBounds(193, 224, 173, 23);
		contentPane.add(confirmb);
		
		JButton viewb = new JButton("View Order");
		viewb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call the view order method to display in a jpanel
				JOptionPane.showMessageDialog(viewpanel, backend.View());
			}
		});
		viewb.setBounds(394, 224, 118, 23);
		contentPane.add(viewb);
		
		JButton finishb = new JButton("Finish Order");
		finishb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when you hit finish show the finish panel dialog then output the transaction file
				JOptionPane.showMessageDialog(finishpanel, backend.Finishbuttondisplay());
				backend.Output();
				//startneworder();
			}
		});
		finishb.setBounds(522, 224, 118, 23);
		contentPane.add(finishb);
		
	
		//new order button
		JButton neworderb = new JButton("New Order");
		neworderb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//starts new order frame to pop up
				startneworder();
				//discard the already existing frame that has the finsihed first order
				Diss(frame);
				
			}
		});
		
		
		
		neworderb.setBounds(647, 224, 118, 23);
		contentPane.add(neworderb);
	JFrame killme=this;
	//exit button code
		JButton exitb = new JButton("Exit");
		exitb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when user hits exit close the frame that is currently displayed
				killme.dispose();
			}
		});
		
		//window builder auto generated code
		exitb.setBounds(775, 224, 118, 23);
		contentPane.add(exitb);
		
		Infofield = new JTextField();
		Infofield.setEditable(false);
		Infofield.setBounds(277, 142, 363, 20);
		contentPane.add(Infofield);
		Infofield.setColumns(10);
		
		Subtotalfield = new JTextField();
		Subtotalfield.setEditable(false);
		Subtotalfield.setBounds(277, 177, 363, 20);
		contentPane.add(Subtotalfield);
		Subtotalfield.setColumns(10);
		//end ofwindow builder auto generated code

		
		processb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on process button preform this
				//setting text fields and buttons to the proper book number and disabling it if it shouldn't be used
				backend.ProcessButton(Integer.valueOf(Quantityfield.getText()), Integer.valueOf(itemsinorderfield.getText()), book);
				if(CON<Integer.valueOf(itemsinorderfield.getText())) {
					itemsinorderfield.setEditable(false);
					itemsinorderfield.setEditable(false);

					confirmb.setEnabled(true);
					processb.setEnabled(false);
					
					IDfield.setText("");
					Quantityfield.setText("");
					//increment the number
					CON=CON+1;
					//relabel the text to appropriatley update the info
					Edittextboxes(processb,confirmb,lblNewLabel_1,lblNewLabel_2,lblNewLabel_3,lblNewLabel_4);

				}
				else {
					//set the buttons the be disabled like the project documentation says
					confirmb.setEnabled(false);
					processb.setEnabled(false);
				}
				
				
			}

			

			
		});
		
		
		
		
		
		
	}
	
	//the re edit of the boxes that update what number item they're on
		private static void Edittextboxes(JButton processb,JButton confirmb,JLabel lblNewLabel_1,JLabel lblNewLabel_2,JLabel lblNewLabel_3,JLabel lblNewLabel_4 ) {
			// TODO Auto-generated method stub
			//process button text
			processb.setText("Process Item #"+ Integer.toString(CON));
			//confirm button text

			confirmb.setText("Confirm item #"+Integer.toString(CON));	
			//ID Label text

			lblNewLabel_1.setText("Enter book ID for item #"+Integer.toString(CON) );
			//Quantity Label text

			lblNewLabel_2.setText("Enter Quantity for tiem# "+Integer.toString(CON)+":" );
			//Item number Label text

			lblNewLabel_3.setText("Item#"+Integer.toString(CON)+"info:" );
			//order subtotal  Label text

			lblNewLabel_4.setText("Order Subtotal for "+Integer.toString(CON-1)+"item(s):" );



			
		}
	
	//creation of thread methods
		class makethread extends Thread{
			public void run() {
				backend=new BackEnd();
			}
		}
		//start of thread method
		private void start() {
			new makethread().start();
		}
	
	
	
	//Auto generator for the book name field
	private static void generator(Bookproperties book) {
		//set the text to the books info in the text field
		Infofield.setText(book.getBookInfo());
		//if the quantity text is not empty, set the subtotal to the total added amount for that book
		if(!Quantityfield.getText().isEmpty())
			Subtotalfield.setText(String.valueOf(backend.showCost(book, Integer.valueOf(Quantityfield.getText()))));
		//else just display the cost for one of that book
		else {
			Quantityfield.setText("1");
			Subtotalfield.setText(String.valueOf(backend.showCost(book, 1)));
		}
			
			
			
	}
	private void Diss(JFrame frame) {
		frame.dispose();
	}
	
	private static void startneworder() {
		GUIframe framex = new GUIframe();
		framex.start();
		framex.setVisible(true);
		
		//trash code that didn't work that I was testing around with
		
		
		
		//new GUIframe().start();
		/*CON=1;
		itemsinorderfield.setText("");
		IDfield.setText("");
		Quantityfield.setText("");
		Infofield.setText("");
		Subtotalfield.setText("");
		Edittextboxes(processb,confirmb,lblNewLabel_1,lblNewLabel_2,lblNewLabel_3,lblNewLabel_4);*/
		//itemsinorderfield.setEditable(true);
	//	confirmb.setEnabled(true);
		//confirmb.setEnabled(false);

	}
	
}
