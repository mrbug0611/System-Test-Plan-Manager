/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * class for test cases 
 * @author Matthew Williams 
 */
public class TestCase {
	
	/** id **/ 
	private String testCasdId; 
	
	/** type **/
	private String testType; 
	
	/** description **/
	private String description; 
	
	/** expected result **/
	private String expectedResult; 
	
	/** testplan **/ 
	private TestPlan testPlan; 
	
	/**
	 * test results 
	 */
	private ILog<TestResult> testResults; 
	
	/**
	 * constructor
	 * @param id of case
	 * @param type of case
	 * @param description of case
	 * @param result of case
	 * @throws IllegalArgumentException if any of parameters null or empty string
	 */
	public TestCase(String id, String type, String description, String result) throws IllegalArgumentException {
		
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		if (type == null || "".equals(type)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		if (description == null || "".equals(description)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		if (result == null || "".equals(result)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		
		this.testCasdId = id; 
		this.testType = type;
		this.description = description; 
		this.expectedResult = result; 
		this.testResults = new Log<TestResult>();
		this.testPlan = null; 
	}
	
	/**
	 * adds test result 
	 * @param passing condition of test result 
	 * @param result of test result 
	 */
	public void addTestResult(boolean passing, String result) {
		TestResult tr = new TestResult(passing, result);
		testResults.add(tr);
	}
	
	/**
	 * sees if test case if passing
	 * @return if passing or not 
	 */
	public boolean isTestCasePassing() {
		
		if (testResults.size() == 0) {
			return false; 
		}
		
		return testResults.get(testResults.size() - 1).isPassing();
		
	}
	
	/**
	 * get status of case
	 * @return status 
	 */
	public String getStatus() {
		
		
		if (isTestCasePassing()) {
			return TestResult.PASS; 
		}
		 return TestResult.FAIL;
		
	}
	
	/**
	 * gets actual result log of case
	 * @return actual result log 
	 */
	public String getActualResultsLog() {
		String results = "";
		for (int i = 0; i < testResults.size(); i++) {
			results += "- ";
			results += testResults.get(i).toString();
			results += "\n";
		}
		return results; 
	}

	/**
	 * sets test plan 
	 * @param testPlan the testPlan to set
	 * @throws IllegalArgumentException if parameter null
	 */
	public void setTestPlan(TestPlan testPlan) throws IllegalArgumentException{
		
		if (testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		
		this.testPlan = testPlan;
	}

	/**
	 * getter
	 * @return the testCasdId
	 */
	public String getTestCaseId() {
		return testCasdId;
	}

	/**
	 * getter
	 * @return the testType
	 */
	public String getTestType() {
		return testType;
	}

	/**
	 * getter
	 * @return the description
	 */
	public String getTestDescription() {
		return description;
	}

	/**
	 * getter
	 * @return the expectedResult
	 */
	public String getExpectedResults() {
		return expectedResult;
	}

	/**
	 * getter
	 * @return the testPlan
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}

	@Override
	public String toString() {
		return "# " + getTestCaseId() + "," + getTestType() + "\n"
				+ "* " + getTestDescription() + "\n"
				+ "* " + getExpectedResults() + "\n"
				+ getActualResultsLog();
	}
	
	
	
	
	
	
	

}
