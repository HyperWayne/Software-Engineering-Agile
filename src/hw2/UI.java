package hw2;
import hw2.Main_extends_Object.NoSuchIDExceptions;
import hw2.Main_extends_Object.NoSuchCommandExceptions;
public class UI {
	String msg="tt";
	public UI(){
			
			try{
				GradeSystem aGradeSystem=new GradeSystem();
			}
			finally{
				
			}
	}
	public boolean checkID(int ID)throws NoSuchIDExceptions{
		/*boolean flag=false;
		for (Iterator<Grades> i = gs.gradeList.iterator(); i.hasNext();) {
			current=((Grades)i.next());
		      if(current.ID==ID){
		    	  this.currentID=ID;
		    	  flag=true;
		    	  break;
		      }
		}
		if(flag==false)throw new NoSuchIDExceptions(ID);
		return flag;*/
		return false;
	}
	public void promptCommand()throws NoSuchCommandExceptions{
		
	}
	public void promptID(){
		
	}
	public void showFinishMsg(){
		
	}
	public void showWelcomeMsg(){
		
	}
}
