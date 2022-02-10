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
import javax.swing.JOptionPane;

public class VentanaEnviarDinero extends JFrame {

	private static JFrame ventanaActual;
	private static JFrame ventanaAnterior;
	private static Usuario usuarioActual;
	private static Cuenta cuentaActual;
	private JPanel contentPane;
	private JTextField txtCantidad;
	private ImageIcon getxobank;
	private JTextField tfNumero;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEnviarDinero window = new VentanaEnviarDinero(cuentaActual, usuarioActual);
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
	public VentanaEnviarDinero(Cuenta c, Usuario u) {

		ventanaActual = this;
		usuarioActual = u;
		cuentaActual = c;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1352, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		getxobank = new ImageIcon(VentanaCajeroSacar.class.getResource("logo_small.png"));
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
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
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("N\u00DAMERO TARJETA DE LA CUENTA DESTINATARIA:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_3.add(lblNewLabel_1);
		
		tfNumero = new JTextField();
		panel_3.add(tfNumero);
		tfNumero.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnVolver = new JButton("VOLVER");
		panel_2.add(btnVolver);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		panel_2.add(btnAceptar);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				VentanaHome ventanaHome = new VentanaHome(cuentaActual, usuarioActual);
				ventanaHome.setVisible(true);
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cuenta cuenta = BD.getCuentaEspecifica(Integer.valueOf(tfNumero.getText()));
				System.out.println(cuenta);
				Usuario usuario = BD.getUsuarioEspecifico(cuenta);
				System.out.println(usuario);
				if(cuentaActual.getSaldo() < Integer.valueOf(txtCantidad.getText())) {
					JOptionPane.showMessageDialog(null, "DINERO INSUFICIENTE EN LA CUENTA", "error", JOptionPane.ERROR_MESSAGE);
				} else {
					if(cuenta == null) {
						JOptionPane.showMessageDialog(null, "CUENTA DESTINATARIA NO EXISTENTE", "error", JOptionPane.ERROR_MESSAGE);
					} else {
						BD.enviarDinero(cuentaActual, cuenta, Integer.valueOf(txtCantidad.getText()));
						usuarioActual.setSaldoTotal(usuarioActual.getSaldoTotal() - Integer.valueOf(txtCantidad.getText()));
						usuario.setSaldoTotal(usuario.getSaldoTotal() + Integer.valueOf(txtCantidad.getText()));
					}
				
				}
				ventanaActual.dispose();
				VentanaHome ventanaHome = new VentanaHome(cuentaActual, usuarioActual);
				ventanaHome.modelo.addRow(new Object[] {usuario.getNombre(), "Ingresar Dinero", Integer.valueOf(txtCantidad.getText()), System.currentTimeMillis()});
				ventanaHome.setVisible(true);
			}
		});
		
		
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
					BD.ingresarDinero(cuentaActual, Integer.valueOf(txtCantidad.getText()));
					System.out.println(c);
				}
				
			}
		});
	}
}
