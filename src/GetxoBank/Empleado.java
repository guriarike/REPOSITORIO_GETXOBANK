package GetxoBank;

public class Empleado {
	private String NSS;
	private  String nombre;
	private int sueldoAnual;
	private String urlFoto;
	private String informacionLaboral;
	public Empleado(String nSS, String nombre, int sueldoAnual, String urlFoto, String informacionLaboral) {
		super();
		NSS = nSS;
		this.nombre = nombre;
		this.sueldoAnual = sueldoAnual;
		this.urlFoto = urlFoto;
		this.informacionLaboral = informacionLaboral;
	}
	public String getNSS() {
		return NSS;
	}
	public void setNSS(String nSS) {
		NSS = nSS;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getSueldoAnual() {
		return sueldoAnual;
	}
	public void setSueldoAnual(int sueldoAnual) {
		this.sueldoAnual = sueldoAnual;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public String getInformacionLaboral() {
		return informacionLaboral;
	}
	public void setInformacionLaboral(String informacionLaboral) {
		this.informacionLaboral = informacionLaboral;
	}
	

}