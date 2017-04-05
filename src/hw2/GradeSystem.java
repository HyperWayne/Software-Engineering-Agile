package hw2;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class GradeSystem{
	
	
	
	/**
	 * @uml.property  name="weights" multiplicity="(0 -1)" dimension="1"
	 */
	float[] weights={0.1f, 0.1f, 0.1f, 0.3f,0.4f};
	/**
	 * @uml.property  name="new_weights" multiplicity="(0 -1)" dimension="1"
	 */
	float[] new_weights=new float[5];
	/**
	 * @uml.property  name="filePath"
	 */
	String filePath = "src/gradeinput.txt";
	/**
	 * @uml.property  name="alist"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="hw2.Grades"
	 */
	public LinkedList<Grades> alist=new LinkedList<Grades>();
	/**
	 * @uml.property  name="line"
	 */
	String line = null;
	/**
	 * @uml.property  name="fileName"
	 */
	String fileName = filePath.substring(0,filePath.indexOf("gradeinput.txt"));
	double a_lab1=0, a_lab2=0, a_lab3=0, a_mid=0, a_total=0, a_final=0;
	
	public  GradeSystem(){
			
		try{
			InputStreamReader fileReader = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
			@SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine())!=null){
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner (line);
				int lab1, lab2, lab3, mid, final_exam;
				String ID, name;
				ID = scanner.next();
				name = scanner.next();
				if(name.length()<=1){
					name=name+scanner.next();	
				}
				lab1=Integer.parseInt(scanner.next());
				lab2=Integer.parseInt(scanner.next());
				lab3=Integer.parseInt(scanner.next());
				mid=Integer.parseInt(scanner.next());
				final_exam=Integer.parseInt(scanner.next());
				Grades aGrade= new Grades(ID,name,lab1,lab2,lab3,mid,final_exam);
				aGrade.totalGrade=aGrade.calculateTotalGrade(weights);		
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
	
	/** method showRank-------------------------------------------------------
	 *  用來算排名
	 * 
	 *  @param ID  當下的使用者
	 *  @return integer rank
	 * 
	 *  Pseudo code:
	 *  透過兩個for迴圈，一個for先找出使用者的totalGrade，另一個算rank
	 *  回傳rank
	 * 
	 *  Time estimate:O(2*n)=O(n)
	 *  Example GradeSystem物件.showRank(ID);回傳rank
	 -----------------------------------------------------------------------*/
	
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
	
	/** method showGrade-------------------------------------------------------
	 *  輸出使用者的成績
	 * 
	 *  @param ID  當下的使用者
	 * 
	 * 
	 *  Pseudo code:
	 *  透過if else判斷是否低於60分，低於會在成績後標註個*
	 *  輸出使用者成績列表
	 * 
	 *  Time estimate:O(1)
	 *  Example GradeSystem物件.showGrade(ID);輸出成績
	 -----------------------------------------------------------------------*/
	
	public void showGrade(String ID){
		for(Iterator<Grades>it=alist.iterator();it.hasNext();){
			Grades current=((Grades)it.next());
			if(current.ID.equals(ID)){
				String lab1, lab2, lab3, mid_term, final_exam, total_grade;
				if(current.lab1<60) lab1=Integer.toString(current.lab1)+"*";
				else lab1=Integer.toString(current.lab1);
				if(current.lab2<60) lab2=Integer.toString(current.lab2)+"*";
				else lab2=Integer.toString(current.lab2);
				if(current.lab3<60) lab3=Integer.toString(current.lab3)+"*";
				else lab3=Integer.toString(current.lab3);
				if(current.midTerm<60) mid_term=Integer.toString(current.midTerm)+"*";
				else mid_term=Integer.toString(current.midTerm);
				if(current.finalExam<60) final_exam=Integer.toString(current.finalExam)+"*";
				else final_exam=Integer.toString(current.finalExam);
				if(current.totalGrade<60) total_grade=Integer.toString(current.totalGrade)+"*";
				else total_grade=Integer.toString(current.totalGrade);
				System.out.println(current.name+" Score:"+
				"\nlab1: "+lab1+
				"\nlab2: "+lab2+
				"\nlab3: "+lab3+
				"\nmid-term: "+mid_term+
				"\nfinal exam: "+final_exam+
				"\ntotal grade: "+total_grade);
			}
		}
	}
	/** method updateWeight---------------------------------------------------------
	 *  用來算處理輸入配分
	 * 
	 *  @param ID  當下的使用者
	 * 
	 * 
	 *  Pseudo code:
	 *  呼叫showOldWeights印出舊配分
	 *  再call getNewWeights接使用者輸入的新成績
	 *  最後setWeights(weights,ID)確認使用者輸入的新配分，並更新所有人的totalGrade
	 * 
	 *  Time estimate:O(1)
	 *  Example GradeSystem物件.updateWeight(ID);進入改加權狀態
	 -----------------------------------------------------------------------*/
	public void updateWeight(){
		showOldWeights();
		getNewWeights();
		setWeights(weights);
	}
	/** method showOldWeights--------------------------------------------------
	 *  用來印出當下的加權
	 * 
	 * 
	 *  Pseudo code:
	 *  System.out.println(舊加權)
	 * 
	 *  Time estimate:O(1)
	 *  Example GradesSystem物件.showOldWeights();輸出舊配分
	 -----------------------------------------------------------------------*/
	private void showOldWeights(){
		System.out.println("Old Weighted Scores"+
				"\nlab1: "+Math.round(weights[0]*100)+"%"+
				"\nlab2: "+Math.round(weights[1]*100)+"%"+
				"\nlab3: "+Math.round(weights[2]*100)+"%"+
				"\nmid-term: "+Math.round(weights[3]*100)+"%"+
				"\nfinal exam: "+Math.round(weights[4]*100)+"%");
		System.out.println("\nPlease type new five weights, e.g, 0.1 0.2 0.3 0.4 0.5");
	}
	/** method getNewWeights---------------------------------------------------
	 *  用來接使用者輸入的新配分
	 * 
	 * 
	 * 
	 * 
	 *  Pseudo code:
	 *  透過Scanner來接使用者新輸入的加權，然後將值放進暫存的float [] new_weights裡
	 *  最後印出使用者新輸入的配分(須為小數)
	 * 
	 *  Time estimate:O(1)
	 *  Example GradeSystem物件.getNewWeights();輸入新配分，之後印出新配分
	 -----------------------------------------------------------------------*/
	@SuppressWarnings("resource")
	private void getNewWeights() throws InputMismatchException{
		
		Scanner scanner=new Scanner(System.in);
		
		new_weights[0]=scanner.nextFloat();
		new_weights[1]=scanner.nextFloat();
		new_weights[2]=scanner.nextFloat();
		new_weights[3]=scanner.nextFloat();
		new_weights[4]=scanner.nextFloat();
		
		System.out.println(
				"New Weighted Scores"+
				"\nlab1: "+Math.round(new_weights[0]*100)+
				"\nlab2: "+Math.round(new_weights[1]*100)+
				"\nlab3: "+Math.round(new_weights[2]*100)+
				"\nmid-term: "+Math.round(new_weights[3]*100)+
				"\nfinal exam: "+Math.round(new_weights[4]*100));
			
	}
	/** method setWeights------------------------------------------------------
	 *  用來確認使用者新輸入的配分，並更新所有人的totalGrade
	 * 
	 * 
	 * 
	 * 
	 *  Pseudo code:
	 *  輸出新配分，if使用者輸入Y確認的話就去用for迴圈更新所有人的totalGrade，並更新weights
	 *  else 則保持原樣，讓new_weights設為0
	 * 
	 *  Time estimate:O(n)
	 *  Example GradeSystem物件.setWeights(weights,id);接確認指令，更新weights、totalGrade
	 -----------------------------------------------------------------------*/
	private void setWeights(float[] weights){
		
		System.out.println("New Weighted Score Confirmation: "+
				"\nlab1: "+Math.round(new_weights[0]*100)+"%"+
				"\nlab2: "+Math.round(new_weights[1]*100)+"%"+
				"\nlab3: "+Math.round(new_weights[2]*100)+"%"+
				"\nmid-term: "+Math.round(new_weights[3]*100)+"%"+
				"\nfinal exam: "+Math.round(new_weights[4]*100)+"%"+
				"\nCorrect? Press Y(Yes) or N(No)");
		
		Scanner scanner=new Scanner(System.in);
		
		if(scanner.next().toUpperCase().equals("Y")){
			weights[0]=new_weights[0];
			weights[1]=new_weights[1];
			weights[2]=new_weights[2];
			weights[3]=new_weights[3];
			weights[4]=new_weights[4];
			
			for(Iterator<Grades>it=alist.iterator();it.hasNext();){
				Grades cur=it.next();
				cur.totalGrade=cur.calculateTotalGrade(weights);			
			}
		}
		else {
			new_weights[0]=0.1f;
			new_weights[1]=0.1f;
			new_weights[2]=0.1f;
			new_weights[3]=0.3f;
			new_weights[4]=0.4f;
		}
		System.out.println("Set Weights Successful!");
	}
	/** method initialize_Weights-----------------------------------------------
	 *  當使用者登出，重置配分、totalGrade
	 * 
	 * 
	 * 
	 * 
	 *  Pseudo code:
	 *  initialize weights、totalGrade
	 * 
	 *  Time estimate:O(n)
	 *  Example GradeSystem物件.initialize_Weights();初始化weights、totalGrade
	 -----------------------------------------------------------------------*/
	public void initialize_Weights(){
		weights[0]=0.1f;
		weights[1]=0.1f;
		weights[2]=0.1f;
		weights[3]=0.3f;
		weights[4]=0.4f;
		for(Iterator<Grades>it=alist.iterator();it.hasNext();){
			Grades cur=it.next();
			
			cur.totalGrade=cur.calculateTotalGrade(weights);
			
		}
	}
	/** method showAverage------------------------------------------------------
	 *  顯示平均
	 * 
	 * 
	 *  Pseudo code:
	 *  1. 如果是第一次進入則call calculateAve算平均
	 *  2. 否則就輸出之前算好的平均
	 * 
	 *  Time estimate:O(1)
	 *  Example GradeSystem物件.showAverage();
	 -----------------------------------------------------------------------*/
	public void showAverage(){
			calculateAve();
			System.out.println("Total Average:"+
					"\nlab1: "+a_lab1+
					"\nlab2: "+a_lab2+
					"\nlab3: "+a_lab3+
					"\nmid-term: "+a_mid+
					"\nfinal exam: "+a_final+
					"\ntotal grade: "+a_total);
		
	}
	
	/** method calculateAve-----------------------------------------------------
	 *  算總平均
	 * 
	 * 
	 *  Pseudo code:
	 *  1.一層for迴圈算出各項加總
	 *  2.除以人數
	 * 
	 *  Time estimate:O(n)
	 *  Example GradeSystem物件.calculateAve();
	 -----------------------------------------------------------------------*/
	private void calculateAve(){
		int person=0;
		
		for(Iterator<Grades>i=alist.iterator();i.hasNext();){
			Grades cur=((Grades)i.next());
			a_lab1=a_lab1+cur.lab1;
			a_lab2=a_lab2+cur.lab2;
			a_lab3=a_lab3+cur.lab3;
			a_mid=a_mid+cur.midTerm;
			a_final=a_final+cur.finalExam;
			a_total=a_total+cur.totalGrade;
			person++;
		}	

		a_lab1 = Math.round(a_lab1/person*100);	
		a_lab1 /= 100;
		a_lab2 = Math.round(a_lab2/person*100);
		a_lab2 /= 100;
		a_lab3 = Math.round(a_lab3/person*100);
		a_lab3 /= 100;
		a_mid = Math.round(a_mid/person*100);
		a_mid /=100;
		a_final = Math.round(a_final/person*100);
		a_final /=100;
		a_total = Math.round(a_total/person*100);
		a_total /= 100;
	}

	
	
		
}
