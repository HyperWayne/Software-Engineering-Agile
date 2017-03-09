package hw2;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;
import java.util.Scanner;

public class GradeSystem{
	
	
	
	float[] weights={0.1f,0.1f,0.1f,0.3f,0.4f};
	float[] new_weights=new float[5];
	String filePath = "C:/Users/���s�M/workspace/Software-Engineering-Agile/src/gradeinput.txt";
	//weights=new float[5];
	public LinkedList<Grades> alist;
	String line = null;
	String fileName = filePath.substring(0,filePath.indexOf("gradeinput.txt"));
	
	public  GradeSystem(){
	
		try{
			InputStreamReader fileReader = new InputStreamReader(new FileInputStream(filePath),"utf-8");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine())!=null){
				Scanner scanner = new Scanner (line);
				int lab1,lab2,lab3,mid,final_exam;
				String ID,name;
				ID=scanner.next();
				name=scanner.next();
				lab1=Integer.parseInt(scanner.next());
				lab2=Integer.parseInt(scanner.next());
				lab3=Integer.parseInt(scanner.next());
				mid=Integer.parseInt(scanner.next());
				final_exam=Integer.parseInt(scanner.next());
				Grades aGrade= new Grades(ID,name,lab1,lab2,lab3,mid,final_exam);
				aGrade.totalGrade=aGrade.calculateTotalGrade(weights);
				//System.out.println(ID);
				alist.add(aGrade);
				
			}
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to open file "+fileName);
		}
		catch(IOException ex){
			System.out.println("Error reading file:"+fileName);
		}
		
	}
	
	public boolean containsID(int ID){
		boolean flag;
		for(Iterator<Grades>it=alist.iterator();it.hasNext();){
			Grades current=((Grades)it.next());
			if(current.ID.equals(ID)){
				flag=true;
				break;
			}
		}
		flag=false;
		return flag;
	}
	
	public int showRank(String ID){
		int rank=1,TotalGrade=0;
		for(Iterator<Grades>it=alist.iterator();it.hasNext();){
			Grades current=((Grades)it.next());
			if(current.ID.equals(ID)){
				TotalGrade=current.totalGrade;
			}
		}
		for(Iterator<Grades>it=alist.iterator();it.hasNext();){
			Grades current=((Grades)it.next());
			if(current.totalGrade>TotalGrade){
				rank=rank+1;
			}
		}
		return rank;
	}
	
	public void showGrade(String ID){
		for(Iterator<Grades>it=alist.iterator();it.hasNext();){
			Grades current=((Grades)it.next());
			if(current.ID.equals(ID)){
				String lab1,lab2,lab3,mid_term,final_exam,total_grade;
				if(current.lab1<60)lab1=Integer.toString(current.lab1)+"*";
				else lab1=Integer.toString(current.lab1);
				if(current.lab2<60)lab2=Integer.toString(current.lab2)+"*";
				else lab2=Integer.toString(current.lab2);
				if(current.lab3<60)lab3=Integer.toString(current.lab3)+"*";
				else lab3=Integer.toString(current.lab3);
				if(current.midTerm<60)mid_term=Integer.toString(current.midTerm)+"*";
				else mid_term=Integer.toString(current.midTerm);
				if(current.finalExam<60)final_exam=Integer.toString(current.finalExam)+"*";
				else final_exam=Integer.toString(current.finalExam);
				if(current.totalGrade<60)total_grade=Integer.toString(current.totalGrade)+"*";
				else total_grade=Integer.toString(current.totalGrade);
				System.out.println(current.name+"���Z:"+"lab1:   "+lab1+"\n      lab2:   "+lab2+
				"\n      lab3:    "+lab3+"\n      mid-term: "+mid_term+"\n      final exam: "+final_exam+
				"\n     total grade: "+total_grade);
			}
		}
	}
	public void updateWeight(){
		showOldWeights();
		getNewWeights();
		setWeights(weights);
	}
	public void showOldWeights(){
		System.out.println("�°t��"+"\n  lab1   "+weights[0]*10+"%"+"\n  lab2   "+weights[1]*10+"%"+
				"\n  lab3   "+weights[2]*10+"%"+"\n  mid-term "+weights[3]*10+"%"+"\n final exam  "+weights[4]*10+"%");
	}
	public void getNewWeights(){
		Scanner scanner=new Scanner(System.in);
		new_weights[0]=scanner.nextFloat();
		new_weights[1]=scanner.nextFloat();
		new_weights[2]=scanner.nextFloat();
		new_weights[3]=scanner.nextFloat();
		new_weights[4]=scanner.nextFloat();
	}
	public void setWeights(float[] weights){
		System.out.println("�нT�{�s�t��"+"\n  lab1   "+new_weights[0]*10+"%"+"\n  lab2   "+new_weights[1]*10+"%"+
				"\n  lab3   "+new_weights[2]*10+"%"+"\n  mid-term "+new_weights[3]*10+"%"+"\n final exam  "+new_weights[4]*10+"%"+
				"\n�H�W���T��?Y(Yes)��N(No)");
		Scanner scanner=new Scanner(System.in);
		if(Integer.toString(scanner.nextInt()).equals("Y")){
			weights[0]=new_weights[0];
			weights[1]=new_weights[1];
			weights[2]=new_weights[2];
			weights[3]=new_weights[3];
			weights[4]=new_weights[4];
		}
		else {
			new_weights[0]=0;
			new_weights[1]=0;
			new_weights[2]=0;
			new_weights[3]=0;
			new_weights[4]=0;
		}
	}
	
	
		
}
