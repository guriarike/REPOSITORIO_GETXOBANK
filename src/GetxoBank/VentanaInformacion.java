package GetxoBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaInformacion extends JFrame {

	private JPanel contentPane;
	private JTextField textTrabajador;
	ImageIcon imagenActual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInformacion frame = new VentanaInformacion();
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
	public VentanaInformacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblImagenTrabajador = new JLabel("");
		panelCentro.add(lblImagenTrabajador);
		
		textTrabajador = new JTextField();
		panelCentro.add(textTrabajador);
		textTrabajador.setColumns(10);
		
		JPanel panelZurdo = new JPanel();
		contentPane.add(panelZurdo, BorderLayout.WEST);
		
		JButton btnIzquierda = new JButton("<");
		panelZurdo.add(btnIzquierda);
		
		JPanel panelDiestro = new JPanel();
		contentPane.add(panelDiestro, BorderLayout.EAST);
		
		JButton btnDerecha = new JButton(">");
		panelDiestro.add(btnDerecha);
	}
	public void mostrarFotoDelEmpleado(Empleado e) {
		
	}

}
