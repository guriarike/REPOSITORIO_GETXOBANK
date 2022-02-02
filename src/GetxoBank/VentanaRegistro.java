package GetxoBank;

import java.awt.BorderLayout;
import java.util.*;
import java.text.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Date;
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
	private static JComboBox<Integer> comboA�o;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContrase�a;
	private JTextField textDni;
	private Provincia p;
	private String nombre,dni,contrase�a;
	private int a�o;
	private JFrame ventanaActual;
	private ArrayList<Cuenta> cuentasUsuario;

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
		panelCentral.setLayout(new GridLayout(7, 2, 0, 0));
		
		JLabel lblNombre = new JLabel("NOMBRE");
		panelCentral.add(lblNombre);
		
		textNombre = new JTextField();
		panelCentral.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		panelCentral.add(lblDni);
		
		textDni = new JTextField();
		panelCentral.add(textDni);
		textDni.setColumns(10);
	
		
		JLabel lblContrase�a = new JLabel("INTRODUZCA SU CONTRASE\u00D1A");
		panelCentral.add(lblContrase�a);
		
		textContrase�a = new JTextField();
		panelCentral.add(textContrase�a);
		textContrase�a.setColumns(10);
		
		JLabel lblA�oNacimiento = new JLabel("A\u00D1O DE  NACIMIENTO");
		panelCentral.add(lblA�oNacimiento);
		
		JComboBox comboA�o = new JComboBox<Integer>();
		llenarComboFechas(comboA�o);
		
		panelCentral.add(comboA�o);
		
		JLabel lblProvincia = new JLabel("PROVINCIA");
		panelCentral.add(lblProvincia);
		
		JComboBox comboProvincia = new JComboBox();
		comboProvincia.setModel(new DefaultComboBoxModel(Provincia.values()));
		panelCentral.add(comboProvincia);
		
		ventanaActual = this;
		nombre = textNombre.getText();
		dni= textDni.getText();
		contrase�a = textContrase�a.getText();			
		a�o = (Integer) comboA�o.getSelectedItem();
		p = (Provincia) comboProvincia.getSelectedItem();
		 
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
	public void crearUsuario() {
		Usuario u = new Usuario(nombre, dni, contrase�a, 0.0, a�o,  p, new ArrayList<Cuenta>());
		guardarUsuarioEnElHashMapDeLaVentanaLogin(u);
		System.out.println(u);
		//Usuario u = new Usuario();
		
	}
	public void llenarComboFechas(JComboBox<Integer> combo) {
		// LLENAMOS EL COMBO BOX CON LOS ULTIMOS 100 A�OS
		int a�o = 2022;
		int i = 0;
		
		for(i = 0; i<100;i++) {
			a�o = a�o -1;
			combo.addItem(a�o);
			
		}
		
	}
	public static void guardarUsuarioEnElHashMapDeLaVentanaLogin(Usuario u) {
		BD.insertarUsuario(u);
		
	}
	
	


}
