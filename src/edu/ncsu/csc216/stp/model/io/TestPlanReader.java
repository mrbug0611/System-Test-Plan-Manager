/**
 * 
 */
package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * class for reading files for test plan 
 * @author Matthew Williams 
 */
public class TestPlanReader {
	
	/**
	 * constructor
	 */
	public TestPlanReader() {
		// empty constructor 
	}
	
	/**
	 * reads file to get test plan 
	 * @param file to be read from 
	 * @return array of test plans 
	 * @throws IllegalArgumentException if unable to load file 
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File file)throws IllegalArgumentException{
		
		try {
			
			Scanner fileReader = new Scanner(new FileInputStream(file));
			
			ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
			String fileText;
			
			
			int iterate = 0; 
			
			String planName = "";
			TestPlan tp = null;
			TestPlan current = null; 
			TestCase currentCase = null; 
			fileText = fileReader.nextLine();
			boolean notOneLine = false; 
			



			while (fileReader.hasNextLine() ) {
				notOneLine = true; 
			if (iterate == 0 && fileText.charAt(0) != '!' ){
					throw new IllegalArgumentException("Unable to load file.");
			}
			
		

			

			if (fileText.charAt(0) == '!') {
				planName = fileText.substring(2);
				tp = new TestPlan(planName);
				testPlans.add(tp);
				current = tp; 
			}
			
			if (fileText.charAt(0) == '#') {
			
				String[] caseParameters = fileText.split(",");
			
				
				String id = caseParameters[0].substring(2);

				String type = caseParameters[1];

				
				
				String description = "";
				String result = "";
				
				int dIterate = 0; 
				
				while (true) {
					
					fileText = fileReader.nextLine();
					
					if (dIterate != 0 && fileText.charAt(0) == '*') {

						break; 
					}

				
					// if char at 0 is not * then add line to description 


					if (fileText.charAt(0) == '*') {
						description += fileText.substring(2); }
					
					else {
						description += "\n";
						description += fileText;
					}
					
				
					
					dIterate += 1; 

					

				}


				
		

				
				boolean anotherCase = false; 
				while (fileText.charAt(0) != '-') {

					
					if (fileText.charAt(0) == '#') {
						anotherCase = true; 
						break; 
						
					}


				
					
					
					String[] resultParameters = fileText.split("-");
					String expected = resultParameters[0];

				
					
					try {

					if (expected.charAt(0) == '*') {
						
						result += expected.substring(2); }
					
					else {
						result += "\n";
						result += expected; 
					}

					}
					
					
					catch (Exception e) {
						// do nothing
					}
					fileText = fileReader.nextLine();
}
				
				
				
				TestCase c = new TestCase(id, type, description, result);
				c.setTestPlan(tp);
			
				
				
				// add at end of method
				current.addTestCase(c);

				currentCase = c; 
				
				if (anotherCase) {
					continue; 
				}
				
				
				
			}
			
		
			if (fileText.charAt(0) == '-') {
				String[] results = fileText.split(":");
				String pass = results[0];
				String result = results[1];
				result = result.trim();
				
				boolean passing; 
				
				if ("PASS".equals(pass.substring(2))) {
					
					passing = true; 
				}
				
				else {
					passing = false; 
				}
				
				currentCase.addTestResult(passing, result);

			}
				
				iterate += 1;
				
				try {
				fileText = fileReader.nextLine(); }
				catch (Exception e) {
					//empty do nothing
				}

			}
			
			if (!notOneLine) {
				
				if (iterate == 0 && fileText.charAt(0) != '!' ){
					throw new IllegalArgumentException("Unable to load file.");
			}
			
				
				planName = fileText.substring(2);
				tp = new TestPlan(planName);
				testPlans.add(tp);
				current = tp; 
				
			}
			
			fileReader.close();

			return testPlans; 
			
		}
		
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
		
		
	}

}