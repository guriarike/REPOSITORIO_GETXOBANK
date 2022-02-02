package GetxoBank;
import java.util.*;


public class Main {

	public static void main(String[] args) {
		VentanaInicio vgetxobank = new VentanaInicio();
		vgetxobank.setVisible(true);
		BD.initBD("getxobank.db");
		ArrayList<Usuario> us = BD.getUsuarios();
		
		if(us == null) {
			BD.rellenarBD();
		};
		

	}

}
