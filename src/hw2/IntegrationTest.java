package hw2;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.InputMismatchException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 *  case 1 - Construct a new student data to test 
	 *  public class Grades
	 * 
	 */
	
	@Test
	public void integrationTest1(){
		float[] weights1={0.1f, 0.1f, 0.1f, 0.3f,0.4f};
		Grades gradeSystem = new Grades("962001051","李威廷", 81, 32, 50, 90, 93);
		assertEquals(81, gradeSystem.calculateTotalGrade (weights1));
	}
	
	/**
	 *  case 2 - Test every public method of 
	 *  
	 *  public class GradeSystem
	 *  
	 *		-ID = 985002201
	 *  In one test, testing all public methods.
	 */
	
	@Test
	public void integrationTest2() throws InputMismatchException, FileNotFoundException, IOException{
		GradeSystem gradeSystem = null;
		gradeSystem = new GradeSystem();
		String ID = "985002201";
		
		/* public method showGrade */
		String expected_grade = "蘇亮 Score:\nlab1: 81\nlab2: 91\nlab3: 85\nmid-term: 84\nfinal exam: 90\ntotal grade: 87\n";
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent));
		gradeSystem.showGrade(ID);
		assertEquals(expected_grade, outContent.toString());	
		
		/* public method showRank */
		assertEquals(46, gradeSystem.showRank(ID));
		
		/* public method  updateWeigths*/
		/* Please enter 0.2 / 0.2 / 0.2 / 0.2 / 0.2 */
		String expected_weight = "蘇亮 Score:\nlab1: 81\nlab2: 91\nlab3: 85\nmid-term: 84\nfinal exam: 90\ntotal grade: 86\n";
		gradeSystem.updateWeight();
		final  ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent2));
		gradeSystem.showGrade(ID);
		assertEquals(expected_weight, outContent.toString());
		
		/* public method showAverage */
		String expected_average = "Total Average:\nlab1: 90.32\nlab2: 87.71\nlab3: 89.1\nmid-term: 89.52\nfinal exam: 89.73\ntotal grade: 89.57\n";
		final  ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent3));
		gradeSystem.showAverage();
		assertEquals(expected_average,outContent.toString());		
	}


}
