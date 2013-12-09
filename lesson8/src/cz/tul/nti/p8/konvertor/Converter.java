package cz.tul.nti.p8.konvertor;

public class Converter {
	
	public static double toCzk(double hodnota, double kurz) {
		return hodnota * kurz;
	}

	public static double toEur(double hodnota, double kurz) {
		if (kurz == 0) return hodnota / 27;
		else return hodnota / kurz;
	}

}

