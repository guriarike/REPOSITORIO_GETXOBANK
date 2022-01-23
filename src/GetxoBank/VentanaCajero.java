package GetxoBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class VentanaCajero extends JFrame {

	private static JFrame ventanaActual;
	private static JFrame ventanaAnterior;
	private static Usuario usuarioActual;
	private static Cuenta cuentaActual;
	private JPanel contentPane;
	private JTextField txtCantidad;
	private ImageIcon getxobank;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCajero window = new VentanaCajero(cuentaActual, usuarioActual);
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
	public VentanaCajero(Cuenta c, Usuario u) {

		ventanaActual = this;
		usuarioActual = u;
		cuentaActual = c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getxobank = new ImageIcon(VentanaCajero.class.getResource("logo_small.png"));
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 976, 276);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtCantidad = new JTextField();
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setFont(new Font("Tahoma", Font.PLAIN, 40));
		txtCantidad.setText("0");
		panel.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(236, 349, 539, 106);
		lblNewLabel.setIcon(getxobank);
		contentPane.add(lblNewLabel);
		
		JButton btnAumentar = new JButton("+");
		btnAumentar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnAumentar);
		btnAumentar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtCantidad.setText(String.valueOf(Integer.valueOf(txtCantidad.getText()) + 1));	
			}
		});
		
		JButton btnDisminuir = new JButton("-");
		btnDisminuir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnDisminuir);
		btnDisminuir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtCantidad.setText(String.valueOf(Integer.valueOf(txtCantidad.getText()) - 1));
			}
		});
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setBounds(126, 502, 357, 51);
		contentPane.add(btnVolver);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BD.ingresarDinero(cuentaActual, Integer.valueOf(txtCantidad.getText()));
			}
		});
		btnAceptar.setBounds(515, 502, 357, 51);
		contentPane.add(btnAceptar);
		
		
		JLabel tecla = new JLabel("");
		tecla.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					BD.ingresarDinero(c, Integer.valueOf(txtCantidad.getText()));
				}
				
			}
		});
	

		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				VentanaGestion ventanaGestion = new VentanaGestion(cuentaActual, usuarioActual);
				ventanaGestion.setVisible(true);

			}
		});
	}
}
