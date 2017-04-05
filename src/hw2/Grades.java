package hw2;


public class Grades {
	/**
	 * @uml.property  name="name"
	 */
	String name;
	/**
	 * @uml.property  name="iD"
	 */
	String ID;
	/**
	 * @uml.property  name="lab1"
	 */
	public int lab1;
	/**
	 * @uml.property  name="lab2"
	 */
	public int lab2;
	/**
	 * @uml.property  name="lab3"
	 */
	public int lab3;
	/**
	 * @uml.property  name="midTerm"
	 */
	public int midTerm;
	/**
	 * @uml.property  name="finalExam"
	 */
	public int finalExam;
	/**
	 * @uml.property  name="totalGrade"
	 */
	public int totalGrade;
	public Grades(String ID, String name, int lab1, int lab2, int lab3, int midTerm, int finalExam){
			this.ID=ID;
			this.name=name;
			this.lab1=lab1;
			this.lab2=lab2;
			this.lab3=lab3;
			this.midTerm=midTerm;
			this.finalExam=finalExam;
			
	}
	/**method calculateTotalGrade--------------------------------------------
	 * 用來算總成績
	 * 
	 * @param weight  當下的加權比例
	 * @return integer totalGrade
	 * 
	 * Pseudo code:
	 * 透過成績*加權然後取四捨五入到整數，算出並更新總成績
	 * 回傳totalGrade
	 * 
	 * Time estimate:O(1)
	 * Example Grades物件.calculateTotalGrade(weight);傳回totalGrade;
	 -----------------------------------------------------------------------*/
	public int calculateTotalGrade(float[] weights){
		this.totalGrade = Math.round(lab1*weights[0]+
									 lab2*weights[1]+
									 lab3*weights[2]+
									 midTerm*weights[3]+
									 finalExam*weights[4]);
		return totalGrade;
	}
}
