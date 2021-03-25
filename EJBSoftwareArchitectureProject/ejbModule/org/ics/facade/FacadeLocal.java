package org.ics.facade;

import javax.ejb.Local;

import org.ics.ejb.Building;
import org.ics.ejb.Office;

@Local
public interface FacadeLocal {
	public Building findByAddress(String address);
    public Building createBuilding(Building building);
    public Building updateBuilding(Building building);
    public void deleteBuilding(String address);
    
    public Office findByOfficeId(String buildingAddress, String officeNumber);
    public Office createOffice(Office office);
    public Office updateOffice(Office office);
    public void deleteOffice(String buildingAddress, String officeNumber);
}
