package GetxoBank;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;

public class VentanaAdmin extends JFrame{

	private JFrame ventanaActual;
	private ArrayList<Usuario> us;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaAdmin() {
		
		ventanaActual = this;
		
		
		setBounds(100, 100, 455, 355);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		
		JList list = new JList();
		panel_1.add(list);
		DefaultListModel<Usuario> listmodel = new DefaultListModel<>();

		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(11, 0, 0, 0));
		
		us = BD.getUsuarios();
		
		for (Usuario usuario : us) {
			listmodel.addElement(usuario);
		}
		
		Usuario mayorS = us.get(0);
		int totalSaldo = 0;
		Usuario mayorE = us.get(0);
		int contBiz = 0;
		int contGip = 0;
		int contAra = 0;
		Provincia mayorC = Provincia.BIZKAIA;
		
		for (Usuario usuario : us) {
			listmodel.addElement(usuario);
			if(usuario.getSaldoTotal() > mayorS.getSaldoTotal()) {
				mayorS = usuario;
			}
			totalSaldo += usuario.getSaldoTotal();
			if(usuario.getAño_nac()< mayorE.getAño_nac()) {
				mayorE = usuario;
			}
			if(usuario.getProvincia() == Provincia.BIZKAIA) {
				contBiz++;
			} else if(usuario.getProvincia() == Provincia.GIPUZKOA) {
				contGip++;
			} else {
				contAra++;
			}
		}
		
		if(contBiz >= contGip && contBiz >= contAra) {
			mayorC = Provincia.BIZKAIA;
		} else {
			if(contAra > contGip) {
				mayorC = Provincia.ARABA;
			} else {
				mayorC = Provincia.GIPUZKOA;
			}
		}
		
		
	
		
		JLabel lblNewLabel = new JLabel("Cantidad de clientes: ");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setText(String.valueOf(us.size()));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario con mayor saldo : ");
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setText(mayorS.toString());
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Media saldo: ");
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setText(String.valueOf(totalSaldo / us.size()));
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cliente m\u00E1s longevo:");
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setText(mayorE.toString());
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Provincia con m\u00E1s clientes");
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setText(String.valueOf(mayorC));
		panel.add(lblNewLabel_9);
		
		JButton btnVolver = new JButton("VOLVER");
		panel.add(btnVolver);
		
	}

}
