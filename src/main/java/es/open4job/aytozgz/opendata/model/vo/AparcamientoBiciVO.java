package es.open4job.aytozgz.opendata.model.vo;

import java.sql.Date;

public class AparcamientoBiciVO {

	private int id;
	private Punto punto;	
	private String title;
	private String icon;
	private Date lastUpdated;
	
	public AparcamientoBiciVO(int id,Punto punto,String title,String icon,Date lasTUpdated) {
		this.id = id;
		this.punto = punto;
		this.title = title;
		this.icon = icon;
		this.lastUpdated = lasTUpdated;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	
	
}
