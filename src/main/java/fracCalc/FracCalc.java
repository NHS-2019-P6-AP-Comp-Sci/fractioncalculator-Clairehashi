/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

import net.sf.saxon.functions.Substring;

public class FracCalc {
	static int den1 = 1;
	static int num1 = 0;
	static int whole1 = 0;
	static int den2 = 1;
	static int num2 = 0;
	static int whole2 = 0;
	static int recipro = 1;
	static int whole = 0;
	static int frac = 0;

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner userInput = new Scanner(System.in);
		String equation = userInput.nextLine();
		while (!equation.equals("quit")) {
			System.out.println(produceAnswer(equation));
			equation = userInput.nextLine();
		}
		userInput.close();
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"

	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		int index = input.indexOf(" ");
		String operand1 = input.substring(0, index);
		String operator = input.substring(index + 1, index + 2);
		String operand2 = input.substring(index + 3);

		den1 = 1;
		num1 = 0;
		whole1 = 0;
		den2 = 1;
		num2 = 0;
		whole2 = 0;
		recipro = 1;
		whole = 0;
		frac = 0;
		String answer = "0";

		// both mixed numbers
		if (operand2.indexOf("/") >= 0 && operand1.indexOf("/") >= 0) {
			num2 = Integer.parseInt(Num(operand2));
			den2 = Integer.parseInt(Den(operand2));
			whole2 = Integer.parseInt(Whole(operand2));
			num1 = Integer.parseInt(Num(operand1));
			den1 = Integer.parseInt(Den(operand1));
			whole1 = Integer.parseInt(Whole(operand1));
		} else if (operand2.indexOf("/") >= 0) {
			whole1 = Integer.parseInt(Whole(operand1));
			num2 = Integer.parseInt(Num(operand2));
			den2 = Integer.parseInt(Den(operand2));
			whole2 = Integer.parseInt(Whole(operand2));
		} else if (operand1.indexOf("/") >= 0) {
			whole2 = Integer.parseInt(Whole(operand2));
			num1 = Integer.parseInt(Num(operand1));
			den1 = Integer.parseInt(Den(operand1));
			whole1 = Integer.parseInt(Whole(operand1));
		} else {
			whole1 = Integer.parseInt(Whole(operand1));
			whole2 = Integer.parseInt(Whole(operand2));
		}

		if (whole1 < 0) {
			num1 *= -1;
		}
		num1 = (whole1 * den1) + num1;
		whole1 = 0;
		if (whole2 < 0) {
			num2 *= -1;
		}
		num2 = (whole2 * den2) + num2;
		whole2 = 0;
		if (operator.equals("+")) {
			num1 *= den2;
			num2 *= den1;
			den1 *= den2;
			den2 = den1;
			num1 += num2;
			whole = num1 / den1;
			frac = Math.abs(num1 % den1);
		} else if (operator.equals("*")) {
			num1 *= num2;
			den1 *= den2;
			whole = num1 / den1;
			frac = num1 % den1;
		} else if (operator.equals("/")) {
			recipro = den2;
			den2 = num2;
			num1 *= recipro;
			den1 *= den2;
			whole = num1 / den1;
			frac = num1 % den1;
		} else {
			num1 *= den2;
			num2 *= den1;
			den1 *= den2;
			den2 = den1;
			num1 -= num2;
			whole = num1 / den1;
			frac = num1 % den1;
		}
		int factor = gdc(frac, den1);
		frac /= factor;
		den1 /= factor;
		
		if (frac != 0 && whole != 0) {
			answer = (whole + "_" + Math.abs(frac) + "/" + Math.abs(den1));
		} else if (frac != 0 && whole == 0) {
			answer = (frac + "/" + Math.abs(den1));
		} else if (frac == 0 & whole != 0) {
			answer = (whole + "");
		}
		return answer;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need
	// parses whole from user input
	public static String Whole(String a) {
		if (a.indexOf("_") != -1) {
			// mixed
			return a.substring(0, a.indexOf("_"));
		} else if (a.indexOf("/") != -1) {
			// impartial
			return "0";
		} else {
			return a;
		}
	}

	// parses numerator from user input
	public static String Num(String a) {
		if (a.indexOf("_") != -1) {
			// mixed fraction
			return a.substring(a.indexOf("_") + 1, a.indexOf(("/")));
		} else if (a.indexOf("/") != -1) {
			// impartial
			return a.substring(a.indexOf("_") + 1, a.indexOf("/"));
		} else {
			// whole
			return "0";
		}
	}

	// parses denominator from user input
	public static String Den(String a) {
		if (a.indexOf("/") != -1) {
			// mixed or impartial fraction
			return a.substring(a.indexOf("/") + 1);
		} else {
			// whole
			return "1";
		}
	}

	public static int gdc(int num1, int num2) {
		if (num2 == 0) {
			return num1;
		}
		return gdc(num2, num1 % num2);
	}
}
