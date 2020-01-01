package main;

import java.util.Scanner;

import main.AmusingPreciseNumber;
import main.AmusingDeque;

/**
 * An Amusing Precise Number PostFix Calculator
 * @author Lewis Sheaffer
 */
public class calculator {
	public static void main(String[] args) {
		AmusingDeque<AmusingPreciseNumber> numberDeque = new AmusingDeque<AmusingPreciseNumber>();
		Scanner input = new Scanner(System.in);
		Scanner inputLine = new Scanner(input.nextLine());
		boolean printFinal = true;
		while(inputLine.hasNext()) {
			String item = inputLine.next();
			if(item.equals("abs")) {
				if(numberDeque.size() >= 1) {
					AmusingPreciseNumber absAPN = numberDeque.pop();
					absAPN.abs();
					numberDeque.push(absAPN);
				}
				else {
					System.out.println("ERROR: At least one operand is needed for the abs operator.");
					printFinal = false;
					break;
				}
			}
			else if(item.equals("neg")) {
				if(numberDeque.size() >= 1) {
					AmusingPreciseNumber negAPN = numberDeque.pop();
					negAPN.negate();
					numberDeque.push(negAPN);
				}
				else {
					System.out.println("ERROR: At least one operand is needed for the neg operator.");
					printFinal = false;
					break;
				}
			}
			else if(item.equals("-")) {
				if(numberDeque.size() >= 2) {
					AmusingPreciseNumber numb1 = numberDeque.pop();
					AmusingPreciseNumber numb2 = numberDeque.pop();
					numb2.subtract(numb1);
					numberDeque.push(numb2);
				}
				else {
					System.out.println("ERROR: At least two operands are needed for the - operator.");
					printFinal = false;
					break;
				}
			}
			else if(item.equals("+")) {
				if(numberDeque.size() >= 2) {
					AmusingPreciseNumber numb1 = numberDeque.pop();
					AmusingPreciseNumber numb2 = numberDeque.pop();
					numb1.add(numb2);
					numberDeque.push(numb1);
				}
				else {
					System.out.println("ERROR: At least two operands are needed for the + operator.");
					printFinal = false;
					break;
				}
			}
			else {
				try {
					AmusingPreciseNumber numb = new AmusingPreciseNumber(item);
					numberDeque.push(numb);
				}
				catch(IllegalArgumentException e) {
					System.out.println("ERROR: Input was entered with improper syntax, please include whitespaces between the operators and numbers.");
					printFinal = false;
					break;
				}
			}
			
			
		}
		
		if(printFinal == true) {
			if(numberDeque.size() == 1) {
				System.out.println(numberDeque.peek());
			}
			else {
				System.out.println("ERROR: Input includes to many numbers given the number of operaters");
			}
		}
		inputLine.close();
		input.close();
	}
}

