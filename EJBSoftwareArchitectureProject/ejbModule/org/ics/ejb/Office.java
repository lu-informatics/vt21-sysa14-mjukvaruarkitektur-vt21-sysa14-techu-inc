package org.ics.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Office")
public class Office implements Serializable{
	private OfficeId officeId;
	private int temperatureSetting;
	private String ventilationSetting;
	private Building building;
	
	
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
