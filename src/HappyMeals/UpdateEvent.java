/*
 * @author: Piyush Agarwal, Stephen Cowie
 */

package HappyMeals;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UpdateEvent {

	Color c = new Color(255,0,0);
	
	public JFrame frame; //JFrame object
	private CommonMethods objCommon;
	
	JComboBox<String> cmbSelEvent;
	JTabbedPane tabPan;
	HashMap<String,String> ClientDet,EventDet,DrinkDet,SnackDet,
	EntreeDet, DessertDet;
	ArrayList<HashMap<String,String>> arrTemp;
	
	/**
	 * Create the application.
	 */
	public UpdateEvent(CommonMethods obj) {
		objCommon = obj;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(500, 200, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					
		//Panel for Update Event
		final JPanel pnlUpdate = new JPanel();		
		pnlUpdate.setLayout(null);
		
		
		//get the tab panel from common and add it to this panel
		tabPan = objCommon.getTabsUpdate();
		tabPan.setVisible(false);
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        //System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(sourceTabbedPane.getSelectedIndex()));
		        selTab(sourceTabbedPane);
		      }
		    };
		tabPan.addChangeListener(changeListener);
		pnlUpdate.add(tabPan);
				
		
		//Update Event page Header
		JLabel lblUpdateHead = new JLabel("Update Event");
		lblUpdateHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateHead.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUpdateHead.setBounds(146, 0, 174, 27);
		pnlUpdate.add(lblUpdateHead);
	
		//Select Event label
		JLabel lblSelEvent = new JLabel("Select an Event to Update");
		lblSelEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelEvent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelEvent.setBounds(10, 30, 184, 27);
		pnlUpdate.add(lblSelEvent);
		
		//Combo-box for selecting event
		cmbSelEvent = new JComboBox<String>();
		cmbSelEvent.setBounds(204, 34, 174, 20);
		
		
		 
		Set set = CommonMethods.hmEvents.entrySet(); //get the set of values in the HashMap
		
		Iterator i = set.iterator(); //create an iterator for the set
		
		// Add elements to the combo box
		cmbSelEvent.addItem("Select...");
		while(i.hasNext()) { 
			Map.Entry me = (Map.Entry)i.next(); 
			cmbSelEvent.addItem((String)me.getKey());
		}
		
		
		//Display the tabs only when some event is selected from the list
		cmbSelEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbSelEvent.getSelectedItem().equals("Select...")){
					tabPan.setVisible(true);
					arrTemp = CommonMethods.hmEvents.get(cmbSelEvent.getSelectedItem());
					ClientDet = new HashMap<String, String>();
					ClientDet = arrTemp.get(0);
					
					if(tabPan.getTitleAt(tabPan.getSelectedIndex()).equals("Client")){
						
						Component[] tabComp = ((JPanel)(tabPan.getComponents()[0])).getComponents();
						for(int i=0; i< tabComp.length;i++)
						{
							if(tabComp[i].getName() != null){
								switch(tabComp[i].getName()){
									case "FN": ((JTextField) tabComp[i]).setText(ClientDet.get("FN")); break;
									case "LN": ((JTextField) tabComp[i]).setText(ClientDet.get("LN")); break;
									case "STREET": ((JTextField) tabComp[i]).setText(ClientDet.get("STREET")); break;
									case "CITY": ((JTextField) tabComp[i]).setText(ClientDet.get("CITY")); break;
									case "STATE": ((JComboBox) tabComp[i]).setSelectedItem((ClientDet.get("STATE"))); break;
									case "ZIP": ((JTextField) tabComp[i]).setText(ClientDet.get("ZIP")); break;
									case "PHONE": ((JTextField) tabComp[i]).setText(ClientDet.get("PHONE")); break;
									case "EMAIL": ((JTextField) tabComp[i]).setText(ClientDet.get("EMAIL")); break;
									default: System.out.println("Error in setting Client Name");
								}
							}
							
						}
					}
					
				
				}
				else{
					tabPan.setVisible(false);
				}
			}
		});		
		pnlUpdate.add(cmbSelEvent);
		
		
		//Cancel button
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objCommon.goHome(frame); //call the function from common to go back to home page
			}
		});
		btnCancel.setBounds(354, 333, 124, 27);
		pnlUpdate.add(btnCancel);
		
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<HashMap<String,String>> tempList = objCommon.Update(objCommon.arrEventDetails);
				
				CommonMethods.hmEvents.put(((HashMap<String, String>)tempList.get(1)).get("EVNAME"), tempList);
				
			}
		});
		btnUpdate.setBounds(226, 333, 124, 27);
		pnlUpdate.add(btnUpdate);
		
		frame.getContentPane().add(pnlUpdate); //add panel to the frame
		
		JButton btnCancelEvent = new JButton("CANCEL EVENT");
		btnCancelEvent.setForeground(Color.RED);
		btnCancelEvent.setBounds(10, 335, 136, 23);
		btnCancelEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to CANCEL/DELETE the selected event from the system?\n Note: If YES, you will redirected back to the Main Page", "Confirm?", JOptionPane.YES_NO_OPTION);
			    if (reply == JOptionPane.YES_OPTION) {
			    	CommonMethods.hmEvents.remove(cmbSelEvent.getSelectedItem());
			    	frame.setVisible(false);
			    	MainPage objMain = new MainPage();
			    	objMain.frame.setVisible(true);
			    }
								
			}
		});
		pnlUpdate.add(btnCancelEvent);
	}
	
	private void selTab(JTabbedPane selectedTab){
		arrTemp = CommonMethods.hmEvents.get(cmbSelEvent.getSelectedItem());
		//System.out.println(arrTemp.get(0));
		//System.out.println(arrTemp.get(1));
		ClientDet = new HashMap<String, String>();
		EventDet = new HashMap<String, String>();
		DrinkDet = new HashMap<String, String>();
		SnackDet = new HashMap<String, String>();
		EntreeDet = new HashMap<String, String>();
		DessertDet = new HashMap<String, String>();
		
		if(arrTemp.size() == 1){ ClientDet = arrTemp.get(0);}
		else if(arrTemp.size() == 2){ 
			ClientDet = arrTemp.get(0);
			EventDet = arrTemp.get(1);
		}
		else if(arrTemp.size() == 3){
			ClientDet = arrTemp.get(0);
			EventDet = arrTemp.get(1);
			DrinkDet = arrTemp.get(2);
		}
		else if(arrTemp.size() == 4){
			ClientDet = arrTemp.get(0);
			EventDet = arrTemp.get(1);
			DrinkDet = arrTemp.get(2);
			SnackDet = arrTemp.get(3);
		}
		else if(arrTemp.size() == 5){
			ClientDet = arrTemp.get(0);
			EventDet = arrTemp.get(1);
			DrinkDet = arrTemp.get(2);
			SnackDet = arrTemp.get(3);
			EntreeDet = arrTemp.get(4);
		}
		else if(arrTemp.size() == 6){
			ClientDet = arrTemp.get(0);
			EventDet = arrTemp.get(1);
			DrinkDet = arrTemp.get(2);
			SnackDet = arrTemp.get(3);
			EntreeDet = arrTemp.get(4);
			DessertDet = arrTemp.get(5);
		}
		
		
		if(selectedTab.getTitleAt(selectedTab.getSelectedIndex()).equals("Client")){
			
			Component[] tabComp = ((JPanel)(tabPan.getComponents()[0])).getComponents();
			for(int i=0; i< tabComp.length;i++)
			{
				
				if(tabComp[i].getName() != null){
					
					switch(tabComp[i].getName()){
						case "FN": ((JTextField) tabComp[i]).setText(ClientDet.get("FN")); break;
						case "LN": ((JTextField) tabComp[i]).setText(ClientDet.get("LN")); break;
						case "STREET": ((JTextField) tabComp[i]).setText(ClientDet.get("STREET")); break;
						case "CITY": ((JTextField) tabComp[i]).setText(ClientDet.get("CITY")); break;
						case "STATE": ((JComboBox<?>) tabComp[i]).setSelectedItem((ClientDet.get("STATE"))); break;
						case "ZIP": ((JTextField) tabComp[i]).setText(ClientDet.get("ZIP")); break;
						case "PHONE": ((JTextField) tabComp[i]).setText(ClientDet.get("PHONE")); break;
						case "EMAIL": ((JTextField) tabComp[i]).setText(ClientDet.get("EMAIL")); break;
						default: System.out.println("Error in getting Client Details");
					}
				}
				
			}
		}
		
		else if(selectedTab.getTitleAt(selectedTab.getSelectedIndex()).equals("Event")){
			
			Component[] tabComp = ((JPanel)(tabPan.getComponents()[1])).getComponents();
			
			for(int i=0; i< tabComp.length;i++)
			{
				
				if(tabComp[i].getName() != null){
					//System.out.println("Event: "+tabComp[i]);
					switch(tabComp[i].getName()){
						case "EVNAME":((JTextField) tabComp[i]).setText((String)cmbSelEvent.getSelectedItem());break;
						case "DATE": ((JTextField) tabComp[i]).setText(EventDet.get("DATE")); break;
						case "TIME": ((JTextField) tabComp[i]).setText(EventDet.get("TIME")); break;
						case "OB": 	if(EventDet.get("VENUE") == "OUR BANQUET"){
										((JRadioButton)tabComp[i]).setSelected(true);
									}break;
						case "O":	if(EventDet.get("VENUE") != "OUR BANQUET"){
									((JRadioButton)tabComp[i]).setSelected(true);
								}break;
						case "OTHER":if(EventDet.get("VENUE") != "OUR BANQUET"){
									((JTextField)tabComp[i]).setEnabled(true);
									((JTextField)tabComp[i]).setText(EventDet.get("VENUE"));
								}break;
						case "ATTEND": ((JTextField) tabComp[i]).setText(EventDet.get("ATTEND")); break;
						case "NOTE_SCROLL": ((JTextArea) (((JScrollPane) tabComp[i]).getViewport()).getView()).setText(EventDet.get("NOTES")); break;
						default: System.out.println("Error in getting Event Details");
					}
				}
				
			}
		}//end else if for event
		
		else if(selectedTab.getTitleAt(selectedTab.getSelectedIndex()).equals("Drinks")){
			
			Component[] tabComp = ((JPanel)(tabPan.getComponents()[2])).getComponents();
			
			for(int i=0; i< tabComp.length;i++)
			{
				
				if(tabComp[i].getName() != null){
					//System.out.println("Drinks: "+tabComp[i]);
					switch(tabComp[i].getName()){
						case "DRINK0": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK0")); break;
						case "DRINK1": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK1")); break;
						case "DRINK2": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK2")); break;
						case "DRINK3": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK3")); break;
						case "DRINK4": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK4")); break;
						case "DRINK5": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK5")); break;
						case "DRINK6": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK6")); break;
						case "DRINK7": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK7")); break;
						case "DRINK8": ((JTextField) tabComp[i]).setText(DrinkDet.get("DRINK8")); break;
						default: System.out.println("Error in getting Drink Details");
					}
				}
				
			}
		}//end else if for drinks
		
		else if(selectedTab.getTitleAt(selectedTab.getSelectedIndex()).equals("Snacks")){
			
			Component[] tabComp = ((JPanel)(tabPan.getComponents()[3])).getComponents();
			
			for(int i=0; i< tabComp.length;i++)
			{
				
				if(tabComp[i].getName() != null){
					//System.out.println("Snacks: "+tabComp[i]);
					switch(tabComp[i].getName()){
						case "SNACK0": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK0")); break;
						case "SNACK1": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK1")); break;
						case "SNACK2": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK2")); break;
						case "SNACK3": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK3")); break;
						case "SNACK4": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK4")); break;
						case "SNACK5": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK5")); break;
						case "SNACK6": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK6")); break;
						case "SNACK7": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK7")); break;
						case "SNACK8": ((JTextField) tabComp[i]).setText(SnackDet.get("SNACK8")); break;
						default: System.out.println("Error in getting Snack Details");
					}
				}
				
			}
		}//end else if for snacks
		
		else if(selectedTab.getTitleAt(selectedTab.getSelectedIndex()).equals("Entrees")){
			
			Component[] tabComp = ((JPanel)(tabPan.getComponents()[4])).getComponents();
			
			for(int i=0; i< tabComp.length;i++)
			{
				
				if(tabComp[i].getName() != null){
					//System.out.println("Entrees: "+tabComp[i]);
					switch(tabComp[i].getName()){
						case "ENTREE0": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE0")); break;
						case "ENTREE1": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE1")); break;
						case "ENTREE2": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE2")); break;
						case "ENTREE3": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE3")); break;
						case "ENTREE4": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE4")); break;
						case "ENTREE5": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE5")); break;
						case "ENTREE6": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE6")); break;
						case "ENTREE7": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE7")); break;
						case "ENTREE8": ((JTextField) tabComp[i]).setText(EntreeDet.get("ENTREE8")); break;
						default: System.out.println("Error in getting Entree Details");
					}
				}
				
			}
		}//end else if for entrees
		
		else if(selectedTab.getTitleAt(selectedTab.getSelectedIndex()).equals("Desserts")){
			
			Component[] tabComp = ((JPanel)(tabPan.getComponents()[5])).getComponents();
			
			for(int i=0; i< tabComp.length;i++)
			{
				
				if(tabComp[i].getName() != null){
					//System.out.println("Desserts: "+tabComp[i]);
					switch(tabComp[i].getName()){
						case "DESSERT0": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT0")); break;
						case "DESSERT1": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT1")); break;
						case "DESSERT2": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT2")); break;
						case "DESSERT3": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT3")); break;
						case "DESSERT4": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT4")); break;
						case "DESSERT5": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT5")); break;
						case "DESSERT6": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT6")); break;
						case "DESSERT7": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT7")); break;
						case "DESSERT8": ((JTextField) tabComp[i]).setText(DessertDet.get("DESSERT8")); break;
						default: System.out.println("Error in getting Dessert Details");
					}
				}
				
			}
		}//end else if for desserts
		
	}//end selTab method
}//end UpdateEvent class
