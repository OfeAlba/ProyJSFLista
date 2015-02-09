package es.open4job.aytozgz.opendata.model.vo;

import java.util.Date;


public class AparcamientosPublicosVO {
	private Punto punto;
	private String horario;
	private String title;
	private String accesos;
	private Date lastUpdated;
	private String accesoPeaton;
	private String accesoVehiculo;
	private int id;
	public AparcamientosPublicosVO(Punto punto, String horario, String title,
			String accesos, Date lastUpdated, String accesoPeaton,
			String accesoVehiculo, int id) {
		super();
		this.punto = punto;
		this.horario = horario;
		this.title = title;
		this.accesos = accesos;
		this.lastUpdated = lastUpdated;
		this.accesoPeaton = accesoPeaton;
		this.accesoVehiculo = accesoVehiculo;
		this.id = id;
	}
	public Punto getPunto() {
		return punto;
	}
	public void setPunto(Punto punto) {
		this.punto = punto;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAccesos() {
		return accesos;
	}
	public void setAccesos(String accesos) {
		this.accesos = accesos;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getAccesoPeaton() {
		return accesoPeaton;
	}
	public void setAccesoPeaton(String accesoPeaton) {
		this.accesoPeaton = accesoPeaton;
	}
	public String getAccesoVehiculo() {
		return accesoVehiculo;
	}
	public void setAccesoVehiculo(String accesoVehiculo) {
		this.accesoVehiculo = accesoVehiculo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(){
		return ("Punto: " + punto + ", horario: " + horario + ", titulo:" +
				 title + ", accesos: " + accesos + ", última actualizacion: " +
				lastUpdated + ", acceso peatones: " + accesoPeaton + ", acceso "
						+ "vehiculos: " + accesoVehiculo + ", id: " + id);
	}
}
	
