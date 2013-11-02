/*
 * @author: Piyush Agarwal
 */

package HappyMeals;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainPage {

	public JFrame frame;
	private static MainPage windowMain = new MainPage();
	public static CommonMethods objCommon;
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
	 * @wbp.parser.entryPoint
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
		
		//create a new label for Heading and add it to the panel
		JLabel lblEvents = new JLabel("Event Management");
		lblEvents.setBounds(30, 70, 185, 40);
		lblEvents.setHorizontalAlignment(SwingConstants.CENTER);
		lblEvents.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlMain.add(lblEvents);
		
		//create a new button for Add Events and add it to the panel
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
		btnAdd.setBounds(40, 115, 160, 30);
		pnlMain.add(btnAdd);
		
		//create a new button for Update Events and add it to the panel
		JButton btnUpdate = new JButton("Update Event");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
					
				UpdateEvent windowUpdate = new UpdateEvent(objCommon);
				windowUpdate.frame.setVisible(true);
				windowMain.frame.setVisible(false);
							
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnUpdate.setBounds(40, 156, 160, 30);
		if(CommonMethods.hmEvents.isEmpty())
			btnUpdate.setEnabled(false);		
		else
			btnUpdate.setEnabled(true);
		pnlMain.add(btnUpdate);
		
		//create a new button for View Events and add it to the panel
		JButton btnView = new JButton("View Events");
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewEvents eventsView = new ViewEvents();
				eventsView.frame.setVisible(true);
				windowMain.frame.setVisible(false);
			}
		});
		
		btnView.setBounds(40, 197, 160, 30);
		pnlMain.add(btnView);
		
		//create a new label for Heading and add it to the panel
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setBounds(296, 70, 98, 40);
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		pnlMain.add(lblMenu);
		
		//create a new button for View Menu and add it to the panel
		JButton btnViewMenu = new JButton("View Menu");
		btnViewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewMenu menuView = new ViewMenu();
				menuView.frame.setVisible(true);
				windowMain.frame.setVisible(false);
			}
		});
		btnViewMenu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViewMenu.setBounds(269, 115, 160, 30);
		pnlMain.add(btnViewMenu);
		
		
		JLabel lblHeading = new JLabel("Happy Meals");
		lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblHeading.setBounds(130, 11, 205, 40);
		pnlMain.add(lblHeading);
		
		
		//add the panel to the frame and make the frame visible
		frame.getContentPane().add(pnlMain);		
	}
}
