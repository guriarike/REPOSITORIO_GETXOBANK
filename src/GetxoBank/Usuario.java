package GetxoBank;

import java.util.Date;
import java.util.TreeSet;

public class Usuario {
	private String nombre;
	private String dni;
	private String ConstaseñaUsuario;
	private int saldoTotal;
	private Date fecha_nac;
	private TreeSet<Cuenta> cuentasUsuario;
	
	
	public Usuario(String nombre, String dni, String constaseñaUsuario, int saldoTotal, Date fecha_nac,
			TreeSet<Cuenta> cuentasUsuario) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		ConstaseñaUsuario = constaseñaUsuario;
		this.saldoTotal = saldoTotal;
		this.fecha_nac = fecha_nac;
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


	public String getConstaseñaUsuario() {
		return ConstaseñaUsuario;
	}


	public void setConstaseñaUsuario(String constaseñaUsuario) {
		ConstaseñaUsuario = constaseñaUsuario;
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
	
	
	
	
	
	
}
	