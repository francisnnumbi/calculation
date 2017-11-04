
import java.util.*;
public class Calculation {

  private static final String OPS = "+*/^%";
  private static ArrayList<Character> ops = new ArrayList<>();
  private static String[] vars;

  private static StringBuilder expression;

  public static double eval(String statement) {
	statement = statement.replace(" ", "");
	organize(statement);
	System.out.println("expression -> " + expression.toString());
	precalc();
	return postCalc();
  }

  private static void organize(String statement) {
	expression = new StringBuilder(statement);
	int t = 0;
	while (t < expression.length()) {
	  if (t > 0 && expression.charAt(t) == '(' && !OPS.contains("" + expression.charAt(t - 1))) {
		expression.insert(t, '*');
	  }
	  t++;
	}
  }

  private static void precalc() {
int q = 1;
	do {
	  String w = "";
	  int x = expression.lastIndexOf("(");
	  int y = expression.indexOf(")", x);
	  w = expression.substring(x + 1, y);
	  System.out.println("solve -> " + w);
	  getOperators(w);
	  getOperands(w);
	  double d = exec();
	  // int xx = x-1 < 0 ? 0 : x-1;
	  expression = expression.replace(x, y + 1, d + "");
	  System.out.println("step " + q++ + ") " + expression);
	}while (expression.toString().contains("("));

  }

  private static double postCalc() {
	getOperators(expression.toString());
	getOperands(expression.toString());
	return exec();
  }

  private static void getOperators(String st) {
	ops.clear();
	for (char c : st.toCharArray()) {
	  if (OPS.contains(Character.toString(c)))ops.add(c);
	}
  }

  private static void getOperands(String st) {
	vars = st.split("[" + OPS + "]");
  }

  private static double exec() {

	double result = solveMinus(vars[0]);
	for (int i = 0; i < ops.size(); i++) {
	  switch (ops.get(i)) {
		case '+':
		  result += solveMinus(vars[i + 1]);
		  break;
		case '*':
		  result *= solveMinus(vars[i + 1]);
		  break;
		case '/':
		  result /= solveMinus(vars[i + 1]);
		  break;  
		case '^':
		  result = Math.pow(result, solveMinus(vars[i + 1]));
		  break; 
		case '%':
		  result = result % solveMinus(vars[i + 1]);
		  break; 
		default:
		  result = solveMinus(vars[i + 1]);
		  break; 
	  }
	}

	return result;
  }


  private static double solveMinus(String statement) {
	if (statement.contains("-")) {
	  String[] ss = statement.split("-");
	  int y = 0;
	  double f;
	try{
	  f = Double.parseDouble(ss[y]);
	  }catch(Exception e){
		y++;
		f = -1 * Double.parseDouble(ss[y]);
	  }
	  y++;
	  for (; y < ss.length; y++) {
		f -= Double.parseDouble(ss[y]);
	  }
	  return f;
	} else
	  return Double.parseDouble(statement);
  }
}
