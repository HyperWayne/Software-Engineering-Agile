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
	public LinkedList<Grades> alist=new LinkedList<Grades>();
	String line = null;
	String fileName = filePath.substring(0,filePath.indexOf("gradeinput.txt"));
	
	public  GradeSystem(){
			//System.out.println("test");
		try{
			InputStreamReader fileReader = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine())!=null){
				Scanner scanner = new Scanner (line);
				int lab1,lab2,lab3,mid,final_exam;
				String ID,name;
				ID=scanner.next();
				name=scanner.next();
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
	
	/*public boolean containsID(String ID){
		boolean flag;
		for(Iterator<Grades>it=alist.iterator();it.hasNext();){
			Grades current=((Grades)it.next());
			if(current.ID.equals(ID)){
				flag=true;
				return flag;
			}
		}
		flag=false;
		return flag;
	}*/
	/*method showRank-------------------------------------------------------
	 * �ΨӺ�ƦW
	 * 
	 * @param ID  ��U���ϥΪ�
	 * @return integer rank
	 * 
	 * Psedo code:
	 * �z�L���for�j��A�@��for����X�ϥΪ̪�totalGrade�A�t�@�Ӻ�rank
	 * �^��rank
	 * 
	 * Time estimate:O(2*n)=O(n)
	 * Example GradeSystem����.showRank(ID);�^��rank
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
	/*method showGrade-------------------------------------------------------
	 * ��X�ϥΪ̪����Z
	 * 
	 * @param ID  ��U���ϥΪ�
	 * 
	 * 
	 * Psedo code:
	 * �z�Lif else�P�_�O�_�C��60���A�C��|�b���Z��е���*
	 * ��X�ϥΪ̦��Z�C��
	 * 
	 * Time estimate:O(1)
	 * Example GradeSystem����.showGrade(ID);��X���Z
	 -----------------------------------------------------------------------*/
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
	/*method showRank---------------------------------------------------------
	 * �ΨӺ�B�z��J�t��
	 * 
	 * @param ID  ��U���ϥΪ�
	 * 
	 * 
	 * Psedo code:
	 * �I�sshowOldWeights�L�X�°t��
	 * �Acall getNewWeights���ϥΪ̿�J���s���Z
	 * �̫�setWeights(weights,ID)�T�{�ϥΪ̿�J���s�t���A�ç�s�Ҧ��H��totalGrade
	 * 
	 * Time estimate:O(1)
	 * Example GradeSystem����.updateWeight(ID);�i�J��[�v���A
	 -----------------------------------------------------------------------*/
	public void updateWeight(String ID){
		showOldWeights();
		getNewWeights();
		setWeights(weights,ID);
	}
	/*method showOldWeights--------------------------------------------------
	 * �ΨӦL�X��U���[�v
	 * 
	 * 
	 * Psedo code:
	 * System.out.println(�¥[�v)
	 * 
	 * Time estimate:O(1)
	 * Example GradesSystem����.showOldWeights();��X�°t��
	 -----------------------------------------------------------------------*/
	public void showOldWeights(){
		System.out.println("�°t��"+"\n  lab1   "+Math.round(weights[0]*100)+"%"+"\n  lab2   "+Math.round(weights[1]*100)+"%"+
				"\n  lab3   "+Math.round(weights[2]*100)+"%"+"\n  mid-term "+Math.round(weights[3]*100)+"%"+"\n final exam  "+
				Math.round(weights[4]*100)+"%");
	}
	/*method getNewWeights---------------------------------------------------
	 * �Ψӱ��ϥΪ̿�J���s�t��
	 * 
	 * 
	 * 
	 * 
	 * Psedo code:
	 * �z�LScanner�ӱ��ϥΪ̷s��J���[�v�A�M��N�ȩ�i�Ȧs��float [] new_weights��
	 * �̫�L�X�ϥΪ̷s��J���t��(�����p��)
	 * 
	 * Time estimate:O(1)
	 * Example GradeSystem����.getNewWeights();��J�s�t���A����L�X�s�t��
	 -----------------------------------------------------------------------*/
	public void getNewWeights(){
		Scanner scanner=new Scanner(System.in);
		new_weights[0]=scanner.nextFloat();
		new_weights[1]=scanner.nextFloat();
		new_weights[2]=scanner.nextFloat();
		new_weights[3]=scanner.nextFloat();
		new_weights[4]=scanner.nextFloat();
		System.out.println("��J�s�t��"+"\n  lab1   "+Math.round(new_weights[0]*100)+"\n  lab2   "+Math.round(new_weights[1]*100)+
				"\n  lab3   "+Math.round(new_weights[2]*100)+"\n  mid-term "+Math.round(new_weights[3]*100)+"\n final exam  "+
				Math.round(new_weights[4]*100));
	}
	/*method setWeights------------------------------------------------------
	 * �ΨӽT�{�ϥΪ̷s��J���t���A�ç�s�Ҧ��H��totalGrade
	 * 
	 * 
	 * 
	 * 
	 * Psedo code:
	 * ��X�s�t���Aif�ϥΪ̿�JY�T�{���ܴN�h��for�j���s�Ҧ��H��totalGrade�A�ç�sweights
	 * else �h�O����ˡA��new_weights�]��0
	 * 
	 * Time estimate:O(n)
	 * Example GradeSystem����.setWeights(weights,id);���T�{���O�A��sweights�BtotalGrade
	 -----------------------------------------------------------------------*/
	public void setWeights(float[] weights,String id){
		System.out.println("�нT�{�s�t��"+"\n  lab1   "+Math.round(new_weights[0]*100)+"%"+"\n  lab2   "+Math.round(new_weights[1]*100)+"%"+
				"\n  lab3   "+Math.round(new_weights[2]*100)+"%"+"\n  mid-term "+Math.round(new_weights[3]*100)+"%"+"\n final exam  "+
				Math.round(new_weights[4]*100)+"%"+
				"\n�H�W���T��?Y(Yes)��N(No)");
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
	}
	/*method initialize_Weights-----------------------------------------------
	 * ��ϥΪ̵n�X�A���m�t���BtotalGrade
	 * 
	 * 
	 * 
	 * 
	 * Psedo code:
	 * initialize weights�BtotalGrade
	 * 
	 * Time estimate:O(n)
	 * Example GradeSystem����.initialize_Weights();��l��weights�BtotalGrade
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
	
	
		
}
