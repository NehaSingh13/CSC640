/*
 * @author: Piyush Agarwal(Client and Event)
 * @author: Stephen Cowie(Orders and Invoice)
 * @author: Neha Singh(Chef and Staff)
 */

package HappyMeals;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

public class UpdateEvent {

	public JFrame frame; // JFrame object
	private CommonMethods objCommon;

	public JComboBox<Object> cmbSelEvent;
	JTabbedPane tabPan;

	/**
	 * Create the application.
	 */
	public UpdateEvent() {
		objCommon = new CommonMethods();
		objCommon.getEvents();
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

		// Panel for Update Event
		final JPanel pnlUpdate = new JPanel();
		pnlUpdate.setLayout(null);

		// get the tab panel from common and add it to this panel
		tabPan = objCommon.getTabsUpdate();

		tabPan.setVisible(false);

		// set the chef text box values based on the orders as soon as the Chef
		// Tab is clicked
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent
						.getSource();
				selTab(sourceTabbedPane);
			}

			private void selTab(JTabbedPane sourceTabbedPane) {
				if (sourceTabbedPane.getTitleAt(
						sourceTabbedPane.getSelectedIndex()).equals(
						"Chef Instructions")) {
					CommonMethods.tA.setText("");
					CommonMethods.tA.append("ITEMS --> QUANTITY\n");
					for (int i = 0; i < 9; i++) {
						if (CommonMethods.drinkQnty[i] > 0) {
							CommonMethods.tA.append(String.format(
									"%s --> %s\n", objCommon.drinkTable
											.getValueAt(i, 0).toString(),
									CommonMethods.drinkQnty[i]));
						}
					}// find all ordered drinks and display

					for (int i = 0; i < 9; i++) {
						if (CommonMethods.snackQnty[i] > 0) {
							CommonMethods.tA.append(String.format(
									"%s --> %s\n", objCommon.snackTable
											.getValueAt(i, 0).toString(),
									CommonMethods.snackQnty[i]));
						}
					}// find all ordered snacks and display

					for (int i = 0; i < 9; i++) {
						if (CommonMethods.entreeQnty[i] > 0) {
							CommonMethods.tA.append(String.format(
									"%s --> %s\n", objCommon.entreeTable
											.getValueAt(i, 0).toString(),
									CommonMethods.entreeQnty[i]));
						}
					}// find all ordered entrees and display

					for (int i = 0; i < 9; i++) {
						if (CommonMethods.dessertQnty[i] > 0) {
							CommonMethods.tA.append(String.format(
									"%s --> %s\n", objCommon.dessertTable
											.getValueAt(i, 0).toString(),
									CommonMethods.dessertQnty[i]));
						}
					}// find all ordered desserts and display
				}
			}
		};
		tabPan.addChangeListener(changeListener);
		pnlUpdate.add(tabPan);

		// Update Event page Header
		JLabel lblUpdateHead = new JLabel("Update Event");
		lblUpdateHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateHead.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUpdateHead.setBounds(146, 0, 174, 27);
		pnlUpdate.add(lblUpdateHead);

		// Select Event label
		JLabel lblSelEvent = new JLabel("Select an Event to Update");
		lblSelEvent.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelEvent.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelEvent.setBounds(10, 30, 184, 27);
		pnlUpdate.add(lblSelEvent);

		final JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean makeUpdate = objCommon.Update(cmbSelEvent
						.getSelectedItem().toString());
				if (makeUpdate) {
					JOptionPane.showMessageDialog(null, "All Changes made to this event Saved!",
							"UPDATE", JOptionPane.INFORMATION_MESSAGE);
					
					cmbSelEvent.setSelectedIndex(0);
				}
			}
		});
		btnUpdate.setBounds(226, 333, 124, 27);
		btnUpdate.setEnabled(false);
		pnlUpdate.add(btnUpdate);
		
		
		// create button for cancelling an event and removing it from file
				final JButton btnCancelEvent = new JButton("CANCEL EVENT");
				btnCancelEvent.setForeground(Color.RED);
				btnCancelEvent.setBounds(10, 335, 136, 23);
				btnCancelEvent.setEnabled(false);
				btnCancelEvent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int reply = JOptionPane
								.showConfirmDialog(
										null,
										"Are you sure you want to CANCEL/DELETE the selected event from the system?\n Note: If YES, you will redirected back to the Main Page",
										"Confirm?", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							try {
								File xmlFile = new File("file.xml");
								DocumentBuilderFactory docFactory = DocumentBuilderFactory
										.newInstance();
								DocumentBuilder docBuilder = docFactory
										.newDocumentBuilder();
								Document doc = docBuilder.parse(xmlFile);

								NodeList nList = doc.getElementsByTagName("EVENT");
								for (int temp = 0; temp < nList.getLength(); temp++) {

									Node nNode = nList.item(temp);

									if (nNode.getNodeType() == Node.ELEMENT_NODE) {

										Element eElement = (Element) nNode;
										if (eElement.getAttribute("NAME").equals(
												cmbSelEvent.getSelectedItem())) {
											eElement.getParentNode().removeChild(
													eElement);

										}
									}

									// update the XML file
									TransformerFactory transformerFactory = TransformerFactory
											.newInstance();
									Transformer transformer = transformerFactory
											.newTransformer();
									DOMSource source = new DOMSource(doc);
									StreamResult result = new StreamResult(xmlFile);
									transformer.transform(source, result);
								}
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

						frame.setVisible(false);
						MainPage objMain = new MainPage();
						objMain.frame.setVisible(true);
					}

				});
				pnlUpdate.add(btnCancelEvent);
				
		
		// Combo-box for selecting event
		Collections.sort(CommonMethods.arrEvents);
		cmbSelEvent = new JComboBox<Object>(CommonMethods.arrEvents.toArray());
		cmbSelEvent.insertItemAt("Select...", 0);
		cmbSelEvent.setSelectedIndex(0);
		cmbSelEvent.setBounds(204, 34, 174, 20);

		// Display the tabs only when some event is selected from the list
		cmbSelEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cmbSelEvent.getSelectedItem().equals("Select...")) {
					tabPan.setVisible(true);
					populateTabs();
					updateVariables();
					btnUpdate.setEnabled(true);
					btnCancelEvent.setEnabled(true);
				} else {
					tabPan.setVisible(false);
					btnUpdate.setEnabled(false);
					btnCancelEvent.setEnabled(false);
				}
			}
		});
		pnlUpdate.add(cmbSelEvent);

		// Cancel button
		JButton btnCancel = new JButton("HOME");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objCommon.goHome(frame); // call the function from common to go
											// back to home page
			}
		});
		btnCancel.setBounds(354, 333, 124, 27);
		pnlUpdate.add(btnCancel);

		

		frame.getContentPane().add(pnlUpdate); // add panel to the frame

		
	}

	// Updates the variable for orders
	private void updateVariables() {

		try {

			File xmlFile = new File("file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			// To normalize the multi-line text in XML Nodes
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("EVENT");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getAttribute("NAME").equals(
							cmbSelEvent.getSelectedItem())) {

						CommonMethods.drinkQnty[0] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY0").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[1] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY1").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[2] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY2").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[3] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY3").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[4] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY4").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[5] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY5").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[6] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY6").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[7] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY7").item(0)
								.getTextContent());
						CommonMethods.drinkQnty[8] = Integer.parseInt(eElement
								.getElementsByTagName("DR_QNTY8").item(0)
								.getTextContent());
						CommonMethods.snackQnty[0] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY0").item(0)
								.getTextContent());
						CommonMethods.snackQnty[1] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY1").item(0)
								.getTextContent());
						CommonMethods.snackQnty[2] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY2").item(0)
								.getTextContent());
						CommonMethods.snackQnty[3] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY3").item(0)
								.getTextContent());
						CommonMethods.snackQnty[4] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY4").item(0)
								.getTextContent());
						CommonMethods.snackQnty[5] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY5").item(0)
								.getTextContent());
						CommonMethods.snackQnty[6] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY6").item(0)
								.getTextContent());
						CommonMethods.snackQnty[7] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY7").item(0)
								.getTextContent());
						CommonMethods.snackQnty[8] = Integer.parseInt(eElement
								.getElementsByTagName("SN_QNTY8").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[0] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY0").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[1] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY1").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[2] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY2").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[3] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY3").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[4] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY4").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[5] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY5").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[6] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY6").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[7] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY7").item(0)
								.getTextContent());
						CommonMethods.entreeQnty[8] = Integer.parseInt(eElement
								.getElementsByTagName("EN_QNTY8").item(0)
								.getTextContent());
						CommonMethods.dessertQnty[0] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY0")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[1] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY1")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[2] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY2")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[3] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY3")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[4] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY4")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[5] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY5")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[6] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY6")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[7] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY7")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[8] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY8")
										.item(0).getTextContent());
						CommonMethods.dessertQnty[9] = Integer
								.parseInt(eElement
										.getElementsByTagName("DE_QNTY9")
										.item(0).getTextContent());

					}// end if
				}// end if
			}// end for

		}// end try
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}// end updateVariables

	/**
	 * Populate tabs with data from XML file
	 */
	private void populateTabs() {

		try {

			File xmlFile = new File("file.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			// To normalize the multi-line text in XML Nodes
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("EVENT");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					if (eElement.getAttribute("NAME").equals(
							cmbSelEvent.getSelectedItem())) {

						/**
						 * We are populating the details for the Client and
						 * Event tab without checking the selected tab because
						 * we know that they are already being validated when
						 * saved and they are necessary fields for an Event to
						 * be registered into the system
						 */

						Component[] tabCompClient = ((JPanel) (tabPan
								.getComponents()[0])).getComponents();
						for (int i = 0; i < tabCompClient.length; i++) {
							if (tabCompClient[i].getName() != null) {
								switch (tabCompClient[i].getName()) {
								case "FN":
									((JTextField) tabCompClient[i])
											.setText(eElement
													.getElementsByTagName(
															"CL_FN").item(0)
													.getTextContent());
									break;
								case "LN":
									((JTextField) tabCompClient[i])
											.setText(eElement
													.getElementsByTagName(
															"CL_LN").item(0)
													.getTextContent());
									break;
								case "STREET":
									((JTextField) tabCompClient[i])
											.setText(eElement
													.getElementsByTagName(
															"CL_STREET")
													.item(0).getTextContent());
									break;
								case "CITY":
									((JTextField) tabCompClient[i])
											.setText(eElement
													.getElementsByTagName(
															"CL_CITY").item(0)
													.getTextContent());
									break;
								case "STATE":
									((JComboBox<?>) tabCompClient[i])
											.setSelectedItem(eElement
													.getElementsByTagName(
															"CL_STATE").item(0)
													.getTextContent());
									break;
								case "ZIP":
									((JTextField) tabCompClient[i])
											.setText(eElement
													.getElementsByTagName(
															"CL_ZIP").item(0)
													.getTextContent());
									break;
								case "PHONE":
									((JTextField) tabCompClient[i])
											.setText(eElement
													.getElementsByTagName(
															"CL_PHONE").item(0)
													.getTextContent());
									break;
								case "EMAIL":
									((JTextField) tabCompClient[i])
											.setText(eElement
													.getElementsByTagName(
															"CL_EMAIL").item(0)
													.getTextContent());
									break;
								default:
									System.out
											.println("Error in setting Client Name");
								}
							}
						}

						Component[] tabCompEvent = ((JPanel) (tabPan
								.getComponents()[1])).getComponents();
						for (int i = 0; i < tabCompEvent.length; i++) {
							if (tabCompEvent[i].getName() != null) {
								switch (tabCompEvent[i].getName()) {
								case "EVNAME":
									((JTextField) tabCompEvent[i])
											.setText((String) cmbSelEvent
													.getSelectedItem());
									CommonMethods.arrEvents
											.remove((String) cmbSelEvent
													.getSelectedItem()); // remove this event name
																			// from the arrEvents so
																			// that we can validate the
																			// duplicacy of events
									break;
								case "DATE":
									((JTextField) tabCompEvent[i])
											.setText(eElement
													.getElementsByTagName(
															"DATE").item(0)
													.getTextContent());

									CommonMethods.arrEventDates.remove(eElement
											.getElementsByTagName("DATE")
											.item(0).getTextContent()); // remove this date
																		// from the arrEventDates
																		// so that we can validate
																		// the duplicacy of dates
									break;
								case "TIME":
									((JTextField) tabCompEvent[i])
											.setText(eElement
													.getElementsByTagName(
															"TIME").item(0)
													.getTextContent());
									break;
								case "OB":
									if (eElement.getElementsByTagName("VENUE")
											.item(0).getTextContent()
											.equals("OUR BANQUET")) {
										((JRadioButton) tabCompEvent[i])
												.setSelected(true);
									}
									break;
								case "O":
									if (!eElement.getElementsByTagName("VENUE")
											.item(0).getTextContent()
											.equals("OUR BANQUET")) {
										((JRadioButton) tabCompEvent[i])
												.setSelected(true);
									}
									break;
								case "OTHER":
									if (!eElement.getElementsByTagName("VENUE")
											.item(0).getTextContent()
											.equals("OUR BANQUET")) {
										((JTextField) tabCompEvent[i])
												.setEnabled(true);
										((JTextField) tabCompEvent[i])
												.setText(eElement
														.getElementsByTagName(
																"VENUE")
														.item(0)
														.getTextContent());
									}
									break;
								case "ATTEND":
									((JTextField) tabCompEvent[i])
											.setText(eElement
													.getElementsByTagName(
															"ATTEND").item(0)
													.getTextContent());
									break;
								case "NOTE_SCROLL":
									((JTextArea) (((JScrollPane) tabCompEvent[i])
											.getViewport()).getView())
											.setText(eElement
													.getElementsByTagName(
															"NOTES").item(0)
													.getTextContent());
									break;
								default:
									System.out
											.println("Error in getting Event Details");
								}
							}
						}// end for loop tabCompEvent that goes through array of

						/** We are populating the details for the Assign Staff */
						Component[] tabCompStaff = ((JPanel) (tabPan
								.getComponents()[5])).getComponents();

						for (int i = 0; i < tabCompStaff.length; i++) {
							if (tabCompStaff[i].getName() != null) {
								switch (tabCompStaff[i].getName()) {
								case "S1":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN1").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN1").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								case "S2":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN2").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN2").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								case "S3":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN3").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN3").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								case "S4":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN4").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN4").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								case "S5":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN5").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN5").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								case "S6":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN6").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN6").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								case "S7":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN7").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN7").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								case "S8":
									if (eElement.getElementsByTagName(
											"ST_ASSIGN8").item(0) != null) {
										if (!eElement
												.getElementsByTagName(
														"ST_ASSIGN8").item(0)
												.getTextContent().equals("")) {
											((JCheckBox) tabCompStaff[i])
													.setSelected(true);
										}

									}
									break;
								}
							}
						}

						Component[] tabCompDrinks = ((Container) ((JTabbedPane) ((JPanel) (tabPan
								.getComponents()[2])).getComponents()[0])
								.getComponents()[0]).getComponents();
						// this gets Order panel
						// JTabbedPane from getTabsOrder call
						// component at 0 is Drinks
						// gets Drink components

						for (int i = 0; i < tabCompDrinks.length; i++) {
							if (tabCompDrinks[i].getName() != null) {
								switch (tabCompDrinks[i].getName()) {
								case "DRINK0":
									if (eElement.getElementsByTagName(
											"DR_QNTY0").item(0) != null) {
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY0")
														.item(0)
														.getTextContent());
									}
									break;
								case "DRINK1":
									if (eElement.getElementsByTagName(
											"DR_QNTY1").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY1")
														.item(0)
														.getTextContent());
									break;
								case "DRINK2":
									if (eElement.getElementsByTagName(
											"DR_QNTY2").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY2")
														.item(0)
														.getTextContent());
									break;
								case "DRINK3":
									if (eElement.getElementsByTagName(
											"DR_QNTY3").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY3")
														.item(0)
														.getTextContent());
									break;
								case "DRINK4":
									if (eElement.getElementsByTagName(
											"DR_QNTY4").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY4")
														.item(0)
														.getTextContent());
									break;
								case "DRINK5":
									if (eElement.getElementsByTagName(
											"DR_QNTY5").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY5")
														.item(0)
														.getTextContent());
									break;
								case "DRINK6":
									if (eElement.getElementsByTagName(
											"DR_QNTY6").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY6")
														.item(0)
														.getTextContent());
									break;
								case "DRINK7":
									if (eElement.getElementsByTagName(
											"DR_QNTY7").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY7")
														.item(0)
														.getTextContent());
									break;
								case "DRINK8":
									if (eElement.getElementsByTagName(
											"DR_QNTY8").item(0) != null)
										((JTextField) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_QNTY8")
														.item(0)
														.getTextContent());
									break;
								case "DR_SUB":
									if (eElement.getElementsByTagName("DR_SUB")
											.item(0) != null)
										((JLabel) tabCompDrinks[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_SUB")
														.item(0)
														.getTextContent());
									break;

								default:
									System.out
											.println("Error in getting Drink Details");
								}// end switch statement
							}// end if statement

						}// end for loop tabCompDrinks that goes through array
							// of drink data fields

						Component[] tabCompSnacks = ((Container) ((JTabbedPane) ((JPanel) (tabPan
								.getComponents()[2])).getComponents()[0])
								.getComponents()[1]).getComponents();
						// //this gets Order panel
						// //JTabbedPane from getTabsOrder call
						// //component at 1 is Snacks
						// //gets Snacks components

						for (int i = 0; i < tabCompSnacks.length; i++) {
							if (tabCompSnacks[i].getName() != null) {
								switch (tabCompSnacks[i].getName()) {
								case "SNACK0":
									if (eElement.getElementsByTagName(
											"SN_QNTY0").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY0")
														.item(0)
														.getTextContent());
									break;
								case "SNACK1":
									if (eElement.getElementsByTagName(
											"SN_QNTY1").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY1")
														.item(0)
														.getTextContent());
									break;
								case "SNACK2":
									if (eElement.getElementsByTagName(
											"SN_QNTY2").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY2")
														.item(0)
														.getTextContent());
									break;
								case "SNACK3":
									if (eElement.getElementsByTagName(
											"SN_QNTY3").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY3")
														.item(0)
														.getTextContent());
									break;
								case "SNACK4":
									if (eElement.getElementsByTagName(
											"SN_QNTY4").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY4")
														.item(0)
														.getTextContent());
									break;
								case "SNACK5":
									if (eElement.getElementsByTagName(
											"SN_QNTY5").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY5")
														.item(0)
														.getTextContent());
									break;
								case "SNACK6":
									if (eElement.getElementsByTagName(
											"SN_QNTY6").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY6")
														.item(0)
														.getTextContent());
									break;
								case "SNACK7":
									if (eElement.getElementsByTagName(
											"SN_QNTY7").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY7")
														.item(0)
														.getTextContent());
									break;
								case "SNACK8":
									if (eElement.getElementsByTagName(
											"SN_QNTY8").item(0) != null)
										((JTextField) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_QNTY8")
														.item(0)
														.getTextContent());
									break;
								case "SN_SUB":
									if (eElement.getElementsByTagName("SN_SUB")
											.item(0) != null)
										((JLabel) tabCompSnacks[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_SUB")
														.item(0)
														.getTextContent());
									break;

								default:
									System.out
											.println("Error in getting Snack Details");
								}// end switch statement
							}// end if statement

						}// end for loop

						Component[] tabCompEntrees = ((Container) ((JTabbedPane) ((JPanel) (tabPan
								.getComponents()[2])).getComponents()[0])
								.getComponents()[2]).getComponents();
						// this gets Order panel
						// JTabbedPane from getTabsOrder call
						// component at 2 is Entrees
						// gets Entree components

						for (int i = 0; i < tabCompEntrees.length; i++) {
							if (tabCompEntrees[i].getName() != null) {
								switch (tabCompEntrees[i].getName()) {
								case "ENTREE0":
									if (eElement.getElementsByTagName(
											"EN_QNTY0").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY0")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE1":
									if (eElement.getElementsByTagName(
											"EN_QNTY1").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY1")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE2":
									if (eElement.getElementsByTagName(
											"EN_QNTY2").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY2")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE3":
									if (eElement.getElementsByTagName(
											"EN_QNTY3").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY3")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE4":
									if (eElement.getElementsByTagName(
											"EN_QNTY4").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY4")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE5":
									if (eElement.getElementsByTagName(
											"EN_QNTY5").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY5")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE6":
									if (eElement.getElementsByTagName(
											"EN_QNTY6").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY6")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE7":
									if (eElement.getElementsByTagName(
											"EN_QNTY7").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY7")
														.item(0)
														.getTextContent());
									break;
								case "ENTREE8":
									if (eElement.getElementsByTagName(
											"EN_QNTY8").item(0) != null)
										((JTextField) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_QNTY8")
														.item(0)
														.getTextContent());
									break;
								case "EN_SUB":
									if (eElement.getElementsByTagName("EN_SUB")
											.item(0) != null)
										((JLabel) tabCompEntrees[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_SUB")
														.item(0)
														.getTextContent());
									break;
								default:
									System.out
											.println("Error in getting Entree Details");
								}// end switch statement
							}// end if statement

						}// end for loop

						Component[] tabCompDesserts = ((Container) ((JTabbedPane) ((JPanel) (tabPan
								.getComponents()[2])).getComponents()[0])
								.getComponents()[3]).getComponents();
						// this gets Order panel
						// JTabbedPane from getTabsOrder call
						// component at 3 is Desserts
						// gets Dessert components

						for (int i = 0; i < tabCompDesserts.length; i++) {
							if (tabCompDesserts[i].getName() != null) {
								switch (tabCompDesserts[i].getName()) {
								case "DESSERT0":
									if (eElement.getElementsByTagName(
											"DE_QNTY0").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY0")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT1":
									if (eElement.getElementsByTagName(
											"DE_QNTY1").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY1")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT2":
									if (eElement.getElementsByTagName(
											"DE_QNTY2").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY2")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT3":
									if (eElement.getElementsByTagName(
											"DE_QNTY3").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY3")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT4":
									if (eElement.getElementsByTagName(
											"DE_QNTY4").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY4")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT5":
									if (eElement.getElementsByTagName(
											"DE_QNTY5").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY5")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT6":
									if (eElement.getElementsByTagName(
											"DE_QNTY6").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY6")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT7":
									if (eElement.getElementsByTagName(
											"DE_QNTY7").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY7")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT8":
									if (eElement.getElementsByTagName(
											"DE_QNTY8").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY8")
														.item(0)
														.getTextContent());
									break;
								case "DESSERT9":
									if (eElement.getElementsByTagName(
											"DE_QNTY9").item(0) != null)
										((JTextField) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_QNTY9")
														.item(0)
														.getTextContent());
									break;
								case "DE_SUB":
									if (eElement.getElementsByTagName("DE_SUB")
											.item(0) != null)
										((JLabel) tabCompDesserts[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_SUB")
														.item(0)
														.getTextContent());
									break;
								default:
									System.out
											.println("Error in getting Dessert Details");
								}// end switch statement
							}// end if statement

						}// end for loop

						Component[] tabCompInvoice = ((JPanel) (tabPan
								.getComponents()[3])).getComponents();
						for (int i = 0; i < tabCompInvoice.length; i++) {
							if (tabCompInvoice[i].getName() != null) {
								switch (tabCompInvoice[i].getName()) {
								case "DR_SUB_INV":
									if (eElement.getElementsByTagName(
											"DR_SUB_INV").item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"DR_SUB_INV")
														.item(0)
														.getTextContent());
									break;

								case "SN_SUB_INV":
									if (eElement.getElementsByTagName(
											"SN_SUB_INV").item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"SN_SUB_INV")
														.item(0)
														.getTextContent());
									break;

								case "EN_SUB_INV":
									if (eElement.getElementsByTagName(
											"EN_SUB_INV").item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"EN_SUB_INV")
														.item(0)
														.getTextContent());
									break;

								case "DE_SUB_INV":
									if (eElement.getElementsByTagName(
											"DE_SUB_INV").item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"DE_SUB_INV")
														.item(0)
														.getTextContent());
									break;

								case "TXV_SUB":
									if (eElement
											.getElementsByTagName("TXV_SUB")
											.item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"TXV_SUB")
														.item(0)
														.getTextContent());
									break;

								case "LV_SUB":
									if (eElement.getElementsByTagName("LV_SUB")
											.item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"LV_SUB")
														.item(0)
														.getTextContent());
									break;

								case "BAL_SUB":
									if (eElement
											.getElementsByTagName("BAL_SUB")
											.item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"BAL_SUB")
														.item(0)
														.getTextContent());
									break;

								case "BAL_REM_SUB":
									if (eElement.getElementsByTagName(
											"BAL_REM_SUB").item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"BAL_REM_SUB")
														.item(0)
														.getTextContent());
									break;

								case "TFV_SUB":
									if (eElement
											.getElementsByTagName("TFV_SUB")
											.item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"TFV_SUB")
														.item(0)
														.getTextContent());
									break;

								case "APFV_SUB":
									if (eElement.getElementsByTagName(
											"APFV_SUB").item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"APFV_SUB")
														.item(0)
														.getTextContent());
									break;

								case "ADFV_SUB":
									if (eElement.getElementsByTagName(
											"ADFV_SUB").item(0) != null)
										((JLabel) tabCompInvoice[i])
												.setText(eElement
														.getElementsByTagName(
																"ADFV_SUB")
														.item(0)
														.getTextContent());
									break;

								default:
									System.out
											.println("Error in getting Invoice Details");
								}// end switch statement
							}// end if statement
						}// end for loop
					}// if the node element of given name is found
				}// if the element is a node element
			}// end for loop to find all elements by tag name = selected name
		}// end try
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}// end populateTabs method

}// end UpdateEvent class
