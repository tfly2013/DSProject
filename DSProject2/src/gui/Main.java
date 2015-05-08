package gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import master.Master;

public class Main {

	private JFrame frame;
	private Master master;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

	/**
	 * Create the application.
	 */
	public Main() {
		master = new Master();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAddAJob = new JButton("Add a Job");
		btnAddAJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddJobDialog dialog = new AddJobDialog(master);
				dialog.pack();
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);				
			}
		});
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(btnAddAJob);
		
		JButton button = new JButton("Add a Worker");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddWorkerDialog dialog = new AddWorkerDialog(master);
				dialog.pack();
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);		
			}
		});
		frame.getContentPane().add(button);
	}

}
