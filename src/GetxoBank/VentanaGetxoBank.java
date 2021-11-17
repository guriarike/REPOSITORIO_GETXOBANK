package GetxoBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaGetxoBank extends JFrame {
	private JFrame ventanaActual;
	private JPanel contentPane, panelSur ;
	private ImageIcon logo,getxobank;
	private JLabel lblGetxoBank ,lblLogo;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGetxoBank frame = new VentanaGetxoBank();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaGetxoBank() {
		
		ventanaActual= this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		logo = new ImageIcon(VentanaGetxoBank.class.getResource("logo_small_icon_only.png"));
		getxobank = new ImageIcon(VentanaGetxoBank.class.getResource("logo_small.png"));
		
		lblGetxoBank = new JLabel("");
		lblGetxoBank.setSize(200,200);
		lblGetxoBank.setIcon(getxobank);
		
		contentPane.add(lblGetxoBank, BorderLayout.NORTH);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(logo);
		lblLogo.setBounds(250,250,250,250);
		contentPane.add(lblLogo, BorderLayout.CENTER);
		
		panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		btnLogin = new JButton("LOGIN/ENTRAR");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelSur.add(btnLogin);
		/*****
		 * 
		 * 
		 * 
		 * 
		 * EVENTOS
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				
				new VentanaLogiin().setVisible(true); 
				
			}
		});
	}

}
