/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * class for writing to file 
 * @author Matthew Williams 
 */
public class TestPlanWriter {
	
	/**
	 * constructor 
	 */
	public TestPlanWriter() {
		// empty constructor 
	}
	
	/**
	 * writes test plan array to file of choice 
	 * @param file to write to 
	 * @param plans test plan array that you will write to file 
	 * @throws IllegalArgumentException if unable to write to file 
	 */
	public static void writeTestPlanFile(File file, ISortedList<TestPlan> plans) throws IllegalArgumentException{
		
		
		try {
			PrintStream fileWriter = new PrintStream(file);
			
			for (int i = 0; i < plans.size(); i++) {
				fileWriter.println("! " + plans.get(i).getTestPlanName());
				for (int j = 0; j < plans.get(i).getTestCases().size(); j++) {
					fileWriter.print(plans.get(i).getTestCases().get(j).toString());
				}
			}
			
			fileWriter.close();
			
		
		}
		
		catch (Exception e) {
			throw new IllegalArgumentException("Unable to save to file.");
		}
		
	}

}
