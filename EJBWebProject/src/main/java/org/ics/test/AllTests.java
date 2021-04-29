package org.ics.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestFacadeEJB_JUnitEE.class);
		suite.addTestSuite(TestFacadeEJB_JUnitEE_Office.class);
		//$JUnit-END$
		return suite;
	}

}
