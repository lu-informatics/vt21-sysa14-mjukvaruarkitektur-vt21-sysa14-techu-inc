package org.ics.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Office")
public class Office implements Serializable{
	private OfficeId officeId;
	private int temperatureSetting;
	private String ventilationSetting;
	private Building building;
	private int ID;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", nullable = false, updatable = false, insertable = false)
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	@EmbeddedId
	public OfficeId getOfficeId() {
		return officeId;
	}
	public void setOfficeId(OfficeId officeId) {
		this.officeId = officeId;
	}
	
	@Column(name = "temperatureSetting")
	public int getTemperatureSetting() {
		return temperatureSetting;
	}
	public void setTemperatureSetting(int temperatureSetting) {
		this.temperatureSetting = temperatureSetting;
	}
	
	@Column(name = "ventilationSetting")
	public String getVentilationSetting() {
		return ventilationSetting;
	}
	public void setVentilationSetting(String ventilationSetting) {
		this.ventilationSetting = ventilationSetting;
	}
	
	@ManyToOne
	@JoinColumn(name="buildingAddress", referencedColumnName="address",
			nullable = false, insertable = false, updatable = false)
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
}
