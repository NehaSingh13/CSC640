/*
 * @author: Neha Singh 
 */

package HappyMeals;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class ViewEvents {

	public JFrame frame; //JFrame object
	CommonMethods objCommon = new CommonMethods();	
		

	/**
	 * Create the application.
	 */
	public ViewEvents() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(500, 200, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		JPanel pnlAdd = new JPanel();		
		pnlAdd.setLayout(null);	
		
		

		//Header Label		
		JLabel lblAddHead = new JLabel("Scheduled Events");
		lblAddHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddHead.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddHead.setBounds(146, 12, 220, 27);
		pnlAdd.add(lblAddHead);
		
		Set set = CommonMethods.hmEvents.entrySet(); //get the set of values in the HashMap
		
		Iterator i = set.iterator(); //create an iterator for the set
		
		ArrayList<HashMap<String,String>> arrTemp = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> EventDet = new HashMap<String, String>();
		
		
		int y=75;
		
		if(!set.isEmpty()){
			while(i.hasNext()) { 
				Map.Entry me = (Map.Entry)i.next();
				arrTemp = CommonMethods.hmEvents.get(me.getKey());
				EventDet = arrTemp.get(1);
				JLabel event = new JLabel((String)me.getKey()+", "+EventDet.get("DATE"));
				event.setBounds(5,y,200,25);
				y+=25;
				pnlAdd.add(event);
			}
		}
		else{
			JLabel event = new JLabel("No Scheduled Events as of now. Look for some new Cients...");
			event.setBounds(5,75,400,25);
			event.setForeground(Color.BLUE);
			pnlAdd.add(event);
		}
		
		
		
		
				
		//Home Button
		JButton btnMainPage = new JButton("HOME");
		btnMainPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
					objCommon.goHome2(frame);
		        }   
			});		
		
		btnMainPage.setBounds(190, 333, 124, 27);
		pnlAdd.add(btnMainPage);
		
		
		frame.getContentPane().add(pnlAdd); //add the panel to the frame
	}//end initialization
		
}//end View Menu class
