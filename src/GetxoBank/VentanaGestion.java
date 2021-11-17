package GetxoBank;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class VentanaGestion extends JFrame {

	private static JFrame ventanaActual;
	private static JFrame ventanaAnterior;
	private static Usuario usuarioActual;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGestion frame = new VentanaGestion(usuarioActual, ventanaAnterior);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaGestion(Usuario us, JFrame va) {
		ventanaAnterior = va;
		ventanaActual = this;
		usuarioActual = us;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelIzq = new JPanel();
		panelCentral.add(panelIzq);
		panelIzq.setLayout(new GridLayout(4, 1, 0, 0));

		JLabel lblNombre = new JLabel("Hola ");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelIzq.add(lblNombre);

		JLabel lblSaldo = new JLabel("500" + "€");
		lblSaldo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panelIzq.add(lblSaldo);

		JLabel lblNewLabel = new JLabel("\u00DAltimas operaciones:");
		panelIzq.add(lblNewLabel);
		JComboBox comboUltimasOperaciones = new JComboBox();
		comboUltimasOperaciones.setToolTipText("");
		panelIzq.add(comboUltimasOperaciones);

		JPanel panelDer = new JPanel();
		panelCentral.add(panelDer);
		panelDer.setLayout(new GridLayout(6, 1, 0, 0));

		JButton btnCrearCuenta = new JButton("CREAR CUENTA");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelDer.add(btnCrearCuenta);

		JButton btnCambiarCuenta = new JButton("CAMBIAR CUENTA");
		btnCambiarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelDer.add(btnCambiarCuenta);

		JButton btnEnviarDinero = new JButton("ENVIAR DINERO");
		panelDer.add(btnEnviarDinero);

		JButton btnIngresarDinero = new JButton("INGRESAR DINERO");
		panelDer.add(btnIngresarDinero);

		JButton btnSacarDinero = new JButton("SACAR DINERO");
		panelDer.add(btnSacarDinero);

		JButton btnCambiarPin = new JButton("CAMBIAR PIN");
		panelDer.add(btnCambiarPin);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnVolver, BorderLayout.SOUTH);

		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
				ventanaActual.dispose();
				VentanaLogiin ventanaLogin = new VentanaLogiin();
				ventanaLogin.setVisible(true);

			}
		});
	}

}
