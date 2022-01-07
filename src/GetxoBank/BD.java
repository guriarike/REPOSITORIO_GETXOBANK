package GetxoBank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;


public class BD {
	private static Connection con;
	
	public static boolean initBD(String nombreBD){
		
		try {
			System.out.println( "Conexión abierta" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			
			Statement statement = con.createStatement();
			String sent = "CREATE TABLE IF NOT EXISTS cuenta (nombre varchar(30), nCuenta INTEGER(10) PRIMARY KEY, tipo varchar(100), fecha DATE);";
			statement.executeUpdate( sent );
			sent = "CREATE TABLE IF NOT EXISTS usuario (nom varchar(20), dni char(9) PRIMARY KEY, pin INTEGER(4), saldoTotal INTEGER, fchaNcto DATE , provincia varchar(15), nCuenta INTEGER(10) FOREIGN KEY REFERENCES CUENTA(nCuenta));";
			statement.executeUpdate( sent );
			
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void cerrarConexion() {
		try {
			con.close();
			System.out.println( "Conexión cerrada" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Usuario> getUsuarios() {
		try (Statement statement = con.createStatement()) {
			ArrayList<Usuario> usuarios = new ArrayList<>();
			String sent = "select * from usuario;";
			System.out.println( sent );
			ResultSet rs = statement.executeQuery( sent );
			while( rs.next() ) { // Leer el resultset
				String nombre = rs.getString("nombre");
				String dni = rs.getString("dni");
				String pin = rs.getString("pin");
				int saldoTotal = rs.getInt("saldoTotal");
				Date fchaNac = rs.getDate("fchaNcto");
				String p = rs.getString("provincia");
				
				usuarios.add( new Usuario ( nombre, dni, pin, saldoTotal, fchaNac , Provincia.valueOf(p) , new ArrayList<Cuenta>() ) );
				}
			
			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	}
	
	