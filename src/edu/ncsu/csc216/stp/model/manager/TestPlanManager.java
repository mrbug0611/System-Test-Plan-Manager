/**
 * 
 */
package edu.ncsu.csc216.stp.model.manager;

import java.io.File;

import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * class for back end of test plan manager 
 * @author Matthew Williams 
 */
public class TestPlanManager {
	
	/** array of test plans **/
	private ISortedList<TestPlan> testPlans; 
	
	/** failing test **/
	private FailingTestList failing; 
	
	/** current test plan **/
	private AbstractTestPlan currentTestPlan; 
	
	/** if test is changed **/
	private boolean isChanged; 
	
	/**
	 * constructor
	 */
	public TestPlanManager() {
		this.testPlans = new SortedList<TestPlan>();
		failing = new FailingTestList();
		this.isChanged = false; 
		currentTestPlan = failing;  
		
	}
	
	/**
	 * load test plans from file 
	 * @param file to load from 
	 */
	public void loadTestPlans(File file) {
		testPlans = TestPlanReader.readTestPlansFile(file);
		setCurrentTestPlan(FailingTestList.FAILING_TEST_LIST_NAME);
		isChanged = true; 
		
	}
	
	/**
	 * save test plans to a file 
	 * @param file you save to 
	 */
	public void saveTestPlans(File file) {
		TestPlanWriter.writeTestPlanFile(file, testPlans);
		isChanged = false; 
	}
	
	/**
	 * gets is changed 
	 * @return isChanged field 
	 */
	public boolean isChanged() {
		return isChanged; 
	}
	
	/**
	 * add test plan to array 
	 * @param name of plan 
	 * @throws IllegalArgumentException if name if failing or a duplicate 
	 */
	public void addTestPlan(String name)  throws IllegalArgumentException{
		
		if (name.equals(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().toLowerCase().equals(name.toLowerCase())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		TestPlan tp = new TestPlan(name);
		testPlans.add(tp);
		setCurrentTestPlan(name);
		isChanged = true; 
		
		
		
		
		
	}
	
	/**
	 * gets test plan as array of strings
	 * @return test plan names 
	 */
	public String[] getTestPlanNames() {
		String[] names = new String[testPlans.size() + 1];
	
		names[0] = FailingTestList.FAILING_TEST_LIST_NAME;
	
		for (int i = 1; i < names.length; i++) {
			names[i] = testPlans.get(i - 1).getTestPlanName();
		}
		
		return names; 
	}
	
	/**
	 * gets current plan 
	 * @return the currentTestPlan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}

	/**
	 * setter for currentTestPlan
	 * @param testPlanName the currentTestPlan to set
	 */
	public void setCurrentTestPlan(String testPlanName) {
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(testPlanName)) {
				currentTestPlan = testPlans.get(i);
			}
		}
		
		
	}
	
	/**
	 * edits test plan 
	 * @param updateName of plan 
	 * @throws IllegalArgumentException if current test plan is failing test or new name is duplicate 
	 */
	public void editTestPlan(String updateName) throws IllegalArgumentException{
		
		if (currentTestPlan instanceof FailingTestList ) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		
		if (updateName.equals(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");

		}
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(updateName)) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		
		currentTestPlan.setTestPlanName(updateName);
		removeTestPlan();
		addTestPlan(updateName);
		
		isChanged = true; 
		
	}
	
	/**
	 * remove current test plan 
	 * @throws IllegalArgumentException if you try to remove failing test list
	 */
	public void removeTestPlan() {
		
		if (currentTestPlan instanceof FailingTestList ) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).equals(currentTestPlan)) {
				testPlans.remove(i);
			}
		}
		
		currentTestPlan = failing; 
		isChanged = true; 
		
	}
	
	/**
	 * adds test case to current plan 
	 * @param c test case to be added 
	 */
	public void addTestCase(TestCase c) {
		
		if (currentTestPlan instanceof TestPlan) {
			currentTestPlan.addTestCase(c);
			
			if (!c.isTestCasePassing()) {
				failing.addTestCase(c);
				isChanged = true; 
			}
		}
		
	}
	
	/**
	 * add test result  
	 * @param idx it will be added 
	 * @param passing if passing 
	 * @param actual result 
	 */
	public void addTestResult(int idx, boolean passing, String actual) {
		currentTestPlan.addTestResult(idx, passing, actual);
	}
	
	/**
	 * clears test plans array
	 */
	public void clearTestPlans() {
		
		this.testPlans = new SortedList<TestPlan>();
		failing = new FailingTestList();
		this.isChanged = false; 
		failing.clearTests();
		currentTestPlan = failing;  
		
	}

}