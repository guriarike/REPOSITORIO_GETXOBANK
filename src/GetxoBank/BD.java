package GetxoBank;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;




public class BD {
	private static Connection con;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	
	public static boolean initBD(String nombreBD){
		
		try {
			System.out.println( "ConexiÃ³n abierta" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			
			Statement statement = con.createStatement();
			String sent = "CREATE TABLE IF NOT EXISTS cuenta (nombre varchar(30), nCuenta INTEGER(10) PRIMARY KEY,saldo Decimal(7,2) tipo varchar(100));";
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
			System.out.println( "ConexiÃ³n cerrada" );
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
				ArrayList<Cuenta> aCuentas = getCuentasDeUnUsuario(nombre);
				
				usuarios.add( new Usuario ( nombre, dni, pin, saldoTotal, fchaNac , Provincia.valueOf(p), aCuentas ) );
				}
			
			return usuarios;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Cuenta> getCuentas() {
		try (Statement statement = con.createStatement()) {
			ArrayList<Cuenta> cuentas = new ArrayList<>();
			String sent = "select * from cuentas;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while(rs.next()) {
				String nombre = rs.getString("nombre");
				int nTarjeta = rs.getInt("nCuenta");
				double saldo = rs.getDouble("saldo");
				String tipo = rs.getString("tipo");
				cuentas.add(new Cuenta(nombre, nTarjeta, saldo, TipoCuenta.valueOf(tipo)));
			}
			statement.close();
			return cuentas;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return null;
		}
		
	}
	
	public static ArrayList<Cuenta>getCuentasDeUnUsuario(String nombre) {
		ArrayList<Cuenta> cuentas = getCuentas();
		ArrayList<Cuenta> cuentasUsuario = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cuentas) {
			if(cuenta.getNombre() == nombre) {
				cuentasUsuario.add(cuenta);
			}
		}
		return cuentasUsuario;
		
	}
	
	public static boolean insertarUsuario(Usuario u) {
		try (Statement st = con.createStatement()){
			ArrayList<Cuenta> nuevaCuenta = new ArrayList<>();
			String sent = "insert into usuario values ('" + u.getNombre() + "','" + u.getDni() + "','" + u.getPin() + "'," + u.getSaldoTotal() + ",'" + u.getFecha_nac() + "','" + u.getProvincia() + "'," + nuevaCuenta + ");";
			logger.log( Level.INFO, "Statement: " + sent );
			int insertados = st.executeUpdate( sent );
			if (insertados!=1) return false;
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			String pk = rs.getString( 2 );
			u.setDni(pk);
			st.close();
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
		
	}
	
	
	
	public static boolean insertarNuevaCuenta(Cuenta c, Usuario u) {
		try (Statement st = con.createStatement()) {
			ArrayList<Usuario> usuarios = getUsuarios();
			if(!usuarios.contains(u)) {
				insertarUsuario(u);
			}
			if(c.getNombre() != u.getNombre()) return false;
			String sent = "insert into Cuenta values('" + c.getNombre() + "', " + c.getNumeroTarjeta() + ", " + c.getSaldo() + ", '"+ c.getTipo()+ "');";
			int val = st.executeUpdate(sent);
			logger.log(Level.INFO, val + "cuenta añadida");
			st.close();
			return true;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
			return false;
		}
	}
	
	public static void cambiarPin(Usuario u, String pin) throws SQLException {
		Statement st = con.createStatement();
		String nuevoPin = pin;
		String sent = "update usuario set pin = '" + nuevoPin + "' where dni = '" + u.getDni() + "';";
		ResultSet rrss = st.executeQuery(sent);
		rrss.next();
		st.close();
	}
	
	public static void enviarDinero(Cuenta cPaga, Cuenta cCobra, double dinero) {
		try (Statement st = con.createStatement()){
			double saldoNuevo1 = cPaga.getSaldo() - dinero;
			double saldoNuevo2 = cCobra.getSaldo() + dinero;
			String sent = "update cuenta set saldo = " + saldoNuevo1 + " where nombre = '" + cPaga.getNombre() + "';";
			String sent2 = "update cuenta set saldo = " + saldoNuevo2 + " where nombre = '" + cCobra.getNombre() + "';";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void rellenarBD() {
		try {
			Statement st = con.createStatement();
			//st.executeUpdate(sent);
			ArrayList<String> posiblesNombres = new ArrayList<>();
			posiblesNombres.add("MARTA");
			posiblesNombres.add("IÑIGO");
			posiblesNombres.add("UNAI");
			posiblesNombres.add("PAULA");
			posiblesNombres.add("ANDER");
			posiblesNombres.add("MAIDER");
			posiblesNombres.add("JUNE");
			posiblesNombres.add("LANDER");
			posiblesNombres.add("PEIO");
			posiblesNombres.add("DAVID");
			posiblesNombres.add("IZARO");
			posiblesNombres.add("ITSASO");
			posiblesNombres.add("CARMEN");
			posiblesNombres.add("ALBA");
			posiblesNombres.add("IBON");
			posiblesNombres.add("IBAI");
			posiblesNombres.add("HAIZEA");
			posiblesNombres.add("MIKEL");
			posiblesNombres.add("MIRIAN");
			posiblesNombres.add("PATXI");
			posiblesNombres.add("NORA");
			posiblesNombres.add("JON");
			posiblesNombres.add("MARIA");
			
			
			
			ArrayList<String> posiblesApellidos = new ArrayList<>();
			posiblesApellidos.add("GUTIERREZ");
			posiblesApellidos.add("VAZQUEZ");
			posiblesApellidos.add("BEASCOECHEA");
			posiblesApellidos.add("MARTINEZ");
			posiblesApellidos.add("RIKE");
			posiblesApellidos.add("CALVO");
			posiblesApellidos.add("GELADO");
			posiblesApellidos.add("JIMENEZ");
			posiblesApellidos.add("ITURRIOTZ");
			posiblesApellidos.add("LANDALUCE");
			posiblesApellidos.add("ALMEIDA");
			posiblesApellidos.add("GOIRIGOLZARRI");
			posiblesApellidos.add("BIDEGORRI");
			posiblesApellidos.add("PANERA");
			posiblesApellidos.add("GOMEZ");
			posiblesApellidos.add("CABO");
			posiblesApellidos.add("REMENTERIA");
			posiblesApellidos.add("CARRANZA");
			posiblesApellidos.add("SANZ");
			
			
			
			
			
			String nombreRandom = "";
			String fechaNacimientoRandom = "";
			String provinciaRandom = "BIZKAIA";
			String pinRandom = "aaaa";
			
			
			
			String sent = "INSERT INTO USUARIO VALUES ('')";
			
			
		
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	
	}
	
	