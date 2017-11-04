
import java.util.*;public class Calculator 
{ 
static Scanner scan = new Scanner(System.in);
  
  public static void main(String[] args){
	String statement = "(3+2)(2-3)/(3-5)";//getStatement("input");
	System.out.println("input -> " + statement);
	double resp = Calculation.eval(statement);
	System.out.println();
	System.out.println("result for -> " + statement + " = " + resp);
  }
  
  private static String getStatement(String what){
	System.out.print(what + ": ");
	return scan.nextLine();
  }
}
