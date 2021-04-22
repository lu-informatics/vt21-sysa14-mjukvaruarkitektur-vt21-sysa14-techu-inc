package org.ics.facade;

import java.util.List;

import javax.ejb.Local;

import org.ics.ejb.Building;
import org.ics.ejb.Office;

@Local
public interface FacadeLocal {
	public Building findByAddress(String address);
    public Building createBuilding(Building building);
    public Building updateBuilding(Building building);
    public void deleteBuilding(String address);
    public List<Building> getAllBuildings();
    
    public Office findByOfficeId(String officeNumber);
    public Office createOffice(Office office);
    public Office updateOffice(Office office);
    public void deleteOffice(String officeNumber);
    public List<Office> getAllOffices();
}
