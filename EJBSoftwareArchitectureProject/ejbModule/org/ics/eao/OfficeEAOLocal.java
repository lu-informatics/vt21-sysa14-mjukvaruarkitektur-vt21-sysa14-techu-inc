package org.ics.eao;

import java.util.List;

import javax.ejb.Local;

import org.ics.ejb.Office;

@Local
public interface OfficeEAOLocal {
	public Office findByOfficeNumber(String officeNumber);
    public Office createOffice(Office office);
    public Office updateOffice(Office office);
    public void deleteOffice(String officeNumber);
    public List<Office> getAllOffices();
}
