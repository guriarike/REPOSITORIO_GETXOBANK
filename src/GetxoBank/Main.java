package GetxoBank;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		VentanaInicio vgetxobank = new VentanaInicio();
		vgetxobank.setVisible(true);
		BD baseDatos = new BD();
		ArrayList<Usuario> us = baseDatos.us;
		
		if(us.isEmpty()) {
			baseDatos.rellenarBD();
		};
		

	}

}
