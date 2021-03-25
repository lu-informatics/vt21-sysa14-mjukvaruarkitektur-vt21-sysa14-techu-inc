package org.ics.ejb;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Embeddable
@SequenceGenerator(name="ID_SEQUENCE", allocationSize=1)
public class OfficeId implements Serializable{
	private String buildingAddress;
	private String officeNumber;
	
	public OfficeId() {}
	
	
	public OfficeId(String buildingAddress, String officeNumber) {
		this.buildingAddress = buildingAddress;
		this.officeNumber = officeNumber;
	}


	@Column(name="buildingAddress", nullable = false)
	@NotNull
	public String getBuildingAddress() {
		return buildingAddress;
	}
	public void setBuildingAddress(String buildingAddress) {
		this.buildingAddress = buildingAddress;
	}
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="officeNumber", insertable = false, nullable = false)
	@NotNull
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	
	public boolean equals(Object other) {
		if((this== other)) {
			return true;
			}
		if((other == null)){
			return false;
			}
		if(!(other instanceof OfficeId)){
			return false;
			}
		OfficeId castOther = (OfficeId) other;
		return((this.getBuildingAddress() == castOther.getBuildingAddress()) ||
				(this.getBuildingAddress() != null &&
				castOther.getBuildingAddress() != null &&
				this.getBuildingAddress().equals(castOther.getBuildingAddress()))) &&
				((this.getOfficeNumber() == castOther.getOfficeNumber()) || 
						(this.getOfficeNumber() != null && 
						castOther.getOfficeNumber() != null && 
						this.getOfficeNumber().equals(castOther.getOfficeNumber())));
	}

	public int hashCode() {
		return super.hashCode();
	}
}
