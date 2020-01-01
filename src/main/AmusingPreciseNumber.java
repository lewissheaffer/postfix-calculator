package main;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * An Amusing Precise number class.
 * 
 * @author Lewis Sheaffer
 */
public class AmusingPreciseNumber {
	/**
	 * The list for which the digits of this Amusing Precise Number are stored
	 */
	private ArrayList<Integer> numberList;

	/**
	 * Create an AmusingPreciseNumber from an int type.
	 * 
	 * @param numb
	 */
	public AmusingPreciseNumber(int numb) {
		this(Integer.toString(numb));
	}

	/**
	 * Create an AmusingPreciseNumber from a String. The formatting of the string is
	 * some number of digits with an optional decimal point. Your constructor is
	 * required to throw a runtime exception if the string does not have a valid
	 * syntax. Valid strings do include 0, 0.0, 0000, 00000123, 00000123.000001000,
	 * - 23432, +1234., and +1234555. That is, leading or trailing zeros, a single
	 * leading plus or minus sign, and no plus or minus sign are all valid numbers.
	 * In effect, any reasonable string of digits (no matter how long) that can be
	 * interpreted as a number is valid.
	 * 
	 * @param numb String to be entered as an amusing precise number
	 */
	public AmusingPreciseNumber(String numb) {
		numberList = new ArrayList<Integer>();
		boolean nonZero = false;
		boolean sign = false;
		boolean decimal = false;
		int realAfterDecimal = -1;
		for (int i = 0; i < numb.length(); i++) {
			char tempChar = numb.charAt(i);

			if (tempChar == '-' || tempChar == '+') {
				if ((i + 1) < numb.length()) {
					// Will throw an error if + or - is followed by a 0 (+012)
					if (numb.charAt(i + 1) == '0') {
						throw new IllegalArgumentException();
					}
				}
				// Assuming that exception should be thrown if the string is just "+" or "-"
				else {
					throw new IllegalArgumentException();
				}
				// If multiple signs are encountered or this sign is not is the correct place
				if (sign) {
					throw new IllegalArgumentException();
				}

				// Adds -10 to the list to represent negative number, 10 for positive
				else if (tempChar == '-') {
					numberList.add(-10);
					sign = true;
				} else {
					numberList.add(10);
					sign = true;
				}
			} else if (tempChar == '.') {
				// Throws an error if decimal has already been encountered
				if (decimal == true) {
					throw new IllegalArgumentException();
				}
				boolean addDecimal = false;
				realAfterDecimal = i;
				// Cycles through the characters after the decimal to see if there is a
				// Character other than '0'

				for (int j = i + 1; j < numb.length(); j++) {
					char AfterDecimalChar = numb.charAt(j);
					if (AfterDecimalChar != '0') {
						addDecimal = true;
						realAfterDecimal = j;
					}
				}
				if (addDecimal == true) {
					numberList.add(20);
				}
				decimal = true;
			}
			// For any other character other than '+', '-', or '.'
			else {
				if (!Character.isDigit(tempChar)) {
					throw new IllegalArgumentException();
				}
				// Using short circuiting to check to see if a trailing zero should be added.
				if (tempChar == '0' && nonZero && !decimal) {
					numberList.add(0);
				}
				else if(tempChar == '0' && decimal && (i <= realAfterDecimal)) {
					numberList.add(0);
				}
				else if (tempChar != '0') {
					numberList.add(Character.getNumericValue(tempChar));
					nonZero = true;
				}
			}

		}
		if (!nonZero) {
			numberList.clear();
			numberList.add(0);
		}
		if (!sign) {
			numberList.add(0, 10);
		}
		
	}

	/**
	 * The same as the string constructor except the input comes from arbitrary
	 * Reader. This means that there is no bound on the number of digits for this
	 * constructor. The format is similar to the String constructor except that a
	 * whitespace character is treated as a termination of the input and no further
	 * data is read from the stream once this whitespace character is read. Leading
	 * whitespace characters are ignored. We will test that this constructor can
	 * handle at least 100,000 digits of precision.
	 * 
	 * @param r
	 * @throws IOException
	 */
	public AmusingPreciseNumber(Reader r) throws IOException {
		this(ReaderConstructorOneLineReturn(r));
	}

	private static String ReaderConstructorOneLineReturn(Reader r) throws IOException {
		String returnString = "";
		char tempChar = (char) r.read();
		// Will keep looping as long as leading whitespace
		while (Character.isWhitespace(tempChar)) {
			tempChar = (char) r.read();
		}

		// Will keep looping as long tempChar is not whitespace.
		while (!Character.isWhitespace(tempChar)) {
			returnString += tempChar;
			tempChar = (char) r.read();
		}

		return returnString;
	}

	/**
	 * A simple copy constructor. It is required that this be a deep copy. We will
	 * test this
	 * 
	 * @param numb The amusingPreciseNumber which will be copied into this
	 *             amusingPreciseNumber.
	 */
	public AmusingPreciseNumber(AmusingPreciseNumber numb) {
		this(numb.toString());
	}

	/**
	 * output the number in its simplest form (that is without leading or trailing
	 * 0s, a leading +, or a trailing .). A single leading 0 on numbers between 0
	 * and 1 (exclusive) is acceptable
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		String returnString = "";

		if(numberList.size() == 1) {
			return returnString + "0";
		}

		// numberList is expected to lead with a 20 or 10 to represent - or +
		if (numberList.get(0) == -10) {
			returnString += "-";
		}

		// i is then set to skip the first index of numberList
		for (int i = 1; i < numberList.size(); i++) {
			if (numberList.get(i) == 20) {
				returnString += ".";
			} else {
				returnString += numberList.get(i);
			}
		}
		return returnString;
	}

	/**
	 * Add numb to this AmusingPreciseNumber
	 * 
	 * @param numb The AmusingPreciseNumber which will be added to this
	 *             AmusingPreciseNumber.
	 */
	public void add(AmusingPreciseNumber numb) {
		this.padNumbers(numb);
		// To find decimal of this APN and numb APN
		int DecimalIndex = numberList.size();
		for (int i = 1; i < numberList.size(); i++) {
			if (numberList.get(i) == 20) {
				DecimalIndex = i;
			}
		}

		// If this APN is positive and the numb APN is negative
		if (this.numberList.get(0) == 10 && numb.numberList.get(0) == -10) {
			numb.numberList.set(0, 10);
			this.subtract(numb);
			numb.numberList.set(0, -10);
		}
		// If this APN is positive and the numb APN is negative
		else if (this.numberList.get(0) == -10 && numb.numberList.get(0) == 10) {
			numb.numberList.set(0, -10);
			this.subtract(numb);
			numb.numberList.set(0, 10);
		} else {
			int carryover = 0;
			for (int i = numberList.size() - 1; i > 0; i--) {
				if (i == DecimalIndex) {
					continue;
				}
				int setNumber = (carryover + this.numberList.get(i) + numb.numberList.get(i));
				this.numberList.set(i, setNumber % 10);
				if (setNumber >= 10) {
					carryover = 1;
				} else {
					carryover = 0;
				}
			}

			if (carryover == 1) {
				this.numberList.add(1, 1);
			}
		}

		// Removes any excess leading or trailing zeros the occured during the adding
		// phase.
		numb.Trim();
		this.Trim();
	}

	/**
	 * Helper method to pad this APN and the APN numb to have matching number of
	 * digits before and after the decimal point
	 * 
	 * @param numb The APN to compare with this list to pad with 0s
	 */
	private void padNumbers(AmusingPreciseNumber numb) {
		int thisDecimalIndex = -1;
		int thisBeforeDecimalCount = 0;
		int thisAfterDecimalCount = 0;
		boolean thisBeforeDecimal = true;
		// For loop to check for the number of digits before and after the decimal in
		// this list.
		for (int i = 1; i < numberList.size(); i++) {
			if (numberList.get(i) == 20) {
				thisDecimalIndex = i;
				thisBeforeDecimal = false;
			} else if (thisBeforeDecimal == true) {
				thisBeforeDecimalCount++;
			} else {
				thisAfterDecimalCount++;
			}
		}

		int numbDecimalIndex = -1;
		int numbBeforeDecimalCount = 0;
		int numbAfterDecimalCount = 0;
		boolean numbBeforeDecimal = true;
		// For loop to check for the number of digits before and after the decimal in
		// numb's list.
		for (int i = 1; i < numb.numberList.size(); i++) {
			if (numb.numberList.get(i) == 20) {
				numbDecimalIndex = i;
				numbBeforeDecimal = false;
			} else if (numbBeforeDecimal == true) {
				numbBeforeDecimalCount++;
			} else {
				numbAfterDecimalCount++;
			}
		}

		// Adding leading 0s to match number of digits before decimal if count differs
		if (thisBeforeDecimalCount > numbBeforeDecimalCount) {
			for (int i = 0; i < (thisBeforeDecimalCount - numbBeforeDecimalCount); i++) {
				numb.numberList.add(1, 0);
			}
		} else if (numbBeforeDecimalCount > thisBeforeDecimalCount) {
			for (int i = 0; i < (numbBeforeDecimalCount - thisBeforeDecimalCount); i++) {
				this.numberList.add(1, 0);
			}
		}

		// Adding trailing 0s to match number of digits before decimal if count differs
		if (thisAfterDecimalCount > numbAfterDecimalCount) {
			// Adds a decimal if other number has decimal values
			if (numbDecimalIndex == -1) {
				numb.numberList.add(20);
				numbDecimalIndex = numb.numberList.size() - 1;
			}
			for (int i = 0; i < (thisAfterDecimalCount - numbAfterDecimalCount); i++) {
				numb.numberList.add(0);
			}
		} else if (numbAfterDecimalCount > thisAfterDecimalCount) {
			// Adds a decimal if other number has decimal values
			if (thisDecimalIndex == -1) {
				this.numberList.add(20);
				thisDecimalIndex = this.numberList.size() - 1;
			}
			for (int i = 0; i < (numbAfterDecimalCount - thisAfterDecimalCount); i++) {
				this.numberList.add(0);
			}
		}
	}

	/**
	 * Method to trim unnecessary 0s off this APN
	 */
	private void Trim() {
		int decimal = numberList.size();
		boolean hasDecimal = false;
		boolean realNumbersAfterDecimal = false;
		for (int i = 0; i < numberList.size(); i++) {
			if (numberList.get(i) == 20) {
				decimal = i;
				hasDecimal = true;
			}
		}
		int removeIndex = 1;
		for(int i = 1; i < numberList.size(); i++) {
			if(numberList.get(removeIndex) != 0) {
				break;
			} 
			numberList.remove(1);
		}
		if(hasDecimal) {
			for (int i = numberList.size() - 1; i > decimal; i--) {
				if (numberList.get(i) != 0) {
					realNumbersAfterDecimal = true;
					break;
				}
				numberList.remove(numberList.size()-1);
			}
			if(realNumbersAfterDecimal == false) {
				numberList.remove(numberList.size()-1);
			}
		}
	}

	/**
	 * Subtract numb from this AmusingPreciseNumber
	 * 
	 * @param numb the AmusingPReciseNumber which will subtract from this
	 *             AmusingPreciseNumber.
	 */
	public void subtract(AmusingPreciseNumber numb) {
		this.padNumbers(numb);
		
		// To find decimal of this APN and numb APN
		int DecimalIndex = numberList.size();
		for (int i = 1; i < numberList.size(); i++) {
			if (numberList.get(i) == 20) {
				DecimalIndex = i;
			}
		}
		
		//Loop to figure out which value is bigger, and to switch APNs
		numb.Trim();
		AmusingPreciseNumber tempNumb = new AmusingPreciseNumber(numb);
		this.padNumbers(numb);
		boolean numbBigger = false;
		for(int i = 1; i<this.numberList.size(); i++) {
			if(this.numberList.get(i) == 20) {
				continue;
			}
			if(this.numberList.get(i) < numb.numberList.get(i)) {
				numbBigger = true;
				this.Trim();
				AmusingPreciseNumber tempThis = new AmusingPreciseNumber(this);
				this.numberList = numb.numberList;
				numb.numberList = tempThis.numberList;
				break;
			}
			else if(numb.numberList.get(i) < this.numberList.get(i)) {
				break;
			}
		}
		this.padNumbers(numb);

		// If this APN is positive and the numb APN is negative
		if (this.numberList.get(0) == 10 && numb.numberList.get(0) == -10) {
			this.numberList.set(0, -10);
			this.add(numb);
			this.numberList.set(0, 10);
		}
		// If this APN is negative and the numb APN is negative
		else if (this.numberList.get(0) == -10 && numb.numberList.get(0) == 10) {
			numb.numberList.set(0, -10);
			this.add(numb);
			numb.numberList.set(0, 10);
		}
		//If both numbers have the same sign
		else {
			int carryover = 0;
			for (int i = numberList.size() - 1; i > 0; i--) {
				if (i == DecimalIndex) {
					continue;
				}
				if(this.numberList.get(i) < (numb.numberList.get(i) + carryover)) {
					if(i == 1) {
						int setNumber = ((this.numberList.get(i)) - (numb.numberList.get(i) + carryover));
						this.numberList.set(i, setNumber * -1);
						this.numberList.set(0, this.numberList.get(0) * -1);
					}
					else {
						this.numberList.set(i, (this.numberList.get(i)+ 10) - (numb.numberList.get(i) + carryover));
						carryover = 1;
					}
				}
				else {
					this.numberList.set(i, (this.numberList.get(i)) - (numb.numberList.get(i) + carryover));
					carryover = 0;
				}
			}
		}

		if(numbBigger == true) {
			numb = tempNumb;
			this.numberList.set(0,this.numberList.get(0) * -1);
		}
		// Removes any excess leading or trailing zeros the occured during the padding
		// phase.
		numb.Trim();
		this.Trim();
	}
	
	

	/**
	 * Negate this AmusingPreciseNumber
	 */
	public void negate() {
		numberList.set(0, numberList.get(0) * -1);
	}

	/**
	 * Compute and store the absolute value of this AmusingPreciseNumbe
	 */
	public void abs() {
		numberList.set(0, 10);
	}

	/**
	 * Return an AmusingPreciseNumber that is the sum of numb1 and numb2. Numb1 and
	 * numb2 are unchanged.
	 * 
	 * @param numb1 A AmusingPreciseNumber for which numb2 will be added to.
	 * @param numb2 A AmusingPreciseNumber for which numb1 will be added to.
	 * @return An AmusingPreciseNumber which is the sum of numb1 and numb2.
	 */
	public static AmusingPreciseNumber add(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
		AmusingPreciseNumber returnAPN = new AmusingPreciseNumber(numb1);
		returnAPN.add(numb2);
		return returnAPN;
	}

	/**
	 * Return an AmusingPreciseNumber that is the difference of numb1 and numb2
	 * (numb1 minus numb2) Numb1 and numb2 are unchanged.
	 * 
	 * @param numb1 The desired AmusingPreciseNumber for which numb2 will subtract
	 *              from.
	 * @param numb2 The desired AmusingPreciseNumber which will subtract from numb1.
	 * @return An amusingPReciseNumber that is the difference of numb1 and numb2.
	 */
	public static AmusingPreciseNumber subtract(AmusingPreciseNumber numb1, AmusingPreciseNumber numb2) {
		AmusingPreciseNumber returnAPN = new AmusingPreciseNumber(numb1);
		returnAPN.subtract(numb2);
		return returnAPN;
	}

	/**
	 * Return an AmusingPreciseNumber that is the negative of numb and leave numb
	 * unchanged.
	 * 
	 * @param numb The desired AmusingPreciseNumber for which the absolute value
	 *             will be returned.
	 * @return The negative of numb.
	 */
	public static AmusingPreciseNumber negate(AmusingPreciseNumber numb) {
		AmusingPreciseNumber returnAPN = new AmusingPreciseNumber(numb);
		returnAPN.negate();
		return returnAPN;
	}

	/**
	 * Return an AmusingPreciseNumber that is the absolute value of numb and leave
	 * numb unchanged.
	 * 
	 * @param numb The desired AmusingPreciseNumber for which the absolute value
	 *             will be returned.
	 * @return An AmusingPreciseNumber that is the absolute value of numb.
	 */
	public static AmusingPreciseNumber abs(AmusingPreciseNumber numb) {
		AmusingPreciseNumber returnAPN = new AmusingPreciseNumber(numb);
		returnAPN.abs();
		return returnAPN;
	}
}
