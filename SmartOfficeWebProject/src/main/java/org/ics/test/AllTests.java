package org.ics.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestBuildingEJB_JUnitEE.class);
		suite.addTestSuite(TestOfficeEJB_JUnitEE.class);
		//$JUnit-END$ 
		return suite;
	}

}
