package org.ics.eao;

import java.util.List;

import javax.ejb.Local;

import org.ics.ejb.Building;

@Local
public interface BuildingEAOLocal {
	public Building findByAddress(String address);
    public Building createBuilding(Building building);
    public Building updateBuilding(Building building);
    public void deleteBuilding(String address);
    public List<Building> getAllBuildings();
}
