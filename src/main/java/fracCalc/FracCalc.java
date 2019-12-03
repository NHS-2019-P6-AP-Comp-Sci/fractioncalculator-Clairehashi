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
		
		String op1Whol = Whole(operand1);
		String op1Num = Num(operand1);
		String op1Den = Den(operand1);
		String op2Num = Num(operand2);
		String op2Den = Den(operand2);
		String op2Whol = Whole(operand2);
		
		int num1 = Integer.parseInt(Num(operand1));
		int num2 = Integer.parseInt(Num(operand2));
		int whole1 = Integer.parseInt(Whole(operand1));
		int whole2 = Integer.parseInt(Whole(operand2));
		int den1 = Integer.parseInt(Den(operand1));
		int den2 = Integer.parseInt(Den(operand2));
		int num3 = 0;
		int den3 = 0;
		int total = 0;
		
		String op2Value = "whole:" + op2Whol + " numerator:" + op2Num + " denominator:" + op2Den;
		
		num1 = num1 + Math.abs(whole1) * den1;
		num2 = num2 + Math.abs(whole2) * den2;
		if (whole1 < 0){
			num1 *= -1;
		}
		if (whole2 < 0){
			num2 *= -1;
		}
		if (operator.equals("+")) {
			num3 = (num1 * den2) + (num2 * den1);
			den3 = (den1 * den2);
		} else if (operator.equals("-")) {
			num3 = (num1 * den2) - (num2 * den1);
			den3= (den1 * den2);
		} else if (operator.equals("*")){
			num3 = (num1 * num2);
			den3 = (den1 * den2);
		}else if (operator.equals("/")) {
			num3 = (num1 * den2);
			den3 = (den1 * num2);
		}
		String result = (num3 + "/" + den3);
		return result;
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
}
