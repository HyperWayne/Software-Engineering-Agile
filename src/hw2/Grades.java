package hw2;

public class Grades {
	String name,ID;
	int lab1,lab2,lab3,midTerm,finalExam,totalGrade;
	
	public Grades(){
		
	}
	public int calculateTotalGrade(int weights){
		totalGrade = totalGrade+lab1+lab2+lab3+midTerm+finalExam;
		return totalGrade;
	}
}
