package GetxoBank;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;




public class BD {
	private static Connection con;
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	private static Random random;
	protected static ArrayList<Usuario> us;
	
	public static boolean initBD(String nombreBD){
		
		try {
			System.out.println( "ConexiÃ³n abierta" );
			Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			
			Statement statement = con.createStatement();
			String sent = "CREATE TABLE IF NOT EXISTS CUENTA (dni varchar(9), nCuenta INTEGER(10) PRIMARY KEY,saldo FLOAT, tipo varchar(100));";
			statement.executeUpdate( sent );
			String sent1 = "CREATE TABLE IF NOT EXISTS USUARIO (nom varchar(20), dni varchar(9) PRIMARY KEY, pin INTEGER(4), saldoTotal INTEGER, añoNac INTEGER(4) , provincia varchar(15));";
			statement.executeUpdate( sent1 );
			
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
				String nombre = rs.getString("nom");
				String dni = rs.getString("dni");
				String pin = rs.getString("pin");
				int saldoTotal = rs.getInt("saldoTotal");
				int añoNac = rs.getInt("añoNac");
				String p = rs.getString("provincia");
				ArrayList<Cuenta> aCuentas = getCuentasDeUnUsuario(dni);
				usuarios.add( new Usuario ( nombre, dni, pin, saldoTotal, añoNac , Provincia.valueOf(p), aCuentas ) );
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
			String sent = "select * from CUENTA;";
			logger.log( Level.INFO, "Statement: " + sent );
			ResultSet rs = statement.executeQuery( sent );
			while(rs.next()) {
				String dni = rs.getString("dni");
				int nTarjeta = rs.getInt("nCuenta");
				double saldo = rs.getDouble("saldo");
				String tipo = rs.getString("tipo");
				cuentas.add(new Cuenta(dni, nTarjeta, saldo, TipoCuenta.valueOf(tipo)));
			}
			statement.close();
			return cuentas;
		} catch (Exception e) {
			logger.log( Level.SEVERE, "ExcepciÃ³n", e );
			return null;
		}
		
	}
	
	public static ArrayList<Cuenta>getCuentasDeUnUsuario(String dni) {
		ArrayList<Cuenta> cuentas = getCuentas();
		ArrayList<Cuenta> cuentasUsuario = new ArrayList<Cuenta>();
		for (Cuenta cuenta : cuentas) {
			if(cuenta.getDni() == dni) {
				cuentasUsuario.add(cuenta);
			}
		}
		return cuentasUsuario;
		
	}
	
	public static boolean insertarUsuario(Usuario u) {
		try (Statement st = con.createStatement()){
			String sent = "insert into USUARIO values ('" + u.getNombre() + "','" + u.getDni() + "','" + u.getPin() + "'," + u.getSaldoTotal() + ",'" + u.getAño_nac() + "','" + u.getProvincia() + "');";
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
			if(c.getDni() != u.getDni()) return false;
			String sent = "insert into CUENTA values('" + c.getDni() + "', " + c.getNumeroTarjeta() + ", " + c.getSaldo() + ", '"+ c.getTipo()+ "');";
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
			String sent = "update cuenta set saldo = " + saldoNuevo1 + " where dni = '" + cPaga.getDni() + "' and nCuenta = " + cPaga.getNumeroTarjeta() + ";";
			String sent2 = "update cuenta set saldo = " + saldoNuevo2 + " where dni = '" + cCobra.getDni() + "' and nCuenta = " + cCobra.getNumeroTarjeta() + ";";
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepción", e );
		}
	}
	
	public static void ingresarDinero(Cuenta c, double dinero) {
		try (Statement st = con.createStatement()){
			double saldoNuevo = c.getSaldo() + dinero;
			String sent = "update cuenta set saldo = " + saldoNuevo + " where nombre = '" + c.getDni() + "' and nCuenta = " + c.getNumeroTarjeta() + ";";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sacarDinero(Cuenta c, double dinero) {
		try (Statement st = con.createStatement()){
			double saldoNuevo = c.getSaldo() - dinero;
			String sent = "update cuenta set saldo = " + saldoNuevo + " where nombre = '" + c.getDni() + "' and nCuenta = " + c.getNumeroTarjeta() + ";";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rellenarBD() {
		us = new ArrayList<>();
		try (Statement st = con.createStatement();){
			
			//st.executeUpdate(sent);
			random = new Random();
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
			
			List<String> posiblesLetras = new ArrayList<String>();
			
			posiblesLetras.add("Q");
			posiblesLetras.add("W");
			posiblesLetras.add("E");
			posiblesLetras.add("R");
			posiblesLetras.add("T");
			posiblesLetras.add("Y");
			posiblesLetras.add("U");
			posiblesLetras.add("I");
			posiblesLetras.add("O");
			posiblesLetras.add("P");
			posiblesLetras.add("L");
			posiblesLetras.add("K");
			posiblesLetras.add("J");
			posiblesLetras.add("H");
			posiblesLetras.add("G");
			posiblesLetras.add("F");
			posiblesLetras.add("D");
			posiblesLetras.add("S");
			posiblesLetras.add("A");
			posiblesLetras.add("Z");
			posiblesLetras.add("X");
			posiblesLetras.add("C");
			posiblesLetras.add("V");
			posiblesLetras.add("B");
			posiblesLetras.add("N");
			posiblesLetras.add("M");
			
			List<Integer> posiblesAños = new ArrayList<>();
			
			for (int i = 1930; i < 2005; i++) {
				posiblesAños.add(i);
				
			}
			
			
			for (int i = 10000000; i < 10000200; i++) {
				String nombre = posiblesNombres.get(random.nextInt(posiblesNombres.size()));
				String dni = i + posiblesLetras.get(random.nextInt(posiblesLetras.size()));
				String pin = String.valueOf(random.nextInt(200) + 1000);
				Double saldo = (double) random.nextInt(10000);
				int año = posiblesAños.get(random.nextInt(posiblesAños.size()));
				
				System.out.println(saldo);
				
				int p = new Random().nextInt(Provincia.values().length);
				Provincia pr = Provincia.values()[p];
				
				String sent = "INSERT INTO USUARIO(nom, dni, pin, saldoTotal, añoNac, provincia) VALUES ('" + nombre.toUpperCase() + "', '" + dni + "', '" + pin + "', " + saldo + ", " + año + ", '"+ pr + "');";
				st.execute(sent);
				us.add(new Usuario(nombre, dni, pin, saldo, año, pr));
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void borrarBD() {
		try(Statement st = con.createStatement()) {
			String sent = "delete from USUARIO";
			st.executeUpdate(sent);
			sent = "delete from CUENTA";
			st.executeUpdate(sent);
		} catch (Exception e){
			logger.log(Level.SEVERE, e.toString());
		}
	}
	
	public static boolean comprobarUsuarioExistente(String dni, String pin) {
		us = getUsuarios();
		for (Usuario usuario : us) {
			if(usuario.getDni().equals(dni)) {
				if(usuario.getPin().equals(pin)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Cuenta getCuentaConMayorSaldo(Usuario u) {
		Cuenta c = u.getCuentasUsuario().get(0);
		for (Cuenta cuenta : u.getCuentasUsuario()) {
			if(cuenta.getSaldo() > c.getSaldo()) {
				c = cuenta;
			}
		}
		return c;
	}
	}
	
	