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
	/* method  checkID  ----------------------------------------------------------------------------------                                                                                                    
	* 用來確認欲查詢的ID是否存在於GradeSystems系統內
	*
	* @param ID     用來查詢的ID
	* @return 一個布林值，若為true則代表GradeSystems有這筆資料，若false則否
	*
	* @throws NoSuchIDExceptions – 
	*			若使用者輸入的ID不在GradeSystems內則拋出NoSuchIDExceptions
	*Pseudo code:
	*1.要aGradeSystem 做containsID(ID) 看 ID 是否含在 aGradeSystem內 
	*2.if not, throw an object of NoSuchIDExceptions
	*3.回傳 true
    *
	* Time estimate : O (n)
	* Example: UI物件.checkID(962001044) ; 傳回結果為 true
	----------------------------------------------------------------------------------------------------------*/

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
	/* method  promptCommand  ----------------------------------------------------------------------------------                                                                                                    
	* 登入ID後的主介面
	*
	*
	* @return 一個布林值，若為true則代表使用者要退出，則回到UI建構子
	*
	* @throws NoSuchCommandExceptions – 
	*			若使用者輸入的Command不在選項中則拋出NoSuchCommandExceptions
	*Pseudo code:
	*1.判斷使用者輸入的command是否合法 
	*2.if not, throw an object of NoSuchCommandExceptions
	*3.else 依據輸入的指令在往下層去做
    *
	* Time estimate : O (1)
	* Example: UI物件.promptCommand() ; 直到吃到E為止都傳回結果為 true
	----------------------------------------------------------------------------------------------------------*/

	public boolean promptCommand()throws NoSuchCommandExceptions{
		inputcommand=scanner.next();
		inputcommand=inputcommand.toUpperCase();
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
			aGradeSystem.initialize_Weights();
			
			to=0;
			flag=false;
			return flag;
		}
		else 
			throw  new NoSuchCommandExceptions(inputcommand);	
		
		return true;
	}
	/*method promptID-------------------------------------------------------
	 * 指示使用者輸入ID或Q
	 * 
	 * 
	 * Psedo code:
	 * 介面
	 * 
	 * Time estimate:O(1)
	 * Example UI物件.promptID();介面
	 -----------------------------------------------------------------------*/
	public void promptID(){
		System.out.println("輸入ID或Q(結束使用)");
	}
	/*method showFinishMsg----------------------------------------------------
	 *使用者輸入Q時，說BYEBYE
	 * 
	 * 
	 * Psedo code:
	 * 離開訊息
	 * 
	 * Time estimate:O(1)
	 * Example UI物件.showFinishMsg();離開訊息
	 -----------------------------------------------------------------------*/
	public void showFinishMsg(){
		System.out.println("Bye");
		System.exit(0);
	}
	/*method showWelcomeMsg----------------------------------------------------
	 * 使用者登入打招呼
	 * 
	 * 
	 * Psedo code:
	 * 打招呼
	 * 
	 * Time estimate:O(1)
	 * Example UI物件.showWelcomeMsg();歡迎訊息
	 -----------------------------------------------------------------------*/
	public void showWelcomeMsg(String ID){
		
			System.out.println("Welcome "+username);	
	}
	/*method showAverage------------------------------------------------------
	 * 顯示平均
	 * 
	 * 
	 * Psedo code:
	 * 1如果是第一次進入則call calculateAve算平均
	 * 2、否則就輸出之前算好的平均
	 * 
	 * Time estimate:O(1)
	 * Example UI物件.promptID();
	 -----------------------------------------------------------------------*/
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
	/*method calculateAve-----------------------------------------------------
	 * 算總平均
	 * 
	 * 
	 * Psedo code:
	 * 1一層for迴圈算出各項加總
	 * 2除以人數
	 * 
	 * Time estimate:O(n)
	 * Example UI物件.calculateAve();
	 -----------------------------------------------------------------------*/
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
