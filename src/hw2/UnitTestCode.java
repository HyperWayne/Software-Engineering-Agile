package hw2;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTestCode {
	
	/**
	 * @uml.property  name="gradeSystem"
	 * @uml.associationEnd  
	 */
	GradeSystem gradeSystem = null;
	
	/**
	 * @uml.property  name="aGrade"
	 * @uml.associationEnd  
	 */
	Grades aGrade = null;
	
	/**
	 * @uml.property  name="aUI"
	 * @uml.associationEnd  
	 */
	UI aUI = null;
	
	/* case1 */
	/**
	 * @uml.property  name="weights1" multiplicity="(0 -1)" dimension="1"
	 */
	float[] weights1={0.1f, 0.1f, 0.1f, 0.3f,0.4f};
	/* case2 */
	/**
	 * @uml.property  name="weights2" multiplicity="(0 -1)" dimension="1"
	 */
	float[] weights2={0.2f, 0.2f, 0.2f, 0.2f,0.2f};
	/* case3 */
	/**
	 * @uml.property  name="weights3" multiplicity="(0 -1)" dimension="1"
	 */
	float[] weights3={0.1f, 0.1f, 0.0f, 0.4f,0.4f};
	
	
	@Before
	public void setUp() throws Exception {
		gradeSystem = new GradeSystem();
		aGrade = new Grades("962001051","李威廷", 81, 32, 50, 90, 93);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/** ------------------------------------------------------------------------------------------------
	 * Unit test for Grades
	 *
	 * testCalculateTotalGrade 
	 *  -case 1: lab1 0.1  lab2 0.1  lab3 0.1  midTerm 0.3  finalExam 0.4
	 *  -case 2: lab1 0.2  lab2 0.2  lab3 0.2  midTerm 0.2  finalExam 0.2
	 *  -case 3: lab1 0.1  lab2 0.1  lab3 0.0  midTerm 0.4  finalExam 0.4
	----------------------------------------------------------------------------------------------------- */
	
	/*Expected result (81*0.1 + 32*0.1 + 50*0.1 + 90*0.3 + 93*0.4 = 80.5, round = 81)*/
	@Test
	public void testCalculateTotalGrade1() {	
		assertEquals(81, aGrade.calculateTotalGrade (weights1));
	}
	
	/*Expected result (81*0.2 + 32*0.2 + 50*0.2 + 90*0.2 + 93*0.2 = 69.2, round = 69)*/
	@Test
	public void testCalculateTotalGrade2() {	
		assertEquals(69, aGrade.calculateTotalGrade (weights2));
	}
	
	/*Expected result (81*0.1 + 32*0.1 + 50*0.0 + 90*0.4 + 93*0.4 = 84.5, round = 85)*/
	@Test
	public void testCalculateTotalGrade3() {	
		assertEquals(85, aGrade.calculateTotalGrade (weights3));
	}	
	
	/** ------------------------------------------------------------------------------------------------
	 * Unit test for GradeSystem
	 *
	 * showRank
	 *  - case 1: 985002201 46
     *  - case 2: 985002501 27
	 * 
	 * showGrade
	 * 
	 * updateWeight
	 * 
	 * showAverage
	 * 
	----------------------------------------------------------------------------------------------------- */

	
	@Test
	public void testShowRank1(){
		assertEquals(46, gradeSystem.showRank("985002201"));
	}
	
	@Test
	public void testShowRank2(){
		assertEquals(27, gradeSystem.showRank("985002501"));
	}
	

	/** ------------------------------------------------------------------------------------------------
	 *  Unit test for GradeSystem
	 *
	 * showRank
	 * 
	 * showGrade 
	 * 		case 1: 
	 * 		- 985002201 蘇  亮 81 91 85 84 90 
	 * 		- 0.1; 0.1; 0.1; 0.3; 0.4
	 * 		- total score: 86.9 => 87
	 * 
	 * 		case 2: 
	 * 		- 985002501 林佩穎 93 83 94 91 89
	 * 		- 0.1; 0.1; 0.1; 0.3; 0.4
	 * 		- total score: 89.9 = > 90
	 * 
	 * updateWeight
	 * 
	 * showAverage
	 * 
	 ------------------------------------------------------------------------------------------------*/	
	
	@Test
	public void testShowGrade1() {
		String expected = "蘇亮 Score:\nlab1: 81\nlab2: 91\nlab3: 85\nmid-term: 84\nfinal exam: 90\ntotal grade: 87\n";
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent));
		gradeSystem.showGrade("985002201");
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testShowGrade2() {
		String expected = "林佩穎 Score:\nlab1: 93\nlab2: 83\nlab3: 94\nmid-term: 91\nfinal exam: 89\ntotal grade: 90\n";
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent));
		gradeSystem.showGrade("985002501");
		assertEquals(expected, outContent.toString());
	}
	/** ------------------------------------------------------------------------------------------------
	 * Unit test for GradeSystem
	 *
	 * showRank
	 * 
	 * showGrade
	 * 
	 * updateWeight
	 * 		case 1: 
	 * 		- Manual Key in 0.2 / 0.2 / 0.2 / 0.2 / 0.2
	 * 		- 985002201 蘇  亮 81 91 85 84 90  
	 * 		- total score: 86.2 => 86 
	 * 
	 *		 case 2:
	 * 		- Manual Key in 0.4 / 0.3 / 0.0 / 0.1 / 0.2
	 *  	- 985002501 林佩穎 93 83 94 91 89
	 * 		- total score: 89 => 89
	 * 
	 * showAverage
	 * 
	 ------------------------------------------------------------------------------------------------*/
	
	@Test
	public void testUpdateWeights1(){
		String expected = "蘇亮 Score:\nlab1: 81\nlab2: 91\nlab3: 85\nmid-term: 84\nfinal exam: 90\ntotal grade: 86\n";
		gradeSystem.updateWeight();
		
		/*final ByteArrayInputStream  in = new ByteArrayInputStream("0.2 0.2 0.2 0.2 0.2\nY\n".getBytes());
		System.setIn (in); */
		
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent));
		gradeSystem.showGrade("985002201");
		assertEquals(expected, outContent.toString());	
		}
	@Test
	public void testUpdateWeights2(){
		String expected = "林佩穎 Score:\nlab1: 93\nlab2: 83\nlab3: 94\nmid-term: 91\nfinal exam: 89\ntotal grade: 89\n";
		gradeSystem.updateWeight();
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent));
		gradeSystem.showGrade("985002501");
		assertEquals(expected, outContent.toString());	
		}
	
	/** ------------------------------------------------------------------------------------------------
	 * Unit test for GradeSystem
	 *
	 * showRank
	 * 
	 * showGrade
	 * 
	 * updateWeight
	 * 
	 * showAverage
	 * 		case 1:
	 * 		- one case will do, because the average stays the same as long as the data never change
	 * 
	 ------------------------------------------------------------------------------------------------*/
	@Test
	public void testShowAverage1(){
		String expected = "Total Average:\nlab1: 90.32\nlab2: 87.71\nlab3: 89.1\nmid-term: 89.52\nfinal exam: 89.73\ntotal grade: 89.57\n";
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent));
		gradeSystem.showAverage();
		assertEquals(expected,outContent.toString());
	}
	
}
