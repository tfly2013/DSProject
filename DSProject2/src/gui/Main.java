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

	String[] columnNames = { "Job Name", "Job ID", "Status" };
	Object[][] data = { { "No jobs", "N/A", "N/A" } };

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		master = new Master();

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
		frame.getContentPane().setLayout(
				new FlowLayout(FlowLayout.CENTER, 5, 5));
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

		JButton btnShowJobsTable = new JButton("Show Job Statuses");
		btnShowJobsTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JobTable jobs = new JobTable(master);
				jobs.setLocation(250, 250);
			}
		});
		frame.getContentPane().add(btnShowJobsTable);

		JButton btnShowWorkersTable = new JButton("Show Worker Statuses");
		btnShowWorkersTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkerTable workers = new WorkerTable(master);
				workers.setLocation(250, 250);
			}
		});
		frame.getContentPane().add(btnShowWorkersTable);
	}
}
