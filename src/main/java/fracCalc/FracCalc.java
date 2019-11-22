/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

import net.sf.saxon.functions.Substring;

public class FracCalc {
	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner userInput = new Scanner(System.in);
		String equation = userInput.nextLine();
		while (!equation.equals("quit")) {
			System.out.println(produceAnswer(equation));
			equation = userInput.nextLine();
		}

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
		String answer = operand1 + operator + operand2;

		String op2Num = Num(operand2);
		String op2Den = Den(operand2);
		String op2Whol = Whole(operand2);

		String op2Value = "whole:" + op2Whol + " numerator:" + op2Num + " denominator:" + op2Den;
		return op2Value;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need
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

	public static String Den(String a) {
		if (a.indexOf("/") != -1) {
			// mixed or impartial fraction
			return a.substring(a.indexOf("/") + 1);
		} else {
			// whole
			return "1";
		}
	}
}
