package GetxoBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaLogiin extends JFrame {
	
	private JFrame ventanaActual;
	private JPanel contentPane;
	private JTextField textDni;
	private String dni, con;
	private char[] contraseña;
	private HashMap<String,Usuario> hmUsuarios;
	private JPasswordField textContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogiin frame = new VentanaLogiin();
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
	public VentanaLogiin() {
		ventanaActual = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		
		JButton btnAcceder = new JButton("ACCEDER");
		panelSur.add(btnAcceder);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		panelSur.add(btnRegistrarse);
		
		JRadioButton admin = new JRadioButton("ADMIN");
		panelSur.add(admin);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDni = new JLabel("DNI ---->");
		lblDni.setForeground(Color.BLACK);
		lblDni.setBackground(Color.WHITE);
		panelCentro.add(lblDni);
		
		textDni = new JTextField();
		panelCentro.add(textDni);
		textDni.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CONTRASE\u00D1A");
		panelCentro.add(lblNewLabel_1);
		
		textContraseña = new JPasswordField();
		panelCentro.add(textContraseña);
		//ACTION LISTENER
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PRIMERO COMPROBAR QUE LOS DATOS SON CORRECTOS
				
				dni = textDni.getText();
				contraseña = textContraseña.getPassword();
				con = new String(contraseña);
				boolean correcto = BD.comprobarUsuarioExistente(dni, con);
				Usuario u = BD.getUsuarioEspecifico(dni);
				System.out.println(u);
				if(u == null) {
					JOptionPane.showMessageDialog(null, "DNI O CONTRASEÑA INCORRECTAS", "error", JOptionPane.ERROR_MESSAGE);
					VentanaLogiin.main(null);
				} else {
				Cuenta c = BD.getCuentaConMayorSaldo(u);
				
				if(admin.isSelected() && correcto && dni.equals("16096183R")) {
					ventanaActual.dispose();
					JFrame ventanaAdmin = new VentanaAdmin();
					ventanaAdmin.setVisible(true);
				} else {
					if(correcto) {
						ventanaActual.dispose();
						JFrame ventanaHome = new VentanaHome(c, u);
						ventanaHome.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "DNI O PIN INCORRECTOS", "error", JOptionPane.ERROR_MESSAGE);
					}
				}
				}
						
				
			}
		});
		btnAcceder.addKeyListener(new KeyListener() {
			
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
				// TODO Auto-generated method stub
				if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
					dni = textDni.getText();
					contraseña = textContraseña.getPassword();
					con = new String(contraseña);
					
					boolean correcto = BD.comprobarUsuarioExistente(dni, con);
					ArrayList<Cuenta> cuentas = BD.getCuentasDeUnUsuario(dni);
					Usuario u = BD.getUsuarioEspecifico(dni);
					System.out.println(u);
					Cuenta c = BD.getCuentaConMayorSaldo(u);
					
					if(admin.isSelected() && correcto && dni.equals("16096183R")) {
						ventanaActual.dispose();
						JFrame ventanaAdmin = new VentanaAdmin();
						ventanaAdmin.setVisible(true);
					} else {
						if(correcto) {
							ventanaActual.dispose();
							JFrame ventanaHome = new VentanaHome(c, u);
							ventanaHome.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "DNI O PIN INCORRECTOS", "error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaRegistro();
			}
		});
		
		
		
		}

	
	//METODOS
	public void vaciarCampos() {
		textContraseña.setText("");
		textDni.setText("");
		
	}
	public void volver() {
		ventanaActual.dispose();
		JFrame ventana = new VentanaInicio();
		ventana.setVisible(true);
		
	}
	public boolean comprobarDatos() {
		String erDni ="[0-9]{8}[A-Z]{1}";
		
		
		boolean verdad = Pattern.matches(dni,erDni);
		if (verdad) {
			return true;
		} else if (dni.equals("")){
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "El dni o la contraseña no son correctos", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	public void abrirVentanaRegistro() {
		ventanaActual.dispose();
		JFrame ventanaregistro = new VentanaRegistro();
		ventanaregistro.setVisible(true);
	}
	
	
	
	
	
	

}
