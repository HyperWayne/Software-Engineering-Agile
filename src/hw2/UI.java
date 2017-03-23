package hw2;
import hw2.Main_extends_Object.NoSuchIDExceptions;

import java.util.Iterator;
import java.util.Scanner;

import hw2.Main_extends_Object.NoSuchCommandExceptions;
public class UI extends Main_extends_Object{
	String msg="tt";
	String currentID="";
	Scanner scanner=new Scanner(System.in);
	String inputcommand;
	String IDI;
	String username;
	int a_lab1=0,a_lab2=0,a_lab3=0,a_mid=0,a_final=0,a_total=0,to=0;
	public GradeSystem aGradeSystem;
	public UI()throws NoSuchIDExceptions,NoSuchCommandExceptions{
			
			try{
				 aGradeSystem=new GradeSystem();
				
				while(true){
					//Scanner scanner=new Scanner(System.in);
					
					
					promptID();
					IDI=scanner.next();
					if(IDI.equals("Q"))break;
					if(checkID(IDI)){
						showWelcomeMsg(IDI);
						System.out.println("輸入指令1)G顯示成績(Grade)\n     2)R顯示排名(Rank)\n     3)A顯示平均(Average)\n     4)W更新配分(Weight)\n    "
								+ " 5)E離開選單(Exit)");
					}
					while(promptCommand()){
					
					}
					
				}
				showFinishMsg();
			}
			finally{
				
			}
	}
	public boolean checkID(String ID)throws NoSuchIDExceptions{
		boolean flag=false;
		for (Iterator<Grades> i = aGradeSystem.alist.iterator(); i.hasNext();) {
			Grades current=((Grades)i.next());
		      if(current.ID.equals(ID)){
		    	  this.currentID=ID;
		    	  flag=true;
		    	  username=current.name;
		    	  break;
		      }
		}
		System.out.println(ID);
		if(flag==false)throw new NoSuchIDExceptions(ID);
		return flag;
	}
	public boolean promptCommand()throws NoSuchCommandExceptions{
		inputcommand=scanner.next();
		System.out.println("使用者輸入:"+inputcommand);
		boolean flag=true;
		if(inputcommand.equals("G")||inputcommand.equals("R")||inputcommand.equals("W")||inputcommand.equals("A")){
			if(inputcommand.equals("G")){
				aGradeSystem.showGrade(IDI);
			}
			else if(inputcommand.equals("R")){
				int rank=aGradeSystem.showRank(IDI);
				System.out.println(username+"排名第"+rank);
				
			}
			else if(inputcommand.equals("W")){
				aGradeSystem.updateWeight(IDI);
				to=0;
			}
			else if(inputcommand.equals("A")){
				showAverage();
			}
			
		}
		else if(inputcommand.equals("E")){
			flag=false;
			return flag;
		}
		else 
			throw  new NoSuchCommandExceptions(inputcommand);	
		
		return true;
	}
	public void promptID(){
		System.out.println("輸入ID或Q(結束使用)");
	}
	public void showFinishMsg(){
		System.out.println("Bye");
		System.exit(0);
	}
	public void showWelcomeMsg(String ID){
		
			System.out.println("Welcome "+username);	
	}
	public void showAverage(){
		if(to==0){
			calculateAve();
			System.out.println("總平均"+"\n  lab1   "+a_lab1+"\n  lab2   "+a_lab2+
					"\n  lab3   "+a_lab3+"\n  mid-term "+a_mid+"\n final exam  "+
					a_final+"\n totalGrade  "+a_total);
		}
		else{
			System.out.println("總平均"+"\n  lab1   "+a_lab1+"\n  lab2   "+a_lab2+
					"\n  lab3   "+a_lab3+"\n  mid-term "+a_mid+"\n final exam  "+
					a_final+"\n totalGrade  "+a_total);
		}
		
	}
	public void calculateAve(){
		int person=0;
		for(Iterator<Grades>i=aGradeSystem.alist.iterator();i.hasNext();){
			Grades cur=((Grades)i.next());
			a_lab1=a_lab1+cur.lab1;
			a_lab2=a_lab2+cur.lab2;
			a_lab3=a_lab3+cur.lab3;
			a_mid=a_mid+cur.midTerm;
			a_final=a_final+cur.finalExam;
			a_total=a_total+cur.totalGrade;
			person++;
		}
		to=1;
		a_lab1=a_lab1/person;
		a_lab2=a_lab2/person;
		a_lab3=a_lab3/person;
		a_mid=a_mid/person;
		a_final=a_final/person;
		a_total=a_total/person;
	}
}
