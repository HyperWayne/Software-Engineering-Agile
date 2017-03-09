package hw2;
import hw2.Main_extends_Object.NoSuchIDExceptions;

import java.util.Iterator;
import java.util.Scanner;

import hw2.Main_extends_Object.NoSuchCommandExceptions;
public class UI {
	String msg="tt";
	String currentID="";
	Scanner scanner=new Scanner(System.in);
	String inputcommand;
	String IDI;
	public GradeSystem aGradeSystem;
	public UI()throws NoSuchIDExceptions,NoSuchCommandExceptions{
			
			try{
				 aGradeSystem=new GradeSystem();
				while(true){
					//Scanner scanner=new Scanner(System.in);
					
					IDI=scanner.next();
					if(IDI.equals("Q"))break;
					promptID();
					if(checkID(IDI)){
						showWelcomeMsg(IDI);
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
		    	  break;
		      }
		}
		if(flag==false)throw new NoSuchIDExceptions(ID);
		return flag;
	}
	public boolean promptCommand()throws NoSuchCommandExceptions{
		inputcommand=scanner.next();
		boolean flag=true;
		if(!inputcommand.equals("G")||!inputcommand.equals("R")||!inputcommand.equals("W")||
				!inputcommand.equals("E"))throw  new NoSuchCommandExceptions(inputcommand);
		if(inputcommand.equals("E")){
			flag=false;
			return flag;
		}
		else {
			if(inputcommand.equals("G")){
				aGradeSystem.showGrade(IDI);
			}
			else if(inputcommand.equals("R")){
				aGradeSystem.showRank(IDI);
			}
			else if(inputcommand.equals("W")){
				aGradeSystem.showGrade(IDI);
			}
		}
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
		System.out.println("Welcome"+ID);
	}
}
