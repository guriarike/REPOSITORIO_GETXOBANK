package GetxoBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class VentanaLogiin extends JFrame {
	
	private JFrame ventanaActual;
	private JPanel contentPane;
	private JTextField textDni;
	private String dni,contraseña;
	private HashMap<String,Usuario>hmUsuarios;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER");
		
		panelSur.add(btnVolver);
		
		JButton btnAcceder = new JButton("ACCEDER");
		btnAcceder.setForeground(Color.BLACK);
		btnAcceder.setBackground(Color.WHITE);
		
		panelSur.add(btnAcceder);
		
		JButton btnRegistrarse = new JButton("REGISTRARSE");
		
		panelSur.add(btnRegistrarse);
		
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
		dni = textDni.getText();
		contraseña = textContraseña.getText();
		//ACTION LISTENER
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PRIMERO COMPROBAR QUE LOS DATOS SON CORRECTOS
				if (!comprobarDatos() ) {
					
				}else {
					comprobarSiEstaEnElHashMap();
					comprobarSiLaContraseñaEstaBien();
					
					
					
					
					
					
					
					
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
		JFrame ventana = new VentanaGetxoBank();
		ventana.setVisible(true);
		
	}
	public boolean comprobarDatos() {
		String erDni ="[0-9]{8}[A-Z]{1}";
		
		
		boolean verdad = Pattern.matches(dni,erDni);
		if (verdad) {
			return verdad;
		}else {
			JOptionPane.showMessageDialog(null, "El dni o la contraseña no son correctos", "¡¡ERROR!!", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		
	}
	public void comprobarSiEstaEnElHashMap() {
	
		for (String clave:hmUsuarios.keySet()) {
			
			Usuario valor = hmUsuarios.get(clave);
			if ( valor.getDni() == dni) {
				System.out.println("SI ESTA ");
				
			}else {
				System.out.println("NO ESTA REGISTRADO");
			}
		}
	}
	public void comprobarSiLaContraseñaEstaBien() {
		for (String clave:hmUsuarios.keySet()) {
			
			Usuario valor = hmUsuarios.get(clave);
			if ( valor.getContraseñaUsuario() == contraseña) {
				System.out.println("CONTRASEÑA CORRECTA ");
				
			}else {
				System.out.println("CONTRASEÑA INCORRECTA");
			}
		}
	}
	public void abrirVentanaRegistro() {
		ventanaActual.dispose();
		JFrame ventanaregistro = new VentanaRegistro();
		ventanaregistro.setVisible(true);
	}
	
	
	
	
	
	

}
