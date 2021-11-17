package GetxoBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {
	
	private JComboBox comboProvincia;
	private static JComboBox<Integer> comboAño;
	private JPanel contentPane;
	private JTextField textEmail;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textTelefono;
	private JTextField textContraseña;
	private JTextField textDni;
	private int numeroDeCuentas,saldoUsuario,telefono;
	private Provincia p;
	private String email,nombre,apellidos,dni,contraseña,fechaNacimiento;
	private JFrame ventanaActual;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
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
	public VentanaRegistro() {
		/**
		textApellidos.setText("");;
		textContraseña.setText("");
		textDni.setText("");;
		textEmail.setText("");;
		textNombre.setText("");;
		textTelefono.setText("");
		**/
		
		
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
		
		JButton btnFinalizar = new JButton("FINALIZAR");
	
		panelSur.add(btnFinalizar);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(10, 2, 0, 0));
		
		JLabel lblEmail = new JLabel(" E-MAIL");
		panelCentral.add(lblEmail);
		
		textEmail = new JTextField();
		panelCentral.add(textEmail);
		textEmail.setColumns(10);
		
		JLabel lblNombre = new JLabel(" NOMBRE");
		panelCentral.add(lblNombre);
		
		textNombre = new JTextField();
		panelCentral.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("Dni");
		panelCentral.add(lblDni);
		
		textDni = new JTextField();
		panelCentral.add(textDni);
		textDni.setColumns(10);
		
		JLabel lblApellidos = new JLabel("APELLIDOS");
		panelCentral.add(lblApellidos);
		
		textApellidos = new JTextField();
		panelCentral.add(textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblTelefono = new JLabel("TELEFONO MOVIL");
		panelCentral.add(lblTelefono);
		
		textTelefono = new JTextField();
		panelCentral.add(textTelefono);
		textTelefono.setColumns(10);
		
		JLabel lblContraseña = new JLabel("INTRODUZCA SU CONTRASE\u00D1A");
		panelCentral.add(lblContraseña);
		
		textContraseña = new JTextField();
		panelCentral.add(textContraseña);
		textContraseña.setColumns(10);
		
		JLabel lblAñoNacimiento = new JLabel("A\u00D1O DE  NACIMIENTO");
		panelCentral.add(lblAñoNacimiento);
		
		
	
		
		
		
		JComboBox comboAño = new JComboBox<Integer>();
		llenarComboFechas(comboAño);
		
		
		panelCentral.add(comboAño);
		
		JLabel lblProvincia = new JLabel("PROVINCIA");
		panelCentral.add(lblProvincia);
		
		JComboBox comboProvincia = new JComboBox();
		comboProvincia.setModel(new DefaultComboBoxModel(Provincia.values()));
		panelCentral.add(comboProvincia);
		
		ventanaActual = this;
		email= textEmail.getText();
		nombre = textNombre.getText();
		apellidos= textApellidos.getText();
		dni= textDni.getText();
		contraseña = textContraseña.getText();			
		numeroDeCuentas= 0;
		Object fch= comboAño.getSelectedItem();
		fechaNacimiento = fch.toString();
		saldoUsuario=0;
		
		 
		 
		 
		 
		 
		 
		 
		 
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				
				VentanaLogiin ventana = new VentanaLogiin();
				ventana.setVisible(true);
				
				
			}
		});
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearUsuario();
			}
		});
		
		
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	}
	//METODOS
	public void comprobarEmail() {
		String datoRecibido = textEmail.getText();
		
	}
	public  boolean comprobarDni() {
		String datoRecibido = textDni.getText();
		String erDni = "[0-9] {8}[A-Z] {1}";
		boolean correctoDni = Pattern.matches(erDni, datoRecibido);
		return correctoDni;
		
		
	}
	public boolean comprobarNombre() {
		String datoRecibido = textNombre.getText();
		String erNombre = "[A-Z]";
		boolean correctoNombre = Pattern.matches(erNombre, datoRecibido);
		return correctoNombre;
	}
	public boolean comprobarTelelfono() {
		String datoRecibido = textTelefono.getText();
		String erTelefono = "[0-9] {9}";
		boolean correctoTelefono = Pattern.matches(erTelefono, datoRecibido);
		return correctoTelefono;
	}
	public Usuario crearUsuario() {
		/**public Usuario(String email, String nombre, String apellidos, String dni, String fechaNacimiento,
			String contraseñaUsuario, Provincia provincia, int numeroDeCuentas, int saldoUsuario, int telefono) 
		**/
		
		
		Usuario u = new Usuario(email, nombre, apellidos, dni, fechaNacimiento, contraseña, p, numeroDeCuentas, saldoUsuario,  telefono);
		System.out.println(u);
		return u;
		//Usuario u = new Usuario();
		
		//Usuario  nuevoUsuario= new Usuario(textEmail.getText(), textNombre.getText(), textApellidos.getText(), textDni.getText(), comboAño.getSelectedItem(), comboProvincia.getSelectedItem(), 0, 0, 0);
		
	}
	public void llenarComboFechas(JComboBox<Integer> combo) {
		// LLENAMOS EL COMBO BOX CON LOS ULTIMOS 100 AÑOS
		int año = 2022;
		int i = 0;
		
		for(i = 0; i<100;i++) {
			año = año -1;
			combo.addItem(año);
			
		}
		
	}
	public void guardarUsuarioEnElHashMapDeLaVentanaLogin() {
		
		
	}
	
	


}
