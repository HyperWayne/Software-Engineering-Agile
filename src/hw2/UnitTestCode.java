package hw2;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTestCode {
	
	GradeSystem gradeSystem = null;
	
	Grades aGrade = null;
	
	UI aUI = null;
	
	/* case1 */
	float[] weights1={0.1f, 0.1f, 0.1f, 0.3f,0.4f};
	/* case2 */
	float[] weights2={0.2f, 0.2f, 0.2f, 0.2f,0.2f};
	/* case3 */
	float[] weights3={0.1f, 0.1f, 0.0f, 0.4f,0.4f};
	
	
	@Before
	public void setUp() throws Exception {
		gradeSystem = new GradeSystem();
		aGrade = new Grades("962001051","李威廷", 81, 32, 50, 90, 93);
		aUI = new UI();
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
	
	/*
	/** ------------------------------------------------------------------------------------------------
	 *  Unit test for UI
	 *  
	 *  promptID
	 *  showFinishMsg
	 *
	 *  Case1 : 
	 *   - Enter ID, or press Q to quit.
	 *   - Q
	 *   - Finish, bye!
	 ----------------------------------------------------------------------------------------------------- 
	@Test
	public void testUI1() {	
		System.out.println("bugs");

		final ByteArrayInputStream  inContent = new ByteArrayInputStream("Q". getBytes());
		System.setIn (inContent);
		
		final  ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut (new PrintStream (outContent));
		
		aUI.showFinishMsg();
		assertEquals("Finish, bye!\n", outContent);
	}
	 */
	
	
	/** ------------------------------------------------------------------------------------------------
	 * Unit test for GradeSystem
	 *
	 * showRank
	 *  - case 1: 975002026 37
     *  - case 2: 975002039 16
	 * 
	 * showGrade
	 * 
	 * updateWeight
	 * 
	----------------------------------------------------------------------------------------------------- */

	@Test
	public void testShowRank1(){
		assertEquals(37, gradeSystem.showRank("975002026"));
	}
	
	@Test
	public void testShowRank2(){
		assertEquals(64, gradeSystem.showRank("962001051"));
	}
	
	
}
