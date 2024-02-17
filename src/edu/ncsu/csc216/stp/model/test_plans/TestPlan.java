/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * class for test plan 
 * @author Matthew Williams 
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	
	
	/**
	 * constructor
	 * @param name of plan 
	 * @throws IllegalArgumentException if name null or empty string
	 */
	public TestPlan(String name)  throws IllegalArgumentException{
		super(name);
		
		if (name == null || "".equals(name) || FailingTestList.FAILING_TEST_LIST_NAME.toLowerCase().equals(super.getTestPlanName().toLowerCase())) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}

	/**
	 * gets test cases as 2d string array 
	 * @return test cases as 2d string array 
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		
		String[][] cases = new String[super.getTestCases().size()][3];
		
		for (int i = 0; i < super.getTestCases().size(); i++) {
			cases[i][0] = super.getTestCases().get(i).getTestCaseId();
			cases[i][1] = super.getTestCases().get(i).getTestType();
			cases[i][2] = super.getTestCases().get(i).getStatus();
			
		}
		return cases; 
	}
	
	/**
	 * add test case 
	 * @param c test case to be added 
	 */
	@Override
	public void addTestCase(TestCase c) {
		super.addTestCase(c);
		c.setTestPlan(this);
	
	}

	/**
	 * compares elements in array 
	 * @param o object that we are comparing
	 * @return int that describes if element is greater or less than 
	 */
	@Override
	public int compareTo(TestPlan o) {
		return super.getTestPlanName().toLowerCase().compareTo(o.getTestPlanName().toLowerCase());
	}
	
	

}
