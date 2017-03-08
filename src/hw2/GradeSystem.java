package hw2;
import java.util.LinkedList;
import java.io.*;
import java.util.Scanner;

public class GradeSystem{
	
	
	
	float[] weights={0.1f,0.1f,0.1f,0.3f,0.4f};
	String filePath = "C:/Users/∂¿©s¿M/workspace/Software-Engineering-Agile/src/gradeinput.txt";
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
				String str;
				Grades aGrade= new Grades();
				str=scanner.next();
				//System.out.println(line);
			}
		}
		catch(FileNotFoundException ex){
			System.out.println("Unable to open file "+fileName);
		}
		catch(IOException ex){
			System.out.println("Error reading file:"+fileName);
		}
		
	}
	
	public void containsID(int ID){
		
		
	}
	
	public void showRank(int ID){
		
	}
	
	public void showGrade(int ID){
		
	}
	public void updateWeight(){
		
	}
	
	
		
}
