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


	public Usuario(String e, String n, String d, String a, int t, String c, int i, String v) {
		// TODO Auto-generated constructor stub
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



	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", dni=" + dni + ", ContraseñaUsuario=" + ContraseñaUsuario
				+ ", saldoTotal=" + saldoTotal + ", fecha_nac=" + fecha_nac + ", provincia=" + provincia
				+ ", cuentasUsuario=" + cuentasUsuario + "]";
	}


	
	
	
	
	
	
	
	
	
}
	