package org.ics.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ics.ejb.Office;
import org.ics.ejb.OfficeId;

/**
 * Session Bean implementation class OfficeEAOImpl
 */
@Stateless
public class OfficeEAOImpl implements OfficeEAOLocal {
	@PersistenceContext(unitName="LabEJBSql")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public OfficeEAOImpl() {
        // TODO Auto-generated constructor stub
    }
    public Office findByOfficeId(String buildingAddress, String officeNumber){
    	OfficeId id = new OfficeId(buildingAddress, officeNumber);
    	return em.find(Office.class, id);
    	}
    public Office createOffice(Office office) {
    	em.persist(office);
    	return office;
    	}
    public Office updateOffice(Office office) {
    	em.merge(office);
    	return office;
    	}
    public void deleteOffice(String buildingAddress, String officeNumber) {
    	Office office = this.findByOfficeId(buildingAddress, officeNumber);
    	if(office != null) {
    		em.remove(office);
    		}
    	}

}
