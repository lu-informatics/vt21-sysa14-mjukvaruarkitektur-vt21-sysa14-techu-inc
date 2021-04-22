package org.ics.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.ics.eao.BuildingEAOLocal;
import org.ics.eao.OfficeEAOLocal;
import org.ics.ejb.Building;
import org.ics.ejb.Office;

/**
 * Session Bean implementation class Facade
 */
@Stateless
public class Facade implements FacadeLocal {
	@EJB
	OfficeEAOLocal officeEAO;
	@EJB
	BuildingEAOLocal buildingEAO;

	/**
	 * Default constructor.
	 */
	public Facade() {
		// TODO Auto-generated constructor stub
	}

	public Building findByAddress(String address) {
		return buildingEAO.findByAddress(address);
	}

	public Building createBuilding(Building building) {
		return buildingEAO.createBuilding(building);
	}

	public Building updateBuilding(Building building) {
		return buildingEAO.updateBuilding(building);
	}

	public void deleteBuilding(String address) {
		buildingEAO.deleteBuilding(address);
	}
	public List<Building> getAllBuildings(){
		return buildingEAO.getAllBuildings();
	}

	public Office findByOfficeId(String officeNumber) {
		return officeEAO.findByOfficeId(officeNumber);
	}

	public Office createOffice(Office office) {
		return officeEAO.createOffice(office);
	}

	public Office updateOffice(Office office) {
		return officeEAO.updateOffice(office);
	}

	public void deleteOffice(String officeNumber) {
		officeEAO.deleteOffice(officeNumber);
	}
	public List<Office> getAllOffices(){
		return officeEAO.getAllOffices();
	}
	

}
