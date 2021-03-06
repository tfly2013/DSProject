/***
 * Subject                      Distributed System
 * Author: 						Bofan Jin, Fei Tang, Kimple Ke, Roger Li
 * Date of last modification: 	31/05/2015
 ***/

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import master.Master;

/**
 * Class for adding a worker, which allows details to be specified
 * for a new worker. 
 */
public class AddWorkerDialog extends JDialog {

	private static final long serialVersionUID = 3206590606836409332L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAddress;
	private JTextField txtPort;
	private JLabel lblAddress;
	private JLabel lblPort;

	private String address;
	private int port;

	/**
	 * Create the dialog.
	 * 
	 * @param master
	 */
	public AddWorkerDialog(Master master) {
		setTitle("Add a Worker");
		setBounds(100, 100, 191, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				// New label for address. 
				lblAddress = new JLabel("Address:");
				lblAddress.setPreferredSize(new Dimension(60, 15));
				panel.add(lblAddress);
			}
			{
				// Text field for address. 
				txtAddress = new JTextField();
				lblAddress.setLabelFor(txtAddress);
				txtAddress.setColumns(10);
				panel.add(txtAddress);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				// Label for port. 
				lblPort = new JLabel("Port:");
				lblPort.setPreferredSize(new Dimension(60, 15));
				panel.add(lblPort);
			}
			{
				// Text field for port. 
				txtPort = new JTextField();
				lblPort.setLabelFor(txtPort);
				txtPort.setColumns(10);
				panel.add(txtPort);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						address = txtAddress.getText();
						try {
							port = Integer.parseInt(txtPort.getText());
						} catch (NumberFormatException ex) {
							// Error message if invalid port. 
							JOptionPane.showMessageDialog(AddWorkerDialog.this,
									"Port is required and must be a number.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}
						if (address.trim().isEmpty()){
							// Error message if no address provided. 
							JOptionPane.showMessageDialog(AddWorkerDialog.this,
									"Address is required.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						else if (port < 0 && port > 65535){
							// Error message if invalid port. 
							JOptionPane.showMessageDialog(AddWorkerDialog.this,
									"Port must between 0 and 65535.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
						else {
							master.addWorker(address, port);
							setVisible(false);
							dispose();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				// Cancel button.
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
