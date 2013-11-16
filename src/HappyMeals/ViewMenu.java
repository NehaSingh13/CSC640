/*
 * @author: Stephen Cowie
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



public class ViewMenu {

	public JFrame frame; //JFrame object
	CommonMethods objCommon = new CommonMethods();	
		

	/**
	 * Create the application.
	 */
	public ViewMenu() {
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
		
		pnlAdd.add(objCommon.getTabsMenu()); //add the tab pane to the panel
		
		//Header Label		
		JLabel lblAddHead = new JLabel("Menu Items");
		lblAddHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddHead.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddHead.setBounds(146, 12, 174, 27);
		pnlAdd.add(lblAddHead);
	
				
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
