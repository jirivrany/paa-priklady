package cz.nti.tul.konvertorpenez;

/**
 * Conversion of values
 * @author Jiri Vrany
 * 
 */

public class Convertor {
	
	/**
	 * Eur to Czk
	 * @param givenVal - double number
	 * @param kurz - double number
	 * @return a * b
	 */
	public static double convertEurToCzk(double givenVal, double kurz) {
		return givenVal * kurz;
	}
	
	/**
	 * Eur to Czk
	 * @param givenVal - double number
	 * @param kurz - double number
	 * @return a / b
	 */
	public static double convertCzkToEur(double givenVal, double kurz) {
		return givenVal / kurz;
	}
	
}
