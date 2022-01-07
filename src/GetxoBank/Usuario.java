package GetxoBank;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class Usuario {
	private String nombre;
	private String dni;
	private String pin;
	private int saldoTotal;
	private Date fecha_nac;
	private Provincia provincia;
	private ArrayList<Cuenta> cuentasUsuario;


	public Usuario(String nom, String dni, String pin, int saldoTotal, Date fchaNcto , Provincia p, ArrayList<Cuenta> cuentasU) {
		// TODO Auto-generated constructor stub
		this.nombre = nom;
		this.dni = dni;
		this.pin = pin;
		this.saldoTotal = saldoTotal;
		this.fecha_nac = fchaNcto;
		this.provincia = p;
		this.cuentasUsuario = cuentasU;
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


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
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


	public ArrayList<Cuenta> getCuentasUsuario() {
		return cuentasUsuario;
	}


	public void setCuentasUsuario(ArrayList<Cuenta> cuentasUsuario) {
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
		return "Usuario [nombre=" + nombre + ", dni=" + dni + ", ContraseñaUsuario=" + pin
				+ ", saldoTotal=" + saldoTotal + ", fecha_nac=" + fecha_nac + ", provincia=" + provincia
				+ ", cuentasUsuario=" + cuentasUsuario + "]";
	}


	
	
	
	
	
	
	
	
	
}
	