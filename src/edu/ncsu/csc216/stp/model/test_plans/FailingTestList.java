/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * failing tests class 
 * @author Matthew Williams 
 */
public class FailingTestList extends AbstractTestPlan {
	
	/** failing test list name **/
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";

	/**
	 * constructor 
	 * @throws IllegalArgumentException if null or empty string
	 */
	public FailingTestList() throws IllegalArgumentException {
		super(FAILING_TEST_LIST_NAME);
	}

	/**
	 * gets test case information as a 2d array of strings
	 * @return 2d string array 
	 */
	@Override
	public String[][] getTestCasesAsArray() {
	String[][] cases = new String[super.getTestCases().size()][3];
		
		for (int i = 0; i < super.getTestCases().size(); i++) {
			cases[i][0] = super.getTestCases().get(i).getTestCaseId();
			cases[i][1] = super.getTestCases().get(i).getTestType();
			cases[i][2] = "";
			
		}
		return cases; 
	}
	
	/**
	 * add test case 
	 * @param c test case to be added 
	 * @throws IllegalArgumentException if test case passing
	 */
	@Override
	public void addTestCase(TestCase c) throws IllegalArgumentException{
		
		if (!c.isTestCasePassing()) {
			super.addTestCase(c);
		}
		else {
		
			throw new IllegalArgumentException("Cannot add passing test case.");
			}
	}
	
	/**
	 * setter 
	 * @param testPlanName the testPlanName to set
	 * @throws IllegalArgumentException if test plan name is a constant 
	 */
	@Override
	public void setTestPlanName(String testPlanName)  throws IllegalArgumentException{
		
		if (!testPlanName.equals(FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		}
		
		super.setTestPlanName(testPlanName);
		
	}
	
	/**
	 * clears test plan
	 */
	public void clearTests() {
		
		while (super.getTestCases().size() > 0) {
			super.getTestCases().remove(0);
		}
		
	}

	

}
