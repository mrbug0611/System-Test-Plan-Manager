/**
 * 
 */
package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;

/**
 * abstract class for test plan 
 * @author Matthew Williams 
 */
public abstract class AbstractTestPlan {
	
	/** name **/
	private String testPlanName; 
	
	/** array of test cases **/ 
	private ISwapList<TestCase> testCases; 
	
	/**
	 * constructor 
	 * @param name of test plan 
	 * @throws IllegalArgumentException if name empty string or null 
	 */
	public AbstractTestPlan(String name)  throws IllegalArgumentException{
		
		if (name == null || "".equals(name) ) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		
		this.testPlanName = name; 
		this.testCases = new SwapList<TestCase>();
	}
	
	/**
	 * gets test cases 
	 * @return array of test cases 
	 */
	public ISwapList<TestCase> getTestCases(){
		return testCases;
		
	}
	
	/**
	 * adds test cases 
	 * @param c for a particular test case 
	 */
	public void addTestCase(TestCase c) {
		testCases.add(c);
	}
	
	/**
	 * remove test case 
	 * @param idx of test case 
	 * @return removed test case 
	 */
	public TestCase removeTestCase(int idx) {
		return testCases.remove(idx);
		
	}
	
	/**
	 * gets test case 
	 * @param idx of test case
	 * @return gotten test case 
	 */
	public TestCase getTestCase(int idx) {
		return testCases.get(idx);
		
	}
	
	/**
	 * gets number of failing tests 
	 * @return amount of failing tests 
	 */
	public int getNumberOfFailingTests() {
		int failing = 0; 
		
		for (int i = 0; i < testCases.size(); i++) {
			if (!testCases.get(i).isTestCasePassing()) {
				failing += 1; 
			}
		}
		
		return failing;
		
	}
	
	/**
	 * adds test result 
	 * @param idx it will be added 
	 * @param passing if test passing
	 * @param actualResults of test 
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		testCases.get(idx).addTestResult(passing, actualResults);
		
	}
	
	/**
	 * gets test cases as 2d string array 
	 * @return test cases as 2d string array 
	 */
	public abstract String[][] getTestCasesAsArray();

	/**
	 * gets plan name 
	 * @return the testPlanName
	 */
	public String getTestPlanName() {
		return testPlanName;
	}

	/**
	 * setter 
	 * @param testPlanName the testPlanName to set
	 * @throws IllegalArgumentException if testPlanName is null or empty string
	 */
	public void setTestPlanName(String testPlanName)  throws IllegalArgumentException{
		
		if (testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		this.testPlanName = testPlanName;
	}

	/**
	 * hash code 
	 * @return hash 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
		return result;
	}

	/**
	 * sees if 2 classes are equal 
	 * @param obj that we are comparing
	 * @return if equals 
	 */
	@Override
	public boolean equals(Object obj) {

		AbstractTestPlan other = (AbstractTestPlan) obj;
		if (testPlanName == null) {
			if (other.testPlanName != null)
				return false;
		} else if (!testPlanName.toLowerCase().equals(other.testPlanName.toLowerCase()))
			return false;
		return true;
	}
	
	
	
	
}
