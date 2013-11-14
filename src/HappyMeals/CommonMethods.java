/*
 * @author: Neha Singh, Piyush Agarwal, Stephen Cowie
 */

package HappyMeals;

import java.awt.Color;
import java.io.File;
import java.io.RandomAccessFile;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CommonMethods {

	Color c = new Color(255, 0, 0);

	JPanel pnlEvent, pnlClient, pnlOrder, pnlInvoice;

	// txt fields for client info
	JTextField txtFN, txtLN, txtStreet, txtZip, txtCity, txtPhone, txtEmail;
	// txt fields for event info
	JTextField txtOther, txtDate, txtTime, txtAttend, txtEventName;
	// txt fields for drink info
	JTextField txtDrink0 = new JTextField("0");
	JTextField txtDrink1 = new JTextField("0");
	JTextField txtDrink2 = new JTextField("0");
	JTextField txtDrink3 = new JTextField("0");
	JTextField txtDrink4 = new JTextField("0");
	JTextField txtDrink5 = new JTextField("0");
	JTextField txtDrink6 = new JTextField("0");
	JTextField txtDrink7 = new JTextField("0");
	JTextField txtDrink8 = new JTextField("0");
	// txt fields for snack info
	JTextField txtSnack0 = new JTextField("0");
	JTextField txtSnack1 = new JTextField("0");
	JTextField txtSnack2 = new JTextField("0");
	JTextField txtSnack3 = new JTextField("0");
	JTextField txtSnack4 = new JTextField("0");
	JTextField txtSnack5 = new JTextField("0");
	JTextField txtSnack6 = new JTextField("0");
	JTextField txtSnack7 = new JTextField("0");
	JTextField txtSnack8 = new JTextField("0");
	// txt fields for entree info
	JTextField txtEntree0 = new JTextField("0");
	JTextField txtEntree1 = new JTextField("0");
	JTextField txtEntree2 = new JTextField("0");
	JTextField txtEntree3 = new JTextField("0");
	JTextField txtEntree4 = new JTextField("0");
	JTextField txtEntree5 = new JTextField("0");
	JTextField txtEntree6 = new JTextField("0");
	JTextField txtEntree7 = new JTextField("0");
	JTextField txtEntree8 = new JTextField("0");
	// txt fields for dessert info
	JTextField txtDessert0 = new JTextField("0");
	JTextField txtDessert1 = new JTextField("0");
	JTextField txtDessert2 = new JTextField("0");
	JTextField txtDessert3 = new JTextField("0");
	JTextField txtDessert4 = new JTextField("0");
	JTextField txtDessert5 = new JTextField("0");
	JTextField txtDessert6 = new JTextField("0");
	JTextField txtDessert7 = new JTextField("0");
	JTextField txtDessert8 = new JTextField("0");
	JTextField txtDessert9 = new JTextField("0");

	JTextArea txtNotes;
	JRadioButton rdbtnOurBanquet, rdbtnOther;
	JCheckBox chkStaff1, chkStaff2, chkStaff3, chkStaff4, chkStaff5, chkStaff6, chkStaff7, chkStaff8;
	ButtonGroup rdbtnGroupVenue;
	JComboBox<Object> cmbStates;
	JTabbedPane tabpnlAdd;
	JTextArea tA;
	// tabs for order info
	JTabbedPane tabpnlAdd2;

	// Hash Maps used for storing event-specific information
	HashMap<String, String> hmClientTab = new HashMap<String, String>(); // HashMap
																			// object
																			// for
																			// storing
																			// Client
																			// data
																			// [Key:
																			// Field
																			// Name
																			// on
																			// Client
																			// Tab,
																			// Value:
																			// Value
																			// assigned
																			// to
																			// the
																			// key]
	HashMap<String, String> hmEventTab = new HashMap<String, String>(); // HashMap
																		// object
																		// for
																		// storing
																		// Client
																		// data
																		// [Key:
																		// Field
																		// Name
																		// on
																		// Event
																		// Tab,
																		// Value:
																		// Value
																		// assigned
																		// to
																		// the
																		// key]
	HashMap<String, String> hmDrinkTab = new HashMap<String, String>(); //
	HashMap<String, String> hmSnackTab = new HashMap<String, String>();
	HashMap<String, String> hmEntreeTab = new HashMap<String, String>();
	HashMap<String, String> hmDessertTab = new HashMap<String, String>();
	HashMap<String, String> hmInvoiceTab = new HashMap<String, String>();

	ArrayList<HashMap<String, String>> arrEventDetails; // will be separate for
														// every event and will
														// store the details of
														// that particular event
	static HashMap<String, ArrayList<HashMap<String, String>>> hmEvents = new HashMap<String, ArrayList<HashMap<String, String>>>(); // HashMap
																																		// object
																																		// for
																																		// storing
																																		// Events
																																		// data
																																		// [Key:Event
																																		// Name,
																																		// Value:
																																		// ArrayList
																																		// Object]

	static ArrayList<String> arrEvents = new ArrayList<String>();// stores the
																	// Event
																	// Names as
																	// populated
	// from XML
	static ArrayList<String> arrEventDates = new ArrayList<String>();// stores
																		// the
																		// Event
																		// Dates
																		// as
	// populated from XML

	// Arrays used for storing menu information
	// headers for columns
	String[] columnHeaders = new String[] { "Item", "Unit", "Unit Cost ($)" };

	// drinks
	// data for 2-d array
	Object[][] drinkArray = new Object[][] {
			{ "Coke", "2-liter", new Double(2.00) },
			{ "Sprite", "2-liter", new Double(2.00) },
			{ "Root Beer", "2-liter", new Double(2.00) },
			{ "Apple Juice", "ea", new Double(0.75) },
			{ "Orange Juice", "ea", new Double(0.75) },
			{ "Punch", "bowl", new Double(5.00) },
			{ "Milk", "ea", new Double(0.75) },
			{ "Choc. Milk", "ea", new Double(0.75) },
			{ "Water", "ea", new Double(1.00) } };

	// snacks
	Object[][] snackArray = new Object[][] {
			{ "Cheese", "ea", new Double(20.00) },
			{ "Vegetables", "ea", new Double(10.00) },
			{ "Meat Balls", "ea", new Double(20.00) },
			{ "Potato Chips", "bowl", new Double(10.00) },
			{ "Pretzels", "bowl", new Double(10.00) },
			{ "Ranch Dip", "bowl", new Double(8.00) },
			{ "Yogurt", "ea", new Double(1.00) },
			{ "Mixed Nuts", "bowl", new Double(0.75) },
			{ "Popcorn", "bowl", new Double(1.00) } };

	// entrees
	Object[][] entreeArray = new Object[][] {
			{ "Hamburgers", "tray", new Double(40.00) },
			{ "Cheeseburgers", "tray", new Double(50.00) },
			{ "Pizza (meat)", "ea", new Double(15.00) },
			{ "Pizza (cheese)", "ea", new Double(12.00) },
			{ "Ravioli", "bowl", new Double(25.00) },
			{ "Sandwiches", "tray", new Double(35.00) },
			{ "Chicken Fingers", "tray", new Double(38.00) },
			{ "Tacos", "tray", new Double(30.00) },
			{ "Mac & Cheese", "bowl", new Double(22.00) } };

	// desserts
	Object[][] dessertArray = new Object[][] {
			{ "Cookies", "tray", new Double(15.00) },
			{ "Cupcakes", "tray", new Double(25.00) },
			{ "Cheesecake", "ea", new Double(20.00) },
			{ "Coconut Cake", "ea", new Double(17.50) },
			{ "Chocolate Cake", "ea", new Double(17.00) },
			{ "Lemon Poundcake", "ea", new Double(15.00) },
			{ "Ice Cream (choc)", "half-gallon", new Double(5.00) },
			{ "Ice Cream (van)", "half-gallon", new Double(5.00) },
			{ "Popcicles", "ea", new Double(0.75) },
			{ "Candy Bars", "tray", new Double(25.00) } };

	// Tables used to display Menu
	JTable drinkTable = new JTable(drinkArray, columnHeaders);
	JTable snackTable = new JTable(snackArray, columnHeaders);
	JTable entreeTable = new JTable(entreeArray, columnHeaders);
	JTable dessertTable = new JTable(dessertArray, columnHeaders);

	// ////CALCULATION VARIABLES and METHODS///////////////////
	final double SALES_TAX = 0.07;
	final double LABOR_RATE = 30;

	// users Order input will be stored in an array for purpose of calcs
	int[] drinkQnty = new int[9];
	int[] snackQnty = new int[9];
	int[] entreeQnty = new int[9];
	int[] dessertQnty = new int[10];

	// subtotal item variables
	double drinkSubtotal = 0;
	double snackSubtotal = 0;
	double entreeSubtotal = 0;
	double dessertSubtotal = 0;
	double totalFoodSubtotal = 0;
	double totalTaxSubtotal = 0;
	double totalLaborSubtotal = 0;
	double totalItemsCost = 0;
	double totalLaborHrs = 0;

	double amountPaid = 0;
	double amountDue = 0;

	JTextField txtAmountPaid;

	// Calculation methods
	void calcDrinkSubtotal() {
		for (int i = 0; i < drinkQnty.length; i++) {
			drinkSubtotal = drinkSubtotal
					+ (drinkQnty[i] * (double) drinkArray[i][2]);
		}
	}

	void calcSnacksSubtotal() {
		for (int i = 0; i < snackQnty.length; i++) {
			snackSubtotal = snackSubtotal
					+ (snackQnty[i] * (double) snackArray[i][2]);
		}
	}

	void calcEntreeSubtotal() {
		for (int i = 0; i < entreeQnty.length; i++) {
			entreeSubtotal = entreeSubtotal
					+ (entreeQnty[i] * (double) entreeArray[i][2]);
		}
	}

	void calcDessertSubtotal() {
		for (int i = 0; i < dessertQnty.length; i++) {
			dessertSubtotal = dessertSubtotal
					+ (dessertQnty[i] * (double) dessertArray[i][2]);
		}
	}

	void calcTotalFoodSubtotal() {
		totalFoodSubtotal = drinkSubtotal + snackSubtotal + entreeSubtotal
				+ dessertSubtotal;
	}

	void calcTotalTaxSubtotal() {
		totalTaxSubtotal = totalFoodSubtotal * SALES_TAX;
	}

	void calcTotalLaborSubtotal() {
		totalLaborSubtotal = totalLaborHrs * LABOR_RATE;
	}

	void calcTotalItemsCost() {
		totalItemsCost = totalFoodSubtotal + totalTaxSubtotal
				+ totalLaborSubtotal;
	}

	// Methods to clear variables
	void clearDrinkSubtotal() {
		drinkSubtotal = 0;
	}

	void clearSnackSubtotal() {
		snackSubtotal = 0;
	}

	void clearEntreeSubtotal() {
		entreeSubtotal = 0;
	}

	void clearDessertSubtotal() {
		dessertSubtotal = 0;
	}

	void clearTotalFoodSubtotal() {
		totalFoodSubtotal = 0;
	}

	void clearTotalTaxSubtotal() {
		totalTaxSubtotal = 0;
	}

	void clearTotalLaborSubtotal() {
		totalLaborSubtotal = 0;
	}

	void clearTotalItemsCost() {
		totalItemsCost = 0;
	}

	void clearAllCosts() {
		clearDrinkSubtotal();
		clearSnackSubtotal();
		clearEntreeSubtotal();
		clearDessertSubtotal();
		clearTotalFoodSubtotal();
		clearTotalTaxSubtotal();
		clearTotalLaborSubtotal();
	}

	// /////////// END CALCULATIONS SECTION ///////////////////

	public JTabbedPane getTabs() {
		tabpnlAdd = new JTabbedPane(JTabbedPane.TOP);
		tabpnlAdd.setBounds(0, 60, 494, 267);
		tabpnlAdd.addTab("Client", makePnlClient());
		tabpnlAdd.addTab("Event", makePnlEvent());
		tabpnlAdd.addTab("Order", makePnlOrder());
		tabpnlAdd.addTab("Invoice", makePnlInvoice());
		return tabpnlAdd;
	}

	public JTabbedPane getTabsUpdate() {
		tabpnlAdd = new JTabbedPane(JTabbedPane.TOP);
		tabpnlAdd.setBounds(0, 60, 494, 267);
		tabpnlAdd.addTab("Client", makePnlClient());
		tabpnlAdd.addTab("Event", makePnlEvent());
		tabpnlAdd.addTab("Order", makePnlOrder());
		tabpnlAdd.addTab("Invoice", makePnlInvoice());
		tabpnlAdd.addTab("Chef Instructions", makeChefInstructions());
		tabpnlAdd.addTab("Assign Staff", makeAssignStaff());
		return tabpnlAdd;
	}

	// tabs for the display of the Menu
	public JTabbedPane getTabsMenu() {
		tabpnlAdd = new JTabbedPane(JTabbedPane.TOP);
		tabpnlAdd.setBounds(0, 60, 494, 267);
		tabpnlAdd.addTab("Drinks", makePnlDrinks());
		tabpnlAdd.addTab("Snacks", makePnlSnacks());
		tabpnlAdd.addTab("Entrees", makePnlEntrees());
		tabpnlAdd.addTab("Desserts", makePnlDesserts());
		return tabpnlAdd;
	}

	// tabs for the display of the Order form
	public JTabbedPane getTabsOrder() {
		tabpnlAdd2 = new JTabbedPane(JTabbedPane.LEFT);
		tabpnlAdd2.setBounds(0, 0, 494, 267);
		tabpnlAdd2.addTab("Drinks", makePnlDrinksOrder());
		tabpnlAdd2.addTab("Snacks", makePnlSnacksOrder());
		tabpnlAdd2.addTab("Entrees", makePnlEntreesOrder());
		tabpnlAdd2.addTab("Desserts", makePnlDessertsOrder());
		return tabpnlAdd2;
	}

	public void goHome(JFrame frame) {
		int reply = JOptionPane
				.showConfirmDialog(
						null,
						"Do you really want to leave this page? \n Note: Any Unsaved changes will be lost",
						"Confirm?", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			frame.setVisible(false);
			MainPage objMain = new MainPage();
			objMain.frame.setVisible(true);
		}
	}

	// calls home page without giving warning
	public void goHome2(JFrame frame) {
		frame.setVisible(false);
		MainPage objMain = new MainPage();
		objMain.frame.setVisible(true);
	}

	private Component makePnlClient() {
		// create a panel object and set its layout to null(absolute)
		pnlClient = new JPanel();
		pnlClient.setLayout(null);

		// FIRST NAME
		JLabel lblFN = new JLabel("First Name");
		lblFN.setHorizontalAlignment(SwingConstants.LEFT);
		lblFN.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFN.setBounds(10, 0, 71, 25);
		pnlClient.add(lblFN);

		txtFN = new JTextField();
		txtFN.setBounds(110, 3, 242, 20);
		txtFN.setColumns(60);
		txtFN.setName("FN");
		pnlClient.add(txtFN);

		// LAST NAME
		JLabel lblLN = new JLabel("Last Name");
		lblLN.setHorizontalAlignment(SwingConstants.LEFT);
		lblLN.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLN.setBounds(10, 25, 71, 25);
		pnlClient.add(lblLN);

		txtLN = new JTextField();
		txtLN.setBounds(110, 28, 242, 20);
		txtLN.setColumns(60);
		txtLN.setName("LN");
		pnlClient.add(txtLN);

		// STREET
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setHorizontalAlignment(SwingConstants.LEFT);
		lblStreet.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStreet.setBounds(10, 50, 71, 25);
		pnlClient.add(lblStreet);

		txtStreet = new JTextField();
		txtStreet.setBounds(110, 53, 242, 20);
		txtStreet.setColumns(60);
		txtStreet.setName("STREET");
		pnlClient.add(txtStreet);

		// CITY
		JLabel lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCity.setBounds(10, 75, 71, 25);
		pnlClient.add(lblCity);

		txtCity = new JTextField();
		txtCity.setBounds(110, 80, 242, 20);
		txtCity.setColumns(60);
		txtCity.setName("CITY");
		pnlClient.add(txtCity);

		// STATE
		JLabel lblState = new JLabel("State");
		lblState.setHorizontalAlignment(SwingConstants.LEFT);
		lblState.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblState.setBounds(10, 100, 71, 25);
		pnlClient.add(lblState);

		// Create an ArrayList containing the states
		ArrayList<String> arrStates = new ArrayList<String>();
		arrStates.add("Alabama");
		arrStates.add("Alaska");
		arrStates.add("Arkansas");
		arrStates.add("Arizona");
		arrStates.add("California");
		arrStates.add("Colorado");
		arrStates.add("Connecticut");
		arrStates.add("Delaware");
		arrStates.add("Florida");
		arrStates.add("Georgia");
		arrStates.add("Hawaii");
		arrStates.add("Idaho");
		arrStates.add("Illinois");
		arrStates.add("Indiana");
		arrStates.add("Iowa");
		arrStates.add("Kansas");
		arrStates.add("Kentucky");
		arrStates.add("Louisiana");
		arrStates.add("Maine");
		arrStates.add("Maryland");
		arrStates.add("Massachusetts");
		arrStates.add("Michegan");
		arrStates.add("Minnesota");
		arrStates.add("Mississippi");
		arrStates.add("Missouri");
		arrStates.add("Montana");
		arrStates.add("Nebraska");
		arrStates.add("Nevada");
		arrStates.add("New Hampshire");
		arrStates.add("New Jersey");
		arrStates.add("New Mexico");
		arrStates.add("New York");
		arrStates.add("North Carolina");
		arrStates.add("North Dakota");
		arrStates.add("Ohio");
		arrStates.add("Oklahoma");
		arrStates.add("Oregon");
		arrStates.add("Pennsylvania");
		arrStates.add("Rhode Island");
		arrStates.add("South Carolina");
		arrStates.add("South Dakota");
		arrStates.add("Tennessee");
		arrStates.add("Texas");
		arrStates.add("Utah");
		arrStates.add("Vermont");
		arrStates.add("Virginia");
		arrStates.add("Washington");
		arrStates.add("West Virginia");
		arrStates.add("Wisconsin");
		arrStates.add("Wyoming");

		Collections.sort(arrStates); // sort the ArrayList

		cmbStates = new JComboBox<Object>(arrStates.toArray()); // Pass the
																// ArrayList
																// object as a
																// parameter to
																// Combo Box to
																// populate it
		cmbStates.setMaximumRowCount(10);
		cmbStates.insertItemAt("Select...", 0);
		cmbStates.setSelectedIndex(0);
		cmbStates.setBounds(110, 103, 242, 20);
		cmbStates.setName("STATE");
		pnlClient.add(cmbStates);

		// ZIP
		JLabel lblZip = new JLabel("Zip");
		lblZip.setHorizontalAlignment(SwingConstants.LEFT);
		lblZip.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblZip.setBounds(10, 125, 71, 25);
		pnlClient.add(lblZip);

		txtZip = new JTextField();
		txtZip.setBounds(110, 128, 242, 20);
		txtZip.setColumns(60);
		txtZip.setName("ZIP");
		pnlClient.add(txtZip);

		// PHONE
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhone.setBounds(10, 150, 71, 25);
		pnlClient.add(lblPhone);

		JLabel lblPhone2 = new JLabel("XXX-XXXXXXX");
		lblPhone2.setBounds(10, 165, 100, 25);
		pnlClient.add(lblPhone2);

		txtPhone = new JTextField();
		txtPhone.setBounds(110, 153, 242, 20);
		txtPhone.setColumns(60);
		txtPhone.setName("PHONE");
		pnlClient.add(txtPhone);

		// EMAIL
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 185, 71, 25);
		pnlClient.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(110, 185, 242, 20);
		txtEmail.setColumns(60);
		txtEmail.setName("EMAIL");
		pnlClient.add(txtEmail);

		return pnlClient;
	}

	private Component makePnlEvent() {
		// create a panel object and set its layout to null(absolute)
		pnlEvent = new JPanel();
		pnlEvent.setLayout(null);

		// EVENT NAME
		JLabel lblEventName = new JLabel("Event Name");
		lblEventName.setHorizontalAlignment(SwingConstants.LEFT);
		lblEventName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEventName.setBounds(10, 0, 140, 25);
		pnlEvent.add(lblEventName);

		txtEventName = new JTextField();
		txtEventName.setBounds(140, 3, 242, 20);
		txtEventName.setColumns(60);
		txtEventName.setName("EVNAME");
		pnlEvent.add(txtEventName);

		// DATE
		JLabel lblDate = new JLabel("Date (MO/DA/YEAR)");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(10, 25, 140, 25);
		pnlEvent.add(lblDate);

		txtDate = new JTextField();
		txtDate.setBounds(140, 28, 242, 20);
		txtDate.setColumns(60);
		txtDate.setName("DATE");
		pnlEvent.add(txtDate);

		// TIME
		JLabel lblTime = new JLabel("Time (XX:XX _M)");
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTime.setBounds(10, 50, 140, 25);
		pnlEvent.add(lblTime);

		txtTime = new JTextField();
		txtTime.setBounds(140, 53, 242, 20);
		txtTime.setColumns(60);
		txtTime.setName("TIME");
		pnlEvent.add(txtTime);

		// VENUE
		JLabel lblVenue = new JLabel("Venue");
		lblVenue.setHorizontalAlignment(SwingConstants.LEFT);
		lblVenue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVenue.setBounds(10, 75, 71, 25);
		pnlEvent.add(lblVenue);

		txtOther = new JTextField();
		txtOther.setBounds(283, 78, 170, 20);
		txtOther.setColumns(10);
		txtOther.setEnabled(false);
		txtOther.setName("OTHER");
		pnlEvent.add(txtOther);

		rdbtnOurBanquet = new JRadioButton("Our Banquet");
		rdbtnOurBanquet.setBounds(80, 78, 113, 23);
		rdbtnOurBanquet.setName("OB");
		;
		rdbtnOurBanquet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				txtOther.setText(""); // empty the text-box as our banquet is
										// selected
				txtOther.setEnabled(false); // disable the text-box
			}
		});
		rdbtnOurBanquet.setSelected(true);
		pnlEvent.add(rdbtnOurBanquet);

		rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBounds(201, 78, 76, 23);
		rdbtnOther.setName("O");
		rdbtnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				txtOther.setEnabled(true); // enable the text-box
			}
		});
		pnlEvent.add(rdbtnOther);

		rdbtnGroupVenue = new ButtonGroup();
		rdbtnGroupVenue.add(rdbtnOurBanquet);
		rdbtnGroupVenue.add(rdbtnOther);

		// ATTENDEES
		JLabel lblAttend = new JLabel("No. Attendees");
		lblAttend.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttend.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAttend.setBounds(10, 100, 120, 25);
		pnlEvent.add(lblAttend);

		txtAttend = new JTextField();
		txtAttend.setBounds(140, 103, 242, 20);
		txtAttend.setColumns(60);
		txtAttend.setName("ATTEND");
		pnlEvent.add(txtAttend);

		// NOTES
		JLabel lblNotes = new JLabel("Notes (If Any)");
		lblNotes.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNotes.setBounds(10, 125, 105, 25);
		pnlEvent.add(lblNotes);

		txtNotes = new JTextArea();
		txtNotes.setLineWrap(true);
		txtNotes.setTabSize(4);
		txtNotes.setRows(4);
		txtNotes.setName("NOTES");
		txtNotes.setWrapStyleWord(true);
		// textArea.setBounds(10, 125, 312, 81);
		JScrollPane sp = new JScrollPane(txtNotes);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setName("NOTE_SCROLL");
		sp.setBounds(10, 150, 312, 81);
		pnlEvent.add(sp);

		return pnlEvent;
	}

	private Component makePnlOrder() {
		// create a panel object and set its layout to null(absolute)
		JPanel pnlOrder = new JPanel();
		pnlOrder.setLayout(null);

		pnlOrder.add(getTabsOrder());

		return pnlOrder;
	}

	private Component makePnlInvoice() {
		// create a panel object and set its layout to null(absolute)
		final JPanel pnlInvoice = new JPanel();
		pnlInvoice.setLayout(null);

		JLabel lblAmntPaid = new JLabel("Amount Paid ($)");
		lblAmntPaid.setBounds(175, 175, 100, 25);
		pnlInvoice.add(lblAmntPaid);

		txtAmountPaid = new JTextField("0");
		txtAmountPaid.setBounds(175, 205, 100, 25);
		pnlInvoice.add(txtAmountPaid);

		JLabel lblInvoiceWarning = new JLabel("Refresh After Order Changes!");
		lblInvoiceWarning.setBounds(1, 20, 170, 23);
		lblInvoiceWarning.setForeground(c);
		pnlInvoice.add(lblInvoiceWarning);

		JLabel lblDrinkInvoiceHeader = new JLabel("Drinks:  ");
		lblDrinkInvoiceHeader.setBounds(1, 40, 100, 25);
		pnlInvoice.add(lblDrinkInvoiceHeader);

		JLabel lblSnackInvoiceHeader = new JLabel("Snacks:  ");
		lblSnackInvoiceHeader.setBounds(1, 65, 100, 25);
		pnlInvoice.add(lblSnackInvoiceHeader);

		JLabel lblEntreeInvoiceHeader = new JLabel("Entrees:  ");
		lblEntreeInvoiceHeader.setBounds(1, 90, 100, 25);
		pnlInvoice.add(lblEntreeInvoiceHeader);

		JLabel lblDessertInvoiceHeader = new JLabel("Desserts:  ");
		lblDessertInvoiceHeader.setBounds(1, 115, 100, 25);
		pnlInvoice.add(lblDessertInvoiceHeader);

		JLabel lblTaxInvoiceHeader = new JLabel("Sales Tax (" + SALES_TAX
				+ "):  ");
		lblTaxInvoiceHeader.setBounds(1, 140, 100, 25);
		pnlInvoice.add(lblTaxInvoiceHeader);

		JLabel lblLaborHeader = new JLabel("Labor:  ");
		lblLaborHeader.setBounds(1, 165, 100, 25);
		pnlInvoice.add(lblLaborHeader);

		JLabel lblTotalInvoiceHeader = new JLabel("Total:  ");
		lblTotalInvoiceHeader.setBounds(1, 190, 100, 25);
		pnlInvoice.add(lblTotalInvoiceHeader);

		JLabel lblBalanceDueHeader = new JLabel("Amount Due:  ");
		lblBalanceDueHeader.setBounds(1, 215, 100, 25);
		pnlInvoice.add(lblBalanceDueHeader);

		JLabel lblTotalFinal = new JLabel("Total:  ");
		lblTotalFinal.setBounds(175, 25, 100, 25);
		pnlInvoice.add(lblTotalFinal);

		JLabel lblAmntPaidFinal = new JLabel("Amount Paid:  ");
		lblAmntPaidFinal.setBounds(175, 50, 100, 25);
		pnlInvoice.add(lblAmntPaidFinal);

		JLabel lblAmntDueFinal = new JLabel("Amount Due:  ");
		lblAmntDueFinal.setBounds(175, 75, 100, 25);
		pnlInvoice.add(lblAmntDueFinal);

		final JLabel lblDrinkInvoiceValue = new JLabel(
				Double.toString(drinkSubtotal));
		final JLabel lblSnackInvoiceValue = new JLabel(
				Double.toString(snackSubtotal));
		final JLabel lblEntreeInvoiceValue = new JLabel(
				Double.toString(entreeSubtotal));
		final JLabel lblDessertInvoiceValue = new JLabel(
				Double.toString(dessertSubtotal));
		final JLabel lblTaxInvoiceValue = new JLabel(
				Double.toString(totalTaxSubtotal));
		final JLabel lblLaborValue = new JLabel(
				Double.toString(totalLaborSubtotal));
		final JLabel lblTotalInvoiceValue = new JLabel(
				Double.toString(totalItemsCost));
		final JLabel lblBalanceDueValue = new JLabel(
				Double.toString(totalItemsCost * .5));
		final JLabel lblTotalFinalValue = new JLabel(
				Double.toString(totalItemsCost));
		final JLabel lblAmntPaidFinalValue = new JLabel(
				Double.toString(amountPaid));
		final JLabel lblAmntDueFinalValue = new JLabel(
				Double.toString(amountDue));

		JButton btnDeposit = new JButton("Deposit Invoice");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearAllCosts();

				calcDrinkSubtotal();
				calcSnacksSubtotal();
				calcEntreeSubtotal();
				calcDessertSubtotal();
				calcTotalFoodSubtotal();
				calcTotalTaxSubtotal();
				calcTotalLaborSubtotal();
				calcTotalItemsCost();

				lblDrinkInvoiceValue.setBounds(100, 40, 100, 25);
				lblSnackInvoiceValue.setBounds(100, 65, 100, 25);
				lblEntreeInvoiceValue.setBounds(100, 90, 100, 25);
				lblDessertInvoiceValue.setBounds(100, 115, 100, 25);
				lblTaxInvoiceValue.setBounds(100, 140, 100, 25);
				lblLaborValue.setBounds(100, 165, 100, 25);
				lblTotalInvoiceValue.setBounds(100, 190, 100, 25);
				lblBalanceDueValue.setBounds(100, 215, 100, 25);

				pnlInvoice.add(lblDrinkInvoiceValue);
				pnlInvoice.add(lblSnackInvoiceValue);
				pnlInvoice.add(lblEntreeInvoiceValue);
				pnlInvoice.add(lblDessertInvoiceValue);
				pnlInvoice.add(lblTaxInvoiceValue);
				pnlInvoice.add(lblLaborValue);
				pnlInvoice.add(lblTotalInvoiceValue);
				pnlInvoice.add(lblBalanceDueValue);

				lblDrinkInvoiceValue.setText("$ "
						+ Double.toString(drinkSubtotal));
				lblSnackInvoiceValue.setText("$ "
						+ Double.toString(snackSubtotal));
				lblEntreeInvoiceValue.setText("$ "
						+ Double.toString(entreeSubtotal));
				lblDessertInvoiceValue.setText("$ "
						+ Double.toString(dessertSubtotal));
				lblTaxInvoiceValue.setText("$ "
						+ Double.toString(totalTaxSubtotal));
				lblLaborValue.setText("$ "
						+ Double.toString(totalLaborSubtotal));
				lblTotalInvoiceValue.setText("$ "
						+ Double.toString(totalItemsCost));

				if ((totalItemsCost * 0.5) < 500 && totalItemsCost > 500) {
					lblBalanceDueValue.setText("$ 500.00");
				} else if ((totalItemsCost * 0.5) < 500 && totalItemsCost < 500) {
					lblBalanceDueValue.setText("$ "
							+ Double.toString(totalItemsCost));
				}

				else {
					lblBalanceDueValue.setText("$ "
							+ Double.toString(totalItemsCost * 0.5));
				}

				pnlInvoice.repaint();

			}
		});

		btnDeposit.setBounds(1, 1, 125, 23);
		pnlInvoice.add(btnDeposit);

		JButton btnFinal = new JButton("Final Invoice");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblTotalFinalValue.setBounds(275, 25, 100, 25);
				lblAmntPaidFinalValue.setBounds(275, 50, 100, 25);
				lblAmntDueFinalValue.setBounds(275, 75, 100, 25);

				pnlInvoice.add(lblTotalFinalValue);
				pnlInvoice.add(lblAmntPaidFinalValue);
				pnlInvoice.add(lblAmntDueFinalValue);

				lblTotalFinalValue.setText("$ "
						+ Double.toString(totalItemsCost));
				lblAmntPaidFinalValue.setText("$ "
						+ Double.toString(amountPaid));
				lblAmntDueFinalValue.setText("$ " + Double.toString(amountDue));

				pnlInvoice.repaint();

			}
		});
		btnFinal.setBounds(175, 1, 125, 23);
		pnlInvoice.add(btnFinal);

		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ArrayList<HashMap<String,String>> tempList = Add();
				// CommonMethods.hmEvents.put(((HashMap<String,
				// String>)tempList.get(1)).get("EVNAME"), tempList);
				amountPaid = Double.parseDouble(txtAmountPaid.getText());
				amountDue = totalItemsCost - amountPaid;

				lblTotalFinalValue.setText("$ "
						+ Double.toString(totalItemsCost));
				lblAmntPaidFinalValue.setText("$ "
						+ Double.toString(amountPaid));
				lblAmntDueFinalValue.setText("$ " + Double.toString(amountDue));
				clearSnackSubtotal();

				pnlInvoice.repaint();

			}
		});
		btnDone.setBounds(405, 212, 80, 23);
		pnlInvoice.add(btnDone);

		return pnlInvoice;
	}

	// Making the Menu Display Tabs
	// Drinks
	private Component makePnlDrinks() {
		// create a panel object and set its layout to null(absolute)
		JPanel pnlDrinks = new JPanel();
		pnlDrinks.setLayout(null);

		// create scroll pane
		JScrollPane drinkPane = new JScrollPane();
		drinkPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		drinkPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// drinkPane.setBounds(1, 3, 275, 235);
		drinkPane.setBounds(1, 3, 490, 235);
		pnlDrinks.add(drinkPane);

		// create table of drink items for menu
		drinkTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		drinkTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		drinkTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		drinkTable.setBackground(UIManager.getColor("Button.background"));
		drinkTable.setEnabled(false);
		drinkPane.setViewportView(drinkTable);

		return pnlDrinks;
	}

	// Snacks
	private Component makePnlSnacks() {
		// create a panel object and set its layout to null(absolute)
		JPanel pnlSnacks = new JPanel();
		pnlSnacks.setLayout(null);

		// create scroll pane
		JScrollPane snackPane = new JScrollPane();
		snackPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		snackPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// snackPane.setBounds(60, 3, 275, 235);
		snackPane.setBounds(1, 3, 490, 235);
		pnlSnacks.add(snackPane);

		// create table of snack items for menu
		snackTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		snackTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		snackTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		snackTable.setBackground(UIManager.getColor("Button.background"));
		snackTable.setEnabled(false);
		snackPane.setViewportView(snackTable);

		return pnlSnacks;
	}

	// Entrees
	private Component makePnlEntrees() {
		// create a panel object and set its layout to null(absolute)
		JPanel pnlEntrees = new JPanel();
		pnlEntrees.setLayout(null);

		JScrollPane entreePane = new JScrollPane();
		entreePane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		entreePane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// entreePane.setBounds(123, 3, 275, 235);
		entreePane.setBounds(1, 3, 490, 235);
		pnlEntrees.add(entreePane);

		// create table of entree items for menu
		entreeTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		entreeTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		entreeTable.getColumnModel().getColumn(2).setPreferredWidth(90);
		entreeTable.setBackground(UIManager.getColor("Button.background"));
		entreeTable.setEnabled(false);
		entreePane.setViewportView(entreeTable);

		return pnlEntrees;
	}

	// Desserts
	private Component makePnlDesserts() {
		// create a panel object and set its layout to null(absolute)
		JPanel pnlDesserts = new JPanel();
		pnlDesserts.setLayout(null);

		JScrollPane dessertPane = new JScrollPane();
		dessertPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dessertPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// dessertPane.setBounds(188, 3, 275, 235);
		dessertPane.setBounds(1, 3, 490, 235);
		pnlDesserts.add(dessertPane);

		// create table of dessert items for menu
		dessertTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		dessertTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		dessertTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		dessertTable.setBackground(UIManager.getColor("Button.background"));
		dessertTable.setEnabled(false);
		dessertPane.setViewportView(dessertTable);

		return pnlDesserts;
	}

	// Making the Order Tabs
	// Drink Order
	private Component makePnlDrinksOrder() {
		// create a panel object and set its layout to null(absolute)
		final JPanel pnlDrinksOrder = new JPanel();
		pnlDrinksOrder.setLayout(null);

		// create scroll pane
		JScrollPane drinkOrderPane = new JScrollPane();
		drinkOrderPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		drinkOrderPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		drinkOrderPane.setBounds(1, 3, 325, 200);
		pnlDrinksOrder.add(drinkOrderPane);

		// create table of drink items for menu
		JTable drinkOrderTable = new JTable(drinkArray, columnHeaders);
		drinkOrderTable.setEnabled(false);
		drinkOrderTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		drinkOrderTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		drinkOrderTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		drinkOrderTable.setBackground(UIManager.getColor("Button.background"));

		drinkOrderPane.setViewportView(drinkOrderTable);

		// create header label for user data
		JLabel drinkQntyLbl = new JLabel("Enter Qnty");
		drinkQntyLbl.setBounds(335, 5, 60, 17);
		drinkQntyLbl.setForeground(c);
		pnlDrinksOrder.add(drinkQntyLbl);

		// position input fields
		txtDrink0.setBounds(342, 24, 40, 17);
		txtDrink1.setBounds(342, 40, 40, 17);
		txtDrink2.setBounds(342, 56, 40, 17);
		txtDrink3.setBounds(342, 72, 40, 17);
		txtDrink4.setBounds(342, 88, 40, 17);
		txtDrink5.setBounds(342, 104, 40, 17);
		txtDrink6.setBounds(342, 120, 40, 17);
		txtDrink7.setBounds(342, 136, 40, 17);
		txtDrink8.setBounds(342, 152, 40, 17);

		// add fields to panel
		pnlDrinksOrder.add(txtDrink0);
		pnlDrinksOrder.add(txtDrink1);
		pnlDrinksOrder.add(txtDrink2);
		pnlDrinksOrder.add(txtDrink3);
		pnlDrinksOrder.add(txtDrink4);
		pnlDrinksOrder.add(txtDrink5);
		pnlDrinksOrder.add(txtDrink6);
		pnlDrinksOrder.add(txtDrink7);
		pnlDrinksOrder.add(txtDrink8);

		// Stephen: Set names of all text boxes for all Drinks, Snacks, Entrees,
		// and Desserts

		// label for drink subtotal cost
		JLabel dSubLbl = new JLabel("Drinks Subtotal");
		dSubLbl.setBounds(35, 198, 120, 23);
		pnlDrinksOrder.add(dSubLbl);

		// display drink subtotal cost
		final JLabel dSubtotal = new JLabel("$ "
				+ Double.toString(drinkSubtotal));
		dSubtotal.setBounds(35, 214, 120, 23);
		pnlDrinksOrder.add(dSubtotal);

		// add SAVE button
		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean val = validateOrders("DRINK");
				if (val) {
					drinkQnty[0] = Integer.parseInt(txtDrink0.getText());
					drinkQnty[1] = Integer.parseInt(txtDrink1.getText());
					drinkQnty[2] = Integer.parseInt(txtDrink2.getText());
					drinkQnty[3] = Integer.parseInt(txtDrink3.getText());
					drinkQnty[4] = Integer.parseInt(txtDrink4.getText());
					drinkQnty[5] = Integer.parseInt(txtDrink5.getText());
					drinkQnty[6] = Integer.parseInt(txtDrink6.getText());
					drinkQnty[7] = Integer.parseInt(txtDrink7.getText());
					drinkQnty[8] = Integer.parseInt(txtDrink8.getText());

					clearDrinkSubtotal();
					calcDrinkSubtotal();
					dSubtotal.setText("$ " + Double.toString(drinkSubtotal));

					pnlDrinksOrder.repaint();
				}

			}
		});
		btnDone.setBounds(328, 208, 80, 23);
		pnlDrinksOrder.add(btnDone);

		return pnlDrinksOrder;
	}

	// Snack Order
	private Component makePnlSnacksOrder() {
		// create a panel object and set its layout to null(absolute)
		final JPanel pnlSnacksOrder = new JPanel();
		pnlSnacksOrder.setLayout(null);

		// create scroll pane
		JScrollPane snackOrderPane = new JScrollPane();
		snackOrderPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		snackOrderPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		snackOrderPane.setBounds(1, 3, 325, 200);
		pnlSnacksOrder.add(snackOrderPane);

		// create table of snack items for menu
		JTable snackOrderTable = new JTable(snackArray, columnHeaders);
		snackOrderTable.setEnabled(false);
		snackOrderTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		snackOrderTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		snackOrderTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		snackOrderTable.setBackground(UIManager.getColor("Button.background"));

		snackOrderPane.setViewportView(snackOrderTable);

		// create header label for user data
		JLabel snackQntyLbl = new JLabel("Enter Qnty");
		snackQntyLbl.setBounds(335, 5, 60, 17);
		snackQntyLbl.setForeground(c);
		pnlSnacksOrder.add(snackQntyLbl);

		// position input fields
		txtSnack0.setBounds(342, 24, 40, 17);
		txtSnack1.setBounds(342, 40, 40, 17);
		txtSnack2.setBounds(342, 56, 40, 17);
		txtSnack3.setBounds(342, 72, 40, 17);
		txtSnack4.setBounds(342, 88, 40, 17);
		txtSnack5.setBounds(342, 104, 40, 17);
		txtSnack6.setBounds(342, 120, 40, 17);
		txtSnack7.setBounds(342, 136, 40, 17);
		txtSnack8.setBounds(342, 152, 40, 17);

		// add fields to panel
		pnlSnacksOrder.add(txtSnack0);
		pnlSnacksOrder.add(txtSnack1);
		pnlSnacksOrder.add(txtSnack2);
		pnlSnacksOrder.add(txtSnack3);
		pnlSnacksOrder.add(txtSnack4);
		pnlSnacksOrder.add(txtSnack5);
		pnlSnacksOrder.add(txtSnack6);
		pnlSnacksOrder.add(txtSnack7);
		pnlSnacksOrder.add(txtSnack8);

		// label for snacks subtotal cost
		JLabel sSubLbl = new JLabel("Snacks Subtotal");
		sSubLbl.setBounds(35, 198, 120, 23);
		pnlSnacksOrder.add(sSubLbl);

		// display snack subtotal cost

		final JLabel sSubtotal = new JLabel("$ "
				+ Double.toString(snackSubtotal));
		sSubtotal.setBounds(35, 214, 120, 23);
		pnlSnacksOrder.add(sSubtotal);

		// add SAVE button
		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean val = validateOrders("SNACK");
				if (val) {
					snackQnty[0] = Integer.parseInt(txtSnack0.getText());
					snackQnty[1] = Integer.parseInt(txtSnack1.getText());
					snackQnty[2] = Integer.parseInt(txtSnack2.getText());
					snackQnty[3] = Integer.parseInt(txtSnack3.getText());
					snackQnty[4] = Integer.parseInt(txtSnack4.getText());
					snackQnty[5] = Integer.parseInt(txtSnack5.getText());
					snackQnty[6] = Integer.parseInt(txtSnack6.getText());
					snackQnty[7] = Integer.parseInt(txtSnack7.getText());
					snackQnty[8] = Integer.parseInt(txtSnack8.getText());

					clearSnackSubtotal();
					calcSnacksSubtotal();
					sSubtotal.setText("$ " + Double.toString(snackSubtotal));

					pnlSnacksOrder.repaint();
				}
			}
		});
		btnDone.setBounds(328, 208, 80, 23);
		pnlSnacksOrder.add(btnDone);

		return pnlSnacksOrder;
	}// end makePnlSnacksOrder

	// Entree Order
	private Component makePnlEntreesOrder() {
		// create a panel object and set its layout to null(absolute)
		final JPanel pnlEntreesOrder = new JPanel();
		pnlEntreesOrder.setLayout(null);

		JScrollPane entreeOrderPane = new JScrollPane();
		entreeOrderPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		entreeOrderPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		entreeOrderPane.setBounds(1, 3, 325, 200);
		pnlEntreesOrder.add(entreeOrderPane);

		// create table of entree items for menu
		JTable entreeOrderTable = new JTable(entreeArray, columnHeaders);
		entreeOrderTable.setEnabled(false);
		entreeOrderTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		entreeOrderTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		entreeOrderTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		entreeOrderTable.setBackground(UIManager.getColor("Button.background"));

		entreeOrderPane.setViewportView(entreeOrderTable);

		// create header label for user data
		JLabel entreeQntyLbl = new JLabel("Enter Qnty");
		entreeQntyLbl.setBounds(335, 5, 60, 17);
		entreeQntyLbl.setForeground(c);
		pnlEntreesOrder.add(entreeQntyLbl);

		// position input fields
		txtEntree0.setBounds(342, 24, 40, 17);
		txtEntree1.setBounds(342, 40, 40, 17);
		txtEntree2.setBounds(342, 56, 40, 17);
		txtEntree3.setBounds(342, 72, 40, 17);
		txtEntree4.setBounds(342, 88, 40, 17);
		txtEntree5.setBounds(342, 104, 40, 17);
		txtEntree6.setBounds(342, 120, 40, 17);
		txtEntree7.setBounds(342, 136, 40, 17);
		txtEntree8.setBounds(342, 152, 40, 17);

		// add fields to panel
		pnlEntreesOrder.add(txtEntree0);
		pnlEntreesOrder.add(txtEntree1);
		pnlEntreesOrder.add(txtEntree2);
		pnlEntreesOrder.add(txtEntree3);
		pnlEntreesOrder.add(txtEntree4);
		pnlEntreesOrder.add(txtEntree5);
		pnlEntreesOrder.add(txtEntree6);
		pnlEntreesOrder.add(txtEntree7);
		pnlEntreesOrder.add(txtEntree8);

		// label for entree subtotal cost
		JLabel eSubLbl = new JLabel("Entrees Subtotal");
		eSubLbl.setBounds(35, 198, 120, 23);
		pnlEntreesOrder.add(eSubLbl);

		// display entree subtotal cost
		final JLabel eSubtotal = new JLabel("$ "
				+ Double.toString(entreeSubtotal));
		eSubtotal.setBounds(35, 214, 120, 23);
		pnlEntreesOrder.add(eSubtotal);

		// add SAVE button
		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean val = validateOrders("ENTREE");
				if (val) {
					entreeQnty[0] = Integer.parseInt(txtEntree0.getText());
					entreeQnty[1] = Integer.parseInt(txtEntree1.getText());
					entreeQnty[2] = Integer.parseInt(txtEntree2.getText());
					entreeQnty[3] = Integer.parseInt(txtEntree3.getText());
					entreeQnty[4] = Integer.parseInt(txtEntree4.getText());
					entreeQnty[5] = Integer.parseInt(txtEntree5.getText());
					entreeQnty[6] = Integer.parseInt(txtEntree6.getText());
					entreeQnty[7] = Integer.parseInt(txtEntree7.getText());
					entreeQnty[8] = Integer.parseInt(txtEntree8.getText());

					clearEntreeSubtotal();
					calcEntreeSubtotal();
					eSubtotal.setText("$ " + Double.toString(entreeSubtotal));

					pnlEntreesOrder.repaint();
				}
			}
		});
		btnDone.setBounds(328, 208, 80, 23);
		pnlEntreesOrder.add(btnDone);

		return pnlEntreesOrder;
	}

	// Dessert Order
	private Component makePnlDessertsOrder() {
		// create a panel object and set its layout to null(absolute)
		final JPanel pnlDessertsOrder = new JPanel();
		pnlDessertsOrder.setLayout(null);

		JScrollPane dessertOrderPane = new JScrollPane();
		dessertOrderPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dessertOrderPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		dessertOrderPane.setBounds(1, 3, 325, 200);
		pnlDessertsOrder.add(dessertOrderPane);

		// create table of dessert items for menu
		JTable dessertOrderTable = new JTable(dessertArray, columnHeaders);
		dessertOrderTable.setEnabled(false);
		dessertOrderTable.getColumnModel().getColumn(0).setPreferredWidth(135);
		dessertOrderTable.getColumnModel().getColumn(1).setPreferredWidth(50);
		dessertOrderTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		dessertOrderTable
				.setBackground(UIManager.getColor("Button.background"));

		dessertOrderPane.setViewportView(dessertOrderTable);

		// create header label for user data
		JLabel dessertQntyLbl = new JLabel("Enter Qnty");
		dessertQntyLbl.setBounds(335, 5, 60, 17);
		dessertQntyLbl.setForeground(c);
		pnlDessertsOrder.add(dessertQntyLbl);

		// position input fields
		txtDessert0.setBounds(342, 24, 40, 17);
		txtDessert1.setBounds(342, 40, 40, 17);
		txtDessert2.setBounds(342, 56, 40, 17);
		txtDessert3.setBounds(342, 72, 40, 17);
		txtDessert4.setBounds(342, 88, 40, 17);
		txtDessert5.setBounds(342, 104, 40, 17);
		txtDessert6.setBounds(342, 120, 40, 17);
		txtDessert7.setBounds(342, 136, 40, 17);
		txtDessert8.setBounds(342, 152, 40, 17);
		txtDessert9.setBounds(342, 168, 40, 17);

		// add fields to panel
		pnlDessertsOrder.add(txtDessert0);
		pnlDessertsOrder.add(txtDessert1);
		pnlDessertsOrder.add(txtDessert2);
		pnlDessertsOrder.add(txtDessert3);
		pnlDessertsOrder.add(txtDessert4);
		pnlDessertsOrder.add(txtDessert5);
		pnlDessertsOrder.add(txtDessert6);
		pnlDessertsOrder.add(txtDessert7);
		pnlDessertsOrder.add(txtDessert8);
		pnlDessertsOrder.add(txtDessert9);

		// label for dessert subtotal cost
		JLabel dSubLbl = new JLabel("Desserts Subtotal");
		dSubLbl.setBounds(35, 198, 120, 23);
		pnlDessertsOrder.add(dSubLbl);

		// display desserts subtotal cost
		final JLabel dSubtotal = new JLabel("$ "
				+ Double.toString(dessertSubtotal));
		dSubtotal.setBounds(35, 214, 120, 23);
		pnlDessertsOrder.add(dSubtotal);

		// add SAVE button
		JButton btnDone = new JButton("DONE");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean val = validateOrders("DESSERT");
				if (val) {
					dessertQnty[0] = Integer.parseInt(txtDessert0.getText());
					dessertQnty[1] = Integer.parseInt(txtDessert1.getText());
					dessertQnty[2] = Integer.parseInt(txtDessert2.getText());
					dessertQnty[3] = Integer.parseInt(txtDessert3.getText());
					dessertQnty[4] = Integer.parseInt(txtDessert4.getText());
					dessertQnty[5] = Integer.parseInt(txtDessert5.getText());
					dessertQnty[6] = Integer.parseInt(txtDessert6.getText());
					dessertQnty[7] = Integer.parseInt(txtDessert7.getText());
					dessertQnty[8] = Integer.parseInt(txtDessert8.getText());
					dessertQnty[9] = Integer.parseInt(txtDessert9.getText());

					clearDessertSubtotal();
					calcDessertSubtotal();
					dSubtotal.setText("$ " + Double.toString(dessertSubtotal));

					pnlDessertsOrder.repaint();
				}
			}
		});
		btnDone.setBounds(328, 208, 80, 23);
		pnlDessertsOrder.add(btnDone);

		return pnlDessertsOrder;
	}

	private Component makeChefInstructions() {
		// create a panel object and set its layout to null(absolute)
		final JPanel pnlChefInstructions = new JPanel();
		pnlChefInstructions.setLayout(null);

		JLabel lblAddHead = new JLabel("Chef Instructions");
		lblAddHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddHead.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddHead.setBounds(146, 12, 174, 27);
		pnlChefInstructions.add(lblAddHead);
        
		tA = new JTextArea("");
		tA.setBounds(20, 40, 420, 150);
		tA.append("\tITEMS\t\tQNTY\n-------------------------------------------------------------------------------------------------------------\n");
		tA.setName("SCROLL");
		pnlChefInstructions.add(tA);

		// add scroll pane
		JScrollPane sp = new JScrollPane(tA);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		sp.setBounds(20, 40, 420, 150);
		pnlChefInstructions.add(sp);

		for (int i = 0; i < 9; i++) {
			if (drinkQnty[i] > 0) {
				tA.append(drinkTable.getValueAt(i, 0).toString() + "\t\t\t"
						+ drinkQnty[i] + "\n");
			}
		}// find all ordered drinks and display

		for (int i = 0; i < 9; i++) {
			if (snackQnty[i] > 0) {
				tA.append(snackTable.getValueAt(i, 0).toString() + "\t\t\t"
						+ snackQnty[i] + "\n");
			}
		}// find all ordered snacks and display

		for (int i = 0; i < 9; i++) {
			if (entreeQnty[i] > 0) {
				tA.append(entreeTable.getValueAt(i, 0).toString() + "\t\t\t"
						+ entreeQnty[i] + "\n");
			}
		}// find all ordered entrees and display

		for (int i = 0; i < 9; i++) {
			if (dessertQnty[i] > 0) {
				tA.append(dessertTable.getValueAt(i, 0).toString() + "\t\t"
						+ dessertQnty[i] + "\n");
			}
		}// find all ordered desserts and display

		JButton btnNext = new JButton("SAVE TO FILE");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane
						.showMessageDialog(
								null,
								"File for Chef Instructions for this Event Saved...\n\n",
								"SAVE TO FILE", JOptionPane.INFORMATION_MESSAGE);
				
				writeToFile(tA.getText());
				
			}
		});
		btnNext.setBounds(350, 212, 130, 23);
		pnlChefInstructions.add(btnNext);

		return pnlChefInstructions;
	}

	private Component makeAssignStaff() {
		// create a panel object and set its layout to null(absolute)
		final JPanel pnlAssignStaff = new JPanel();
		pnlAssignStaff.setLayout(null);

		JLabel lblAddHead = new JLabel("Staff Assignment");
		lblAddHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddHead.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddHead.setBounds(146, 12, 174, 27);
		pnlAssignStaff.add(lblAddHead);

		chkStaff1 = new JCheckBox("Amanda");
		chkStaff2 = new JCheckBox("Brian");
		chkStaff3 = new JCheckBox("Charlie");
		chkStaff4 = new JCheckBox("Scott");
		chkStaff5 = new JCheckBox("Janet");
		chkStaff6 = new JCheckBox("Lisa");
		chkStaff7 = new JCheckBox("Mike");
		chkStaff8 = new JCheckBox("Allison");

		chkStaff1.setBounds(80, 40, 113, 23);
		chkStaff2.setBounds(80, 60, 113, 23);
		chkStaff3.setBounds(80, 80, 113, 23);
		chkStaff4.setBounds(80, 100, 113, 23);
		chkStaff5.setBounds(80, 120, 113, 23);
		chkStaff6.setBounds(80, 140, 113, 23);
		chkStaff7.setBounds(80, 160, 113, 23);
		chkStaff8.setBounds(80, 180, 113, 23);

		chkStaff1.setName("S1");
		chkStaff2.setName("S2");
		chkStaff3.setName("S3");
		chkStaff4.setName("S4");
		chkStaff5.setName("S5");
		chkStaff6.setName("S6");
		chkStaff7.setName("S7");
		chkStaff8.setName("S8");

		chkStaff1.setSelected(false);
		chkStaff2.setSelected(false);
		chkStaff3.setSelected(false);
		chkStaff4.setSelected(false);
		chkStaff5.setSelected(false);
		chkStaff6.setSelected(false);
		chkStaff7.setSelected(false);
		chkStaff8.setSelected(false);

		pnlAssignStaff.add(chkStaff1);
		pnlAssignStaff.add(chkStaff2);
		pnlAssignStaff.add(chkStaff3);
		pnlAssignStaff.add(chkStaff4);
		pnlAssignStaff.add(chkStaff5);
		pnlAssignStaff.add(chkStaff6);
		pnlAssignStaff.add(chkStaff7);
		pnlAssignStaff.add(chkStaff8);

		return pnlAssignStaff;
	}

	public boolean Add() {

		boolean val = validation();

		if (val) {
			Save();
		}
		return val;
	}

	/**
	 * Validates the values provided by the user for the Orders tab
	 * 
	 * @param tab
	 * @return
	 */
	private boolean validateOrders(String tab) {
		String disp = "";

		switch (tab) {
		case "DRINK":
			if (txtDrink0.getText().trim().equals("")
					|| txtDrink1.getText().trim().equals("")
					|| txtDrink2.getText().trim().equals("")
					|| txtDrink3.getText().trim().equals("")
					|| txtDrink4.getText().trim().equals("")
					|| txtDrink5.getText().trim().equals("")
					|| txtDrink6.getText().trim().equals("")
					|| txtDrink7.getText().trim().equals("")
					|| txtDrink8.getText().trim().equals(""))
				disp += "Quantity cannot be left blank";

			if (!Pattern.compile("[\\d]*").matcher(txtDrink0.getText().trim())
					.matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink1.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink2.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink3.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink4.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink5.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink6.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink7.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDrink8.getText().trim()).matches())
				disp += "Incorrect Quantity";

			break;
		case "SNACK":
			if (txtSnack0.getText().trim().equals("")
					|| txtSnack1.getText().trim().equals("")
					|| txtSnack2.getText().trim().equals("")
					|| txtSnack3.getText().trim().equals("")
					|| txtSnack4.getText().trim().equals("")
					|| txtSnack5.getText().trim().equals("")
					|| txtSnack6.getText().trim().equals("")
					|| txtSnack7.getText().trim().equals("")
					|| txtSnack8.getText().trim().equals(""))
				disp += "Quantity cannot be left blank";

			if (!Pattern.compile("[\\d]*").matcher(txtSnack0.getText().trim())
					.matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack1.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack2.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack3.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack4.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack5.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack6.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack7.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtSnack8.getText().trim()).matches())
				disp += "Incorrect Quantity";

			break;
		case "ENTREE":
			if (txtEntree0.getText().trim().equals("")
					|| txtEntree1.getText().trim().equals("")
					|| txtEntree2.getText().trim().equals("")
					|| txtEntree3.getText().trim().equals("")
					|| txtEntree4.getText().trim().equals("")
					|| txtEntree5.getText().trim().equals("")
					|| txtEntree6.getText().trim().equals("")
					|| txtEntree7.getText().trim().equals("")
					|| txtEntree8.getText().trim().equals(""))
				disp += "Quantity cannot be left blank";

			if (!Pattern.compile("[\\d]*").matcher(txtEntree0.getText().trim())
					.matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree1.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree2.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree3.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree4.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree5.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree6.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree7.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtEntree8.getText().trim()).matches())
				disp += "Incorrect Quantity";

			break;
		case "DESSERT":
			if (txtDessert0.getText().trim().equals("")
					|| txtDessert1.getText().trim().equals("")
					|| txtDessert2.getText().trim().equals("")
					|| txtDessert3.getText().trim().equals("")
					|| txtDessert4.getText().trim().equals("")
					|| txtDessert5.getText().trim().equals("")
					|| txtDessert6.getText().trim().equals("")
					|| txtDessert7.getText().trim().equals("")
					|| txtDessert8.getText().trim().equals("")
					|| txtDessert8.getText().trim().equals(""))
				disp += "Quantity cannot be left blank";

			if (!Pattern.compile("[\\d]*")
					.matcher(txtDessert0.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert1.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert2.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert3.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert4.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert5.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert6.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert7.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert8.getText().trim()).matches()
					|| !Pattern.compile("[\\d]*")
							.matcher(txtDessert9.getText().trim()).matches())
				disp += "Incorrect Quantity";
			break;
		}

		if (disp == "")
			return true;
		else {
			JOptionPane.showMessageDialog(null,
					"Please fix the following issues in Orders: \n\n" + disp,
					"Validate", JOptionPane.OK_OPTION);
			return false;
		}
	}

	/**
	 * validates the values provided by the user for the Client and Event Tabs
	 * 
	 * @return
	 */
	private boolean validation() {
		String disp = "";

		if (txtFN.getText().trim().equals("")
				|| txtLN.getText().trim().equals("")
				|| txtStreet.getText().trim().equals("")
				|| txtCity.getText().trim().equals("")
				|| cmbStates.getSelectedIndex() == 0
				|| txtZip.getText().trim().equals("")
				|| txtPhone.getText().trim().equals("")
				|| txtEmail.getText().trim().equals(""))

			disp += "All Client Details must be filled\n";

		if (!txtZip.getText().trim().equals("")
				&& !txtZip.getText().trim().matches("\\d{5}"))
			disp += "Incorrect format for Zip Code\n";

		if (!txtPhone.getText().trim().equals("")
				&& !txtPhone.getText().trim().matches("\\d{3}-\\d{7}"))
			disp += "Incorrect format for Phone\n";

		if (!txtEmail.getText().trim().equals("")
				&& !txtEmail
						.getText()
						.trim()
						.matches(
								"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
			disp += "Incorrect format for Email\n";

		if (txtEventName.getText().trim().equals("")
				|| txtDate.getText().trim().equals("")
				|| txtTime.getText().trim().equals("")
				|| txtAttend.getText().trim().equals(""))

			disp += "All Event Details must be filled\n";

		// date not working
		if (!txtDate.getText().trim().equals("")
				&& !Pattern
						.compile(
								"(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/(20\\d\\d)")
						.matcher(txtDate.getText().trim()).matches())
			disp += "Incorrect format for Date\n";
		

		if (!txtTime.getText().trim().equals("")
				&& !Pattern
						.compile("(1[012]|0?[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)")
						.matcher(txtTime.getText().trim()).matches())
			disp += "Incorrect format for Time\n";

		if (rdbtnOther.isSelected() && txtOther.getText().trim().equals(""))
			disp += "Please mention other venue name";

		if (disp == "")
			return true;
		else {
			JOptionPane.showMessageDialog(null,
					"Please fix the following issues: \n\n" + disp, "Validate",
					JOptionPane.OK_OPTION);
			return false;
		}
	}

	public boolean Update(String selEvent) {

		boolean val = validation();

		if (val) {
			updateXML(selEvent);
		}
		return val;

	}

	// TODO remove this function
	private void Save() {

		// Add Drink Tab details to the Hashmap
		hmDrinkTab.put("DRINK0", txtDrink0.getText());
		hmDrinkTab.put("DRINK1", txtDrink1.getText());
		hmDrinkTab.put("DRINK2", txtDrink2.getText());
		hmDrinkTab.put("DRINK3", txtDrink3.getText());
		hmDrinkTab.put("DRINK4", txtDrink4.getText());
		hmDrinkTab.put("DRINK5", txtDrink5.getText());
		hmDrinkTab.put("DRINK6", txtDrink6.getText());
		hmDrinkTab.put("DRINK7", txtDrink7.getText());
		hmDrinkTab.put("DRINK8", txtDrink8.getText());
		hmDrinkTab.put("DR_SUBTOTAL", "0");

		// Add Snack Tab details to the Hashmap
		hmSnackTab.put("SNACK0", txtSnack0.getText());
		hmSnackTab.put("SNACK1", txtSnack1.getText());
		hmSnackTab.put("SNACK2", txtSnack2.getText());
		hmSnackTab.put("SNACK3", txtSnack3.getText());
		hmSnackTab.put("SNACK4", txtSnack4.getText());
		hmSnackTab.put("SNACK5", txtSnack5.getText());
		hmSnackTab.put("SNACK6", txtSnack6.getText());
		hmSnackTab.put("SNACK7", txtSnack7.getText());
		hmSnackTab.put("SNACK8", txtSnack8.getText());
		hmSnackTab.put("S_SUBTOTAL", "0");

		// Add Entree Tab details to the Hashmap
		hmEntreeTab.put("ENTREE0", txtEntree0.getText());
		hmEntreeTab.put("ENTREE1", txtEntree1.getText());
		hmEntreeTab.put("ENTREE2", txtEntree2.getText());
		hmEntreeTab.put("ENTREE3", txtEntree3.getText());
		hmEntreeTab.put("ENTREE4", txtEntree4.getText());
		hmEntreeTab.put("ENTREE5", txtEntree5.getText());
		hmEntreeTab.put("ENTREE6", txtEntree6.getText());
		hmEntreeTab.put("ENTREE7", txtEntree7.getText());
		hmEntreeTab.put("ENTREE8", txtEntree8.getText());
		hmEntreeTab.put("E_SUBTOTAL", "0");

		// Add Dessert Tab details to the Hashmap
		hmDessertTab.put("DESSERT0", txtDessert0.getText());
		hmDessertTab.put("DESSERT1", txtDessert1.getText());
		hmDessertTab.put("DESSERT2", txtDessert2.getText());
		hmDessertTab.put("DESSERT3", txtDessert3.getText());
		hmDessertTab.put("DESSERT4", txtDessert4.getText());
		hmDessertTab.put("DESSERT5", txtDessert5.getText());
		hmDessertTab.put("DESSERT6", txtDessert6.getText());
		hmDessertTab.put("DESSERT7", txtDessert7.getText());
		hmDessertTab.put("DESSERT8", txtDessert8.getText());
		hmDessertTab.put("DESSERT9", txtDessert9.getText());
		hmDessertTab.put("DE_SUBTOTAL", "0");

		File dataFile = new File("file.xml");
		if (!dataFile.exists())
			createXML();
		else
			appendXML();
	}

	/**
	 * appends a new event to the file
	 */
	private void appendXML() {
		try {
			String filepath = "file.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node eventsRoot = doc.getFirstChild(); // get the root element

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(addNewEvent(doc,
					(Element) eventsRoot));
			StreamResult result = new StreamResult(new File(filepath));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File updated!");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	/**
	 * Creates the XML Schema for a new event
	 * 
	 * @param doc
	 * @param rootElement
	 * @return
	 */
	private Document addNewEvent(Document doc, Element rootElement) {
		// event elements
		Element event = doc.createElement("EVENT");
		rootElement.appendChild(event);

		// set attribute to event element
		event.setAttribute("NAME", txtEventName.getText().trim());

		// DATE element
		Element evDate = doc.createElement("DATE");
		evDate.appendChild(doc.createTextNode(txtDate.getText().trim()));
		event.appendChild(evDate);

		// TIME element
		Element evTime = doc.createElement("TIME");
		evTime.appendChild(doc.createTextNode(txtTime.getText().trim()));
		event.appendChild(evTime);

		// VENUE element
		Element evVenue = doc.createElement("VENUE");
		String ven = rdbtnOurBanquet.isSelected() ? "OUR BANQUET" : txtOther
				.getText().trim();
		evVenue.appendChild(doc.createTextNode(ven));
		event.appendChild(evVenue);

		// ATTEND element
		Element evAttend = doc.createElement("ATTEND");
		evAttend.appendChild(doc.createTextNode(txtAttend.getText().trim()));
		event.appendChild(evAttend);

		// NOTES element
		Element evNotes = doc.createElement("NOTES");
		evNotes.appendChild(doc.createTextNode(txtNotes.getText().trim()));
		event.appendChild(evNotes);

		// AMOUNT PAID element
		Element evPaid = doc.createElement("PAID");
		evPaid.appendChild(doc.createTextNode(txtAmountPaid.getText().trim()));
		event.appendChild(evPaid);

		// CLIENT FN element
		Element clFN = doc.createElement("CL_FN");
		clFN.appendChild(doc.createTextNode(txtFN.getText().trim()));
		event.appendChild(clFN);

		// CLIENT LN element
		Element clLN = doc.createElement("CL_LN");
		clLN.appendChild(doc.createTextNode(txtLN.getText().trim()));
		event.appendChild(clLN);

		// CLIENT STREET element
		Element clStreet = doc.createElement("CL_STREET");
		clStreet.appendChild(doc.createTextNode(txtStreet.getText().trim()));
		event.appendChild(clStreet);

		// CLIENT CITY element
		Element clCity = doc.createElement("CL_CITY");
		clCity.appendChild(doc.createTextNode(txtCity.getText().trim()));
		event.appendChild(clCity);

		// CLIENT STATE element
		Element clState = doc.createElement("CL_STATE");
		clState.appendChild(doc.createTextNode(cmbStates.getSelectedItem()
				.toString()));
		event.appendChild(clState);

		// CLIENT ZIP element
		Element clZip = doc.createElement("CL_ZIP");
		clZip.appendChild(doc.createTextNode(txtZip.getText().trim()));
		event.appendChild(clZip);

		// CLIENT PHONE element
		Element clPhone = doc.createElement("CL_PHONE");
		clPhone.appendChild(doc.createTextNode(txtPhone.getText().trim()));
		event.appendChild(clPhone);

		// CLIENT EMAIL element
		Element clEmail = doc.createElement("CL_EMAIL");
		clEmail.appendChild(doc.createTextNode(txtEmail.getText().trim()));
		event.appendChild(clEmail);
		
		//CHEF TEXT element
		/*Element chefscroll = doc.createElement("SCROLL");
		chefscroll.appendChild(doc.createTextNode(""));
		event.appendChild(chefscroll);*/
		
		//STAFF1
		Element st_assign1 = doc.createElement("ST_ASSIGN1");
		st_assign1.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign1);
		
		//STAFF2
		Element st_assign2 = doc.createElement("ST_ASSIGN2");
		st_assign2.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign2);
		
		//STAFF3
		Element st_assign3 = doc.createElement("ST_ASSIGN1");
		st_assign3.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign3);
		
		//STAFF4
		Element st_assign4 = doc.createElement("ST_ASSIGN4");
		st_assign4.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign4);
		
		//STAFF5
		Element st_assign5 = doc.createElement("ST_ASSIGN5");
		st_assign5.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign5);
		
		//STAFF6
		Element st_assign6 = doc.createElement("ST_ASSIGN6");
		st_assign6.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign6);
		
		//STAFF7
		Element st_assign7 = doc.createElement("ST_ASSIGN7");
		st_assign7.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign7);
		
		//STAFF8
		Element st_assign8 = doc.createElement("ST_ASSIGN8");
		st_assign8.appendChild(doc.createTextNode(""));
		event.appendChild(st_assign8);

		return doc;
	}

	/**
	 * Creates a new XML document if it does not exists
	 */
	private void createXML() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("EVENTS");
			doc.appendChild(rootElement);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(addNewEvent(doc, rootElement));
			StreamResult result = new StreamResult(new File("file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	/**
	 * Gets the scheduled Event Names and their respective Dates from the file
	 */
	public void getEvents() {
		try {
			File xmlFile = new File("file.xml");

			if (xmlFile.exists()) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(xmlFile);

				NodeList nList = doc.getElementsByTagName("EVENT");

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element e = (Element) nNode;
						arrEvents.add(e.getAttribute("NAME"));
						arrEventDates.add(e.getElementsByTagName("DATE")
								.item(0).getTextContent());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	 public void writeToFile(String text){
		//create a text file on chef_inst save to file button
		 try {
		 	String filename = "chef_"+txtEventName.getText().trim()+".txt";
		      File f1 = new File(filename);
		      if (!f1.exists()) { 
		    	  f1.createNewFile();
		       }
		      
	    	RandomAccessFile file = new RandomAccessFile(f1, "rw");
	    	//file.seek(position);
	    	file.writeUTF(text);
	    	file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	/**
	 * Updates the file by saving changes made by the user for the selected
	 * event
	 * 
	 * @param selEvent
	 */
	private void updateXML(String selEvent) {
		try {
			File xmlFile = new File("file.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			
				   	   

			NodeList nList = doc.getElementsByTagName("EVENT");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getAttribute("NAME").equals(selEvent)) {
						eElement.setAttribute("NAME", txtEventName.getText()
								.trim());
						
						
						NodeList list = eElement.getChildNodes(); // get the
																	// list of
																	// all nodes
																	// under
																	// this
																	// event

						for (int i = 0; i < list.getLength(); i++) {
							Node node = list.item(i);

							// get the DATE element, and update the value
							if (node.getNodeName().equals("DATE")) {
								node.setTextContent(txtDate.getText().trim());
							}

							// get the TIME element, and update the value
							if (node.getNodeName().equals("TIME")) {
								node.setTextContent(txtTime.getText().trim());
							}

							// get the VENUE element, and update the value
							if (node.getNodeName().equals("VENUE")) {
								String ven = rdbtnOurBanquet.isSelected() ? "OUR BANQUET"
										: txtOther.getText().trim();
								node.setTextContent(ven);
							}

							// get the ATTEND element, and update the value
							if (node.getNodeName().equals("ATTEND")) {
								node.setTextContent(txtAttend.getText().trim());
							}

							// get the NOTES element, and update the value
							if (node.getNodeName().equals("NOTES")) {
								node.setTextContent(txtNotes.getText().trim());
							}

							// get the AMOUNT PAID element, and update the value
							if (node.getNodeName().equals("PAID")) {
								node.setTextContent(txtAmountPaid.getText()
										.trim());
							}

							// updating the CLIENT Tab elements

							// get the FN element, and update the value
							if (node.getNodeName().equals("CL_FN")) {
								node.setTextContent(txtFN.getText().trim());
							}

							// get the LN element, and update the value
							if (node.getNodeName().equals("CL_LN")) {
								node.setTextContent(txtLN.getText().trim());
							}

							// get the STREET element, and update the value
							if (node.getNodeName().equals("CL_STREET")) {
								node.setTextContent(txtStreet.getText().trim());
							}

							// get the CITY element, and update the value
							if (node.getNodeName().equals("CL_CITY")) {
								node.setTextContent(txtCity.getText().trim());
							}

							// get the STATE element, and update the value
							if (node.getNodeName().equals("CL_STATE")) {
								node.setTextContent(cmbStates.getSelectedItem()
										.toString());
							}

							// get the ZIP element, and update the value
							if (node.getNodeName().equals("CL_ZIP")) {
								node.setTextContent(txtZip.getText().trim());
							}

							// get the PHONE element, and update the value
							if (node.getNodeName().equals("CL_PHONE")) {
								node.setTextContent(txtPhone.getText().trim());
							}

							// get the EMAIL element, and update the value
							if (node.getNodeName().equals("CL_EMAIL")) {
								node.setTextContent(txtEmail.getText().trim());
							}
							
							// updating the chef instructions
							
							// get the CHEf_INST element, and update the value
							/*if (node.getNodeName().equals("SCROLL")) {
								if(!tA.getText().equals(""))
									node.setTextContent(tA.getText().trim());								
								else
								    node.setTextContent("");
								   	
							}*/
							
							
							// updating the assign staff element
							
							// get the STAFF1 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN1")) {
								if(chkStaff1.isSelected())
									node.setTextContent(chkStaff1.getText().trim());
								else
									node.setTextContent("");
							}
							
							// get the STAFF2 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN2")) {
								if(chkStaff2.isSelected())
								    node.setTextContent(chkStaff2.getText().trim());
								else
									node.setTextContent("");
							} 
							
							// get the STAFF3 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN3")) {
								if(chkStaff3.isSelected())
								    node.setTextContent(chkStaff3.getText().trim());
								else
									node.setTextContent("");
							}
							
							// get the STAFF4 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN4")) {
								if(chkStaff4.isSelected())
								    node.setTextContent(chkStaff4.getText().trim());
								else
									node.setTextContent("");
							}
							
							// get the STAFF5 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN5")) {
								if(chkStaff5.isSelected())
								    node.setTextContent(chkStaff5.getText().trim());
								else
									node.setTextContent("");
							}
							
							// get the STAFF6 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN6")) {
								if(chkStaff6.isSelected())
								    node.setTextContent(chkStaff6.getText().trim());
								else
									node.setTextContent("");
							}
							
							// get the STAFF7 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN7")) {
								if(chkStaff7.isSelected())
      								node.setTextContent(chkStaff7.getText().trim());
								else
									node.setTextContent("");
							}
							
							// get the STAFF8 element, and update the value
							if (node.getNodeName().equals("ST_ASSIGN8")) {
								if(chkStaff8.isSelected())
								    node.setTextContent(chkStaff8.getText().trim());
								else
									node.setTextContent("");
							}

						}

					}
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xmlFile);
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}
}
