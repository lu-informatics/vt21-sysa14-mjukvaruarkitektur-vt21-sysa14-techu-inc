package org.ics.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ics.ejb.Building;

/**
 * Session Bean implementation class BuildingEAOImpl
 */
@Stateless
public class BuildingEAOImpl implements BuildingEAOLocal {
	@PersistenceContext(unitName="LabEJBSql")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public BuildingEAOImpl() {
        // TODO Auto-generated constructor stub
    }
    public Building findByAddress(String address){
    	return em.find(Building.class, address);
    }
    public Building createBuilding(Building building) {
    	em.persist(building);
    	return building;
    }
    public Building updateBuilding(Building building) {
    	em.merge(building);
    	return building;
    }
    public void deleteBuilding(String address) {
    	Building building = this.findByAddress(address);
    	if(building != null) {
    		em.remove(building);
    	}
    }
    public List<Building> getAllBuildings(){
    	TypedQuery<Building> tq = em.createNamedQuery("Building.findAll", Building.class);
    	return tq.getResultList();
    }
}
