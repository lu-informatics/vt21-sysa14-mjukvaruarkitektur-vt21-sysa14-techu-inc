package org.ics.test;

import org.ics.ejb.Building;
import org.ics.ejb.Office;

import junit.framework.TestCase;

public class TestFacadeEJB_JUnitEE_Office extends TestCase {

	public TestFacadeEJB_JUnitEE_Office(String name) {
		super(name);
	}

	String expectedOfficeNumber;
	String expectedBuildingAddress;
	String expectedVentilationSetting;
	int expectedTemperatureSetting;
	
	Building building1;
	
	Office office1;

	protected void setUp() throws Exception {
		super.setUp();
		expectedBuildingAddress = "Kungens Kurva";
		
		expectedVentilationSetting = "V3";
		expectedTemperatureSetting = 22;
		expectedOfficeNumber = "O00101";
		
		//No need for context.lookup nor the local facade EJB interface because
		//persisting entities is not practicable for entities with 
		//auto-generated primary keys, in this case office entities.
		
		//Please refer to https://bit.ly/3gUqLFn
		//Preet Sangha: Stackoverflow, Aug 24th, 2009.
		
		building1 = new Building(expectedBuildingAddress);
		office1 = new Office(expectedOfficeNumber, expectedVentilationSetting, expectedTemperatureSetting,building1); 
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		building1 = null;
		office1 = null;
	}


	public void testOfficeTemperatureSetting() {
		assertEquals(office1.getTemperatureSetting(), expectedTemperatureSetting);
	}
	public void testOfficeVentilationSetting() {
		assertEquals(office1.getVentilationSetting(), expectedVentilationSetting);
	}
	public void testOfficeBuildingAddress() {
		assertEquals(office1.getBuilding().getAddress(), expectedBuildingAddress);
	}
	public void testOfficeNumber() {
		assertEquals(office1.getOfficeNumber(), expectedOfficeNumber);
	}

}
