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
		if(inputcommand.equals("G")||inputcommand.equals("R")||inputcommand.equals("W")){
			if(inputcommand.equals("G")){
				aGradeSystem.showGrade(IDI);
			}
			else if(inputcommand.equals("R")){
				int rank=aGradeSystem.showRank(IDI);
				System.out.println(username+"排名第"+rank);
				
			}
			else if(inputcommand.equals("W")){
				aGradeSystem.updateWeight(IDI);
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
}
