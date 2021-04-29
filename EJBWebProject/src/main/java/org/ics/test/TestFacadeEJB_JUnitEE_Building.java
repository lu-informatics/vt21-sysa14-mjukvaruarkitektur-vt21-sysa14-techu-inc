package org.ics.test;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.ics.ejb.Building;
import org.ics.facade.FacadeLocal;

import junit.framework.TestCase;

public class TestFacadeEJB_JUnitEE_Building extends TestCase {

	public TestFacadeEJB_JUnitEE_Building(String name) {
		super(name);
	}

	FacadeLocal facadeLocal;

	String expectedaddress;

	Building building1;
	Building  building2;

	protected void setUp() throws Exception {
		super.setUp();

		expectedaddress = "Haparanda";

		Context context = new InitialContext();
		facadeLocal = (FacadeLocal) context.lookup("java:app/EJBSoftwareArchitectureProject/Facade!org.ics.facade.FacadeLocal");

		building1 = new Building(expectedaddress);

	}

	protected void tearDown() throws Exception {
		super.tearDown();

		facadeLocal.deleteBuilding(expectedaddress);
		facadeLocal = null;

		building1 = null;
		building2 = null;
	}

	public void testFacadeBuilding() {

		building1 = facadeLocal.createBuilding(building1);
		building2 = facadeLocal.findByAddress(expectedaddress);
		assertEquals(building1.getAddress(), expectedaddress);
		assertEquals(building2.getAddress(), expectedaddress);

	}

}
