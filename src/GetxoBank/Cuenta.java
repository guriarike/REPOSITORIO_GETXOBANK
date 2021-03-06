package GetxoBank;

import javax.swing.JOptionPane;

public class Cuenta {
	private String dni;
	private int numeroTarjeta;
	private double saldo;
	private TipoCuenta tipo;
	//TODO private Arraylist<Usuario> dueñoCuenta
	public Cuenta(String dni, int numeroTarjeta, double saldo, TipoCuenta tipo) {
		super();
		this.dni = dni;
		this.numeroTarjeta = numeroTarjeta;
		this.saldo = saldo;
		this.tipo = tipo;
	}
	
	public Cuenta() {
	}
	
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public TipoCuenta getTipo() {
		return tipo;
	}
	public void setTipo(TipoCuenta tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Cuenta [dni=" + dni + ", numeroTarjeta=" + numeroTarjeta + ", saldo=" + saldo + ", tipo=" + tipo
				+ "]";
	}
	//METODOS
	/**
	 * 
	 * @param cantidad
	 */
	public void editarSaldo(double cantidad) {
		double saldoActual = this.getSaldo();
		double saldoEditado = this.getSaldo() + cantidad;
		if (saldoEditado >= 0) {
			this.setSaldo(saldoEditado);
			
		}else {
			this.setSaldo(0);
	
			
		}};
	/**
	 * 
	 * 
	 * @param cantidad Esta es la cantidad que transferiremos
	 * @param c esta es la cuenta desde la que mandaremos el dinero
	 * @param c2 esta cuenta es la que recivira la transferencia 
	 * 
	 * 
	 */
	public void transferencia(double cantidad, Cuenta c , Cuenta c2) {
		if ( c2 == null ) {
			JOptionPane.showMessageDialog(null, "no existe la cuenta a la que usted pretende realizar una transferencia","alerta",JOptionPane.ERROR_MESSAGE);
			
		}
		if (c.getSaldo() >= saldo) {
			c.setSaldo(c.getSaldo()- cantidad);
			c2.setSaldo(c2.getSaldo() + cantidad);
			
		}else {
			JOptionPane.showMessageDialog(null, "USTED NO DISPONE DE SALDO SUFUCUIENTE EN ESTA CUENTA PARA REALIZAR ESTA TRANSFERENCIA","AVISO",JOptionPane.ERROR_MESSAGE);
		}	
		
	};
	/**
	 * 
	 * 
	 * @param c  Si la cuenta existe la borraremos haciendola igual a null.
	 */
	public void eliminarCuenta(Cuenta c) {
		if (c == null) {
			return;
		}else {
			c = null;
			
		}
		
	};
	
	
	
	

}
