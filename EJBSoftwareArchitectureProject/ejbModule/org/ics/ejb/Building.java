package org.ics.ejb;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
		@NamedQuery(name="Building.findAll", query = "SELECT b FROM Building b")
})
@Table(name="Building")
public class Building implements Serializable {
	private static final long serialVersionUID = 1L;
	private String address;
	private Set<Office> offices;

	public Building(String address) {
		this.address = address;
	}
	public Building() {
	}
	@Id
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "building")
	public Set<Office> getOffices() {
		return offices;
	}
	public void setOffices(Set<Office> offices) {
		this.offices = offices;
	}
	
}
