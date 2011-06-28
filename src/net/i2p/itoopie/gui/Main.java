package net.i2p.itoopie.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import net.i2p.itoopie.i18n.ItoopieTranslator;
import net.i2p.itoopie.i2pcontrol.JSONInterface;
import net.i2p.itoopie.util.BrowseException;
import net.i2p.itoopie.util.I2PDesktop;

public class Main {

	private JFrame frame;

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
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 300);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnStop = new JButton(ItoopieTranslator._("Stop I2P"));
		btnStop.setBounds(293, 91, 125, 25);
		frame.getContentPane().add(btnStop);
		
		JLabel lblStop = new JLabel(ItoopieTranslator._("Push to stop I2P"));
		lblStop.setBounds(293, 78, 125, 15);
		frame.getContentPane().add(lblStop);
		
		JButton btnStart = new JButton(ItoopieTranslator._("Start I2P"));
		btnStart.setBounds(43, 91, 125, 25);
		frame.getContentPane().add(btnStart);
		
		JLabel lblStart = new JLabel(ItoopieTranslator._("Push to start I2P"));
		lblStart.setBounds(42, 78, 125, 15);
		frame.getContentPane().add(lblStart);
		
		JLabel lblGetRate = new JLabel(ItoopieTranslator._("Get bwSend rate"));
		lblGetRate.setBounds(293, 172, 125, 15);
		frame.getContentPane().add(lblGetRate);
		
		final JLabel lblDispRate = new JLabel(ItoopieTranslator._("Rate not update yet, push button."));
		lblDispRate.setBounds(0,255,450,15);
		frame.getContentPane().add(lblDispRate);
		
		JButton btnGetRate = new JButton(ItoopieTranslator._("Update"));
		btnGetRate.setBounds(293, 185, 125, 25);
		frame.getContentPane().add(btnGetRate);
		
		JButton btnConnect = new JButton(ItoopieTranslator._("Connect"));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConnect.setBounds(43, 185, 125, 25);
		frame.getContentPane().add(btnConnect);
		
		JLabel lblConnect = new JLabel(ItoopieTranslator._("Connect to I2P"));
		lblConnect.setBounds(43, 172, 125, 15);
		frame.getContentPane().add(lblConnect);
		
		
		btnStop.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SwingWorker<Object, Object>() {
					
					@Override
					protected Object doInBackground() throws Exception {
						return null;
					}
					
					@Override
					protected void done() {
						try {
							I2PDesktop.browse("http://localhost:7657");
						} catch (BrowseException e1) { }    
					}
				}.execute();	
			}
			
		});
		
		btnGetRate.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SwingWorker<Object, Object>() {
					
					@Override
					protected Object doInBackground() throws Exception {
						double rate = JSONInterface.getRateStat("bw.sendRate", 3600000L);
						lblDispRate.setText(ItoopieTranslator._("Current bw.sendRate: " + rate));
						return null;
					}
				}.execute();
			}
		});
	}
}