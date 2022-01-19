package GetxoBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInformacion extends JFrame {
	private JTextField textInformacion;
	private String informacionSobreGetxoBank;
	public VentanaInformacion() {
		
		JPanel panelSur = new JPanel();
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JButton btnVolver = new JButton("VOLVER");
		panelSur.add(btnVolver);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(0, 1, 0, 0));
		
		textInformacion = new JTextField();
		textInformacion.setEditable(false);
		panelCentral.add(textInformacion);
		textInformacion.setColumns(10);
		
		
		//
		informacionSobreGetxoBank = "GetxoBank \n "
				+ "Nuestra empresa es capaz de almacenar datos sobre usuarios bancarios , nuestros propios empleados y las cuentas de cada usuario. ";
		textInformacion.setText(informacionSobreGetxoBank);
		
	}
	
}
