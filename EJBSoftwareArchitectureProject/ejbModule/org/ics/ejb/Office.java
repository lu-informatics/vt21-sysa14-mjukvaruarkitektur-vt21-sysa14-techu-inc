package org.ics.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Office.findAll", query = "SELECT o FROM Office o")
})
@Table(name="Office")
public class Office implements Serializable{
	private String officeNumber;
	private int temperatureSetting;
	private String ventilationSetting;
	private Building building;
	private int ID;

	public Office(String ventilationSetting, int temperatureSetting, Building building) {
		this.ventilationSetting = ventilationSetting;
		this.temperatureSetting = temperatureSetting;
		this.building = building;
	}
	public Office() {
	}
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, updatable = false, insertable = false)
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="officeNumber", insertable = false, nullable = false)
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
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
	@JoinColumn(name="buildingAddress", referencedColumnName="address")
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
}
