package hw2;


public class Grades {
	String name,ID;
	public int lab1,lab2,lab3,midTerm,finalExam,totalGrade;
	public Grades(String ID,String name,int lab1,int lab2,int lab3,int midTerm,int finalExam){
			this.ID=ID;this.name=name;this.lab1=lab1;this.lab2=lab2;this.lab3=lab3;this.midTerm=midTerm;this.finalExam=finalExam;
			
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
