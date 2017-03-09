package hw2;
import java.util.Scanner;
public class Main_extends_Object {
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		String in;
		String msg1="ID error",msg2="Invalid command";
		in=scanner.next();
		try{
			UI aUI=new UI();		
		}catch(NoSuchIDExceptions e1){
			System.out.println(msg1);
		}catch(NoSuchCommandExceptions e2){
			System.out.println(msg2);
		}
	}
	public class NoSuchIDExceptions extends Exception{
		public NoSuchIDExceptions(String ID){
			super(ID);
			
			//throw new NoSuchIDExceptions(msg1);
			
		}
	}
	public class NoSuchCommandExceptions extends Exception {
		public NoSuchCommandExceptions(String Command){
			
			//throw new NoSuchIDExceptions(msg2);
		}
	}
}
