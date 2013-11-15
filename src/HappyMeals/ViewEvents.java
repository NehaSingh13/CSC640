/*
 * @author: Neha Singh 
 */

package HappyMeals;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;

public class ViewEvents {

	public JFrame frame; // JFrame object
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

		// Header Label
		JLabel lblAddHead = new JLabel("Scheduled Events");
		lblAddHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddHead.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblAddHead.setBounds(146, 12, 220, 27);
		pnlAdd.add(lblAddHead);

		int y = 75; // y-axis of 1st event being displayed
		objCommon.getEvents(); // get Events from the XML file
		if (!CommonMethods.arrEvents.isEmpty()) {
			for (int i = 0; i < CommonMethods.arrEvents.size(); i++) {
				JLabel event = new JLabel(CommonMethods.arrEvents.get(i) + ", "
						+ CommonMethods.arrEventDates.get(i)); // both index as
																// i because
																// the length of
																// both lists
																// will be same
				event.setBounds(5, y, 200, 25);
				y += 25;
				pnlAdd.add(event);
			}
		} else {
			JLabel event = new JLabel("No Scheduled Events as of now. Look for some new Cients...");
			event.setBounds(5, 75, 400, 25);
			event.setForeground(Color.BLUE);
			pnlAdd.add(event);
		}

		JCalendar eventCalendar = new JCalendar();
		eventCalendar.setBounds(250, 120, 200, 200);
		pnlAdd.add(eventCalendar);
		
		int i = eventCalendar.getCalendar().get(Calendar.DAY_OF_MONTH);
		
		// Home Button
		JButton btnMainPage = new JButton("HOME");
		btnMainPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				objCommon.goHome2(frame);
			}
		});

		btnMainPage.setBounds(190, 333, 124, 27);
		pnlAdd.add(btnMainPage);

		frame.getContentPane().add(pnlAdd); // add the panel to the frame
	}// end initialization

}// end View Menu class
