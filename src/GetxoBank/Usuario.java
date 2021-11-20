package GetxoBank;

import java.util.Date;
import java.util.TreeSet;

public class Usuario {
	private String nombre;
	private String dni;
	private String ContraseñaUsuario;
	private int saldoTotal;
	private Date fecha_nac;
	private Provincia provincia;
	private TreeSet<Cuenta> cuentasUsuario;
	
	
	public Usuario(String nombre, String dni, String contraseñaUsuario, int saldoTotal, Date fecha_nac, Provincia provincia,
			TreeSet<Cuenta> cuentasUsuario) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		ContraseñaUsuario = contraseñaUsuario;
		this.saldoTotal = saldoTotal;
		this.fecha_nac = fecha_nac;
		this.provincia = provincia;
		this.cuentasUsuario = cuentasUsuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getContraseñaUsuario() {
		return ContraseñaUsuario;
	}


	public void setContraseñaUsuario(String contraseñaUsuario) {
		ContraseñaUsuario = contraseñaUsuario;
	}


	public int getSaldoTotal() {
		return saldoTotal;
	}


	public void setSaldoTotal(int saldoTotal) {
		this.saldoTotal = saldoTotal;
	}


	public Date getFecha_nac() {
		return fecha_nac;
	}


	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}


	public TreeSet<Cuenta> getCuentasUsuario() {
		return cuentasUsuario;
	}


	public void setCuentasUsuario(TreeSet<Cuenta> cuentasUsuario) {
		this.cuentasUsuario = cuentasUsuario;
	}


	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	
	
	
	
	
	
	
}
	