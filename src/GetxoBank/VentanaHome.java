package GetxoBank;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.event.*;

public class VentanaHome extends JFrame {

	private static JFrame ventanaActual;
	private static JFrame ventanaAnterior;
	private static Usuario usuarioActual;
	private static Cuenta cuentaActual;
	private JPanel contentPane;
	
	JTable tabla;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHome frame = new VentanaHome(cuentaActual, usuarioActual);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaHome(Cuenta c, Usuario us) {
		ventanaActual = this;
		usuarioActual = us;
		cuentaActual = c;
		tabla = new JTable();
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

		JLabel lblUltOpe = new JLabel("\u00DAltimas operaciones:");
		panelIzq.add(lblUltOpe);
		
		DefaultTableModel modelo = new DefaultTableModel();
		tabla = new JTable(modelo);
		tabla.setToolTipText("");
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Tipo");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Fecha");
		
		panelIzq.add(new JScrollPane(tabla), BorderLayout.CENTER);
		
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(table.getValueAt(row, 1).toString().equals("INGRESAR")) {
					comp.setBackground(Color.GREEN);
				} else if(table.getValueAt(row, 1).toString().equals("SACAR")){
					comp.setBackground(Color.RED);
				}
				return comp;
			}
		});

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
		btnIngresarDinero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				VentanaCajero vc = new VentanaCajero(cuentaActual, usuarioActual);
				vc.setVisible(true);
			}
		});

		JButton btnSacarDinero = new JButton("SACAR DINERO");
		panelDer.add(btnSacarDinero);
		btnSacarDinero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaActual.dispose();
				VentanaCajero vc = new VentanaCajero(cuentaActual, usuarioActual);
				vc.setVisible(true);
				
			}
		});

		JButton btnCambiarPin = new JButton("CAMBIAR PIN");
		panelDer.add(btnCambiarPin);

		JButton btnVolver = new JButton("VOLVER");
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
