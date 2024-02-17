/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

/**
 * class for test results 
 * @author Matthew Williams 
 */
public class TestResult {
	
	/** string for passing test **/ 
	public static final String PASS = "PASS";
	
	/** string for failing test **/ 
	public static final String FAIL = "FAIL";
	
	/** if test is passing**/ 
	private boolean passing; 
	
	/** actual result of test **/ 
	private String actualResult; 
	
	/**
	 * constructor
	 * @param passing if test is passing
	 * @param actual result of test 
	 * @throws IllegalArgumentException if actual null or empty string
	 */
	public TestResult(boolean passing, String actual)throws IllegalArgumentException {
		
		if (actual == null || "".equals(actual)) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		
		this.passing = passing; 
		this.actualResult = actual; 
		
	}
	
	/**
	 * sees if test is passing
	 * @return passing
	 */
	public boolean isPassing() {
		return passing;
		
	}

	/**
	 * gets actual result 
	 * @return the actualResult
	 */
	public String getActualResults() {
		return actualResult;
	}


	/**
	 * turns test result into string
	 * @return string of test result 
	 */
	@Override
	public String toString() {
		if (isPassing()) {
			return PASS + ": " + getActualResults();
		}
		return FAIL + ": " + getActualResults();
	}
	
	
	
	


}
