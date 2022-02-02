package GetxoBank;
import java.sql.SQLException;
import java.util.*;


public class Main {

	public static void main(String[] args) {
		VentanaInicio vgetxobank = new VentanaInicio();
		vgetxobank.setVisible(true);
		BD.initBD("getxobank.db");
		BD.borrarBD();
		ArrayList<Usuario> us = new ArrayList<>();
		us = BD.getUsuarios();
		BD.rellenarBD();
		System.out.println(BD.getUsuarios());

	}

}
