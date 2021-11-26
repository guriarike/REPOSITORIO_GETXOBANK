package GetxoBank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;


public class BD {
	private static Connection con;
	
	public static void initBD(String nombreBD){
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeBD() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas() {
		String sent1 = "CREATE TABLE IF NOT EXISTS Persona(email String, nom String, dni String, apellido String, telefono intenger, contraseña string, añonacimiento integer, provincia String )";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static TreeMap<String, Usuario> obtenerMapaUsuario(){
		String sent = "SELECT * FROM Usuario";
		Statement st = null;
		TreeMap<String, Usuario> mapaUsuario = new TreeMap<>();
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String e = rs.getString("email");
				String n = rs.getString("nom");
				String d = rs.getString("dni");
				String a = rs.getString("apellido");
				int t = rs.getInt("telefono");
				String c = rs.getString("contrasenia");
				int i = rs.getInt("anio nacimiento");
				String v = rs.getString("provincia");
				// el treeSet Cuenta no se como declararlo.
				Usuario p = new Usuario(e, n, d, a, t, c, i, v);
				mapaUsuario.put(d, p);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return mapaUsuario;
	}}
	
	