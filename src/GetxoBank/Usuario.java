package GetxoBank;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class Usuario {
	private String nombre;
	private String dni;
	private String pin;
	private double saldoTotal;
	private int año_nac;
	private Provincia provincia;
	private ArrayList<Cuenta> cuentasUsuario;


	public Usuario(String nom, String dni, String pin, double saldoTotal, int añoNcto , Provincia p, ArrayList<Cuenta> cuentasU) {
		// TODO Auto-generated constructor stub
		this.nombre = nom;
		this.dni = dni;
		this.pin = pin;
		for (Cuenta cuenta : cuentasU) {
			this.saldoTotal += cuenta.getSaldo();
		}
		this.año_nac = añoNcto;
		this.provincia = p;
		this.cuentasUsuario = cuentasU;
	}
	
	public Usuario(String nombre, String dni, String pin, double saldoTotal,int añoNac, Provincia provincia) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.pin = pin;
		this.saldoTotal = saldoTotal;
		this.año_nac = añoNac;
		this.provincia = provincia;
	}

	public Usuario(String dni, ArrayList<Cuenta> cuentas) {
		super();
		this.dni = dni;
		this.cuentasUsuario = cuentas;
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


	public double getSaldoTotal() {
		return saldoTotal;
	}


	public void setSaldoTotal(double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}


	public int getAño_nac() {
		return año_nac;
	}


	public void setAño_nac(int año_nac) {
		this.año_nac = año_nac;
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
				+ ", saldoTotal=" + saldoTotal + ", fecha_nac=" + año_nac + ", provincia=" + provincia
				+ ", cuentasUsuario=" + cuentasUsuario + "]";
	}


	
	
	
	
	
	
	
	
	
}
	