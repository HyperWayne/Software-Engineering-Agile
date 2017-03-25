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
						System.out.println("��J���O1)G��ܦ��Z(Grade)\n     2)R��ܱƦW(Rank)\n     3)A��ܥ���(Average)\n     4)W��s�t��(Weight)\n    "
								+ " 5)E���}���(Exit)");
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
	* �ΨӽT�{���d�ߪ�ID�O�_�s�b��GradeSystems�t�Τ�
	*
	* @param ID     �ΨӬd�ߪ�ID
	* @return �@�ӥ��L�ȡA�Y��true�h�N��GradeSystems���o����ơA�Yfalse�h�_
	*
	* @throws NoSuchIDExceptions �V 
	*			�Y�ϥΪ̿�J��ID���bGradeSystems���h�ߥXNoSuchIDExceptions
	*Pseudo code:
	*1.�naGradeSystem ��containsID(ID) �� ID �O�_�t�b aGradeSystem�� 
	*2.if not, throw an object of NoSuchIDExceptions
	*3.�^�� true
    *
	* Time estimate : O (n)
	* Example: UI����.checkID(962001044) ; �Ǧ^���G�� true
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
	* �n�JID�᪺�D����
	*
	*
	* @return �@�ӥ��L�ȡA�Y��true�h�N��ϥΪ̭n�h�X�A�h�^��UI�غc�l
	*
	* @throws NoSuchCommandExceptions �V 
	*			�Y�ϥΪ̿�J��Command���b�ﶵ���h�ߥXNoSuchCommandExceptions
	*Pseudo code:
	*1.�P�_�ϥΪ̿�J��command�O�_�X�k 
	*2.if not, throw an object of NoSuchCommandExceptions
	*3.else �̾ڿ�J�����O�b���U�h�h��
    *
	* Time estimate : O (1)
	* Example: UI����.promptCommand() ; ����Y��E����Ǧ^���G�� true
	----------------------------------------------------------------------------------------------------------*/

	public boolean promptCommand()throws NoSuchCommandExceptions{
		inputcommand=scanner.next();
		inputcommand=inputcommand.toUpperCase();
		System.out.println("�ϥΪ̿�J:"+inputcommand);
		boolean flag=true;
		if(inputcommand.equals("G")||inputcommand.equals("R")||inputcommand.equals("W")||inputcommand.equals("A")){
			if(inputcommand.equals("G")){
				aGradeSystem.showGrade(IDI);
			}
			else if(inputcommand.equals("R")){
				int rank=aGradeSystem.showRank(IDI);
				System.out.println(username+"�ƦW��"+rank);
				
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
	 * ���ܨϥΪ̿�JID��Q
	 * 
	 * 
	 * Psedo code:
	 * ����
	 * 
	 * Time estimate:O(1)
	 * Example UI����.promptID();����
	 -----------------------------------------------------------------------*/
	public void promptID(){
		System.out.println("��JID��Q(�����ϥ�)");
	}
	/*method showFinishMsg----------------------------------------------------
	 *�ϥΪ̿�JQ�ɡA��BYEBYE
	 * 
	 * 
	 * Psedo code:
	 * ���}�T��
	 * 
	 * Time estimate:O(1)
	 * Example UI����.showFinishMsg();���}�T��
	 -----------------------------------------------------------------------*/
	public void showFinishMsg(){
		System.out.println("Bye");
		System.exit(0);
	}
	/*method showWelcomeMsg----------------------------------------------------
	 * �ϥΪ̵n�J���۩I
	 * 
	 * 
	 * Psedo code:
	 * ���۩I
	 * 
	 * Time estimate:O(1)
	 * Example UI����.showWelcomeMsg();�w��T��
	 -----------------------------------------------------------------------*/
	public void showWelcomeMsg(String ID){
		
			System.out.println("Welcome "+username);	
	}
	/*method showAverage------------------------------------------------------
	 * ��ܥ���
	 * 
	 * 
	 * Psedo code:
	 * 1�p�G�O�Ĥ@���i�J�hcall calculateAve�⥭��
	 * 2�B�_�h�N��X���e��n������
	 * 
	 * Time estimate:O(1)
	 * Example UI����.promptID();
	 -----------------------------------------------------------------------*/
	public void showAverage(){
		if(to==0){
			calculateAve();
			System.out.println("�`����"+"\n  lab1   "+a_lab1+"\n  lab2   "+a_lab2+
					"\n  lab3   "+a_lab3+"\n  mid-term "+a_mid+"\n final exam  "+
					a_final+"\n totalGrade  "+a_total);
		}
		else{
			System.out.println("�`����"+"\n  lab1   "+a_lab1+"\n  lab2   "+a_lab2+
					"\n  lab3   "+a_lab3+"\n  mid-term "+a_mid+"\n final exam  "+
					a_final+"\n totalGrade  "+a_total);
		}
		
	}
	/*method calculateAve-----------------------------------------------------
	 * ���`����
	 * 
	 * 
	 * Psedo code:
	 * 1�@�hfor�j���X�U���[�`
	 * 2���H�H��
	 * 
	 * Time estimate:O(n)
	 * Example UI����.calculateAve();
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
