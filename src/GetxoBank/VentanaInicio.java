package GetxoBank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;

public class VentanaInicio extends JFrame {

	private JFrame ventanaActual;
	private ImageIcon logo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio window = new VentanaInicio();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ventanaActual = this;
		ventanaActual.setBounds(100, 100, 650, 400);
		ventanaActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventanaActual.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("LOG IN / SIGN UP");
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				VentanaLogiin vLogin = new VentanaLogiin();
				vLogin.setVisible(true);
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("                     ");
		lblNewLabel_1.setBackground(Color.ORANGE);
		panel_1.add(lblNewLabel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("                     ");
		lblNewLabel_2.setBackground(Color.ORANGE);
		panel_1.add(lblNewLabel_2, BorderLayout.EAST);
		
		logo = new ImageIcon(VentanaInicio.class.getResource("logo_small.png"));
		
		lblNewLabel.setIcon(logo);
		lblNewLabel.setSize(ventanaActual.getWidth(),ventanaActual.getHeight());
	}

}
