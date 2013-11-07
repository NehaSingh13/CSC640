/*
 * @author: Piyush Agarwal, Stephen Cowie
 */

package HappyMeals;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class AddEvent {

	public JFrame frame; //JFrame object
	CommonMethods objCommon;	
		

	/**
	 * Create the application.
	 */
	public AddEvent(CommonMethods obj) {
		objCommon = obj;
		//System.out.println(objCommon);
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
		
		
		pnlAdd.add(objCommon.getTabs()); //add the tab pane to the panel
	
		
		JLabel lblAddHead = new JLabel("Add Event");
		lblAddHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddHead.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddHead.setBounds(146, 12, 174, 27);
		pnlAdd.add(lblAddHead);
		
				
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
					objCommon.goHome(frame);
		        }   
			});		
		
		btnCancel.setBounds(360, 333, 124, 27);
		pnlAdd.add(btnCancel);
		
		JButton btnNext = new JButton("SAVE");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(objCommon.Add()){
					//CommonMethods.hmEvents.put(((HashMap<String, String>)tempList.get(1)).get("EVNAME"), tempList); //hard-coded(test)
					frame.setVisible(false);
				    MainPage objMain = new MainPage();
				    objMain.frame.setVisible(true);
				}
			}
		});
		btnNext.setBounds(226, 333, 124, 27);
		pnlAdd.add(btnNext);
		
		frame.getContentPane().add(pnlAdd); //add the panel to the frame
	}
		
}
