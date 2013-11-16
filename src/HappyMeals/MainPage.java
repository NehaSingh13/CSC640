/*
 * @author: Piyush Agarwal
 * @author: Neha Singh (Scheduled Events display)
 */

package HappyMeals;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainPage {

	public JFrame frame;
	private static MainPage windowMain = new MainPage();
	public CommonMethods objCommon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					windowMain.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 * 
	 */
	public MainPage() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 200, 500, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//create a new panel
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(null);

		// create a new button for Add Events and add it to the panel
		JButton btnAdd = new JButton("Add Event");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				objCommon = new CommonMethods();
				AddEvent windowAdd = new AddEvent(objCommon);
				windowAdd.frame.setVisible(true);
				windowMain.frame.setVisible(false);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAdd.setBounds(20, 80, 120, 30);
		pnlMain.add(btnAdd);

		// create a new button for Update Events and add it to the panel
		JButton btnUpdate = new JButton("Update Event");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				UpdateEvent windowUpdate = new UpdateEvent();
				windowUpdate.frame.setVisible(true);
				windowMain.frame.setVisible(false);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(160, 80, 150, 30);
		objCommon = new CommonMethods();
		objCommon.getEvents();
		if (CommonMethods.arrEvents.isEmpty())
			btnUpdate.setEnabled(false);
		else
			btnUpdate.setEnabled(true);
		pnlMain.add(btnUpdate);


		//Create and populate label for displaying scheduled events
		JLabel lblView = new JLabel();
		lblView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblView.setBounds(10,130,470,130);
		lblView.setBorder(BorderFactory.createEtchedBorder());
		pnlMain.add(lblView);
		
		JLabel lblViewHead = new JLabel();
		lblViewHead.setText("Scheduled Events");
		lblViewHead.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblViewHead.setBounds(150, 0, 150, 30);
		lblView.add(lblViewHead);
		
		String event = "<html><body>";
		objCommon.getEvents(); // get Events from the XML file
		if (!CommonMethods.arrEvents.isEmpty()) {
			for (int i = 0; i < CommonMethods.arrEvents.size(); i++) {
				event += CommonMethods.arrEvents.get(i) + "&nbsp;|&nbsp;" + CommonMethods.arrEventDates.get(i) +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				if(i == 2 || i == 5 || i == 8) {//if there are three events in a row, go to the next row
					event+= "<br>";
				}
			}
			event += "</body></html>";
		} else {
			event = "No Scheduled Events as of now. Look for some new Cients...";
		}

		JLabel lblEvent = new JLabel();
		lblEvent.setText(event.trim());
		lblEvent.setForeground(Color.BLUE);
		lblEvent.setBounds(10, 5, 450, 120);
		lblView.add(lblEvent);
		
		
		// create a new button for Viewing Menu and add it to the panel
		JButton btnViewMenu = new JButton("View Menu");
		btnViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewMenu menuView = new ViewMenu();
				menuView.frame.setVisible(true);
				windowMain.frame.setVisible(false);
			}
		});
		btnViewMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewMenu.setBounds(330, 80, 130, 30);
		pnlMain.add(btnViewMenu);

		//create label for Header
		JLabel lblHeading = new JLabel("Happy Meals");
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHeading.setBounds(130, 11, 205, 40);
		pnlMain.add(lblHeading);

		
		// add the panel to the frame and make the frame visible
		frame.getContentPane().add(pnlMain);
	}
}
