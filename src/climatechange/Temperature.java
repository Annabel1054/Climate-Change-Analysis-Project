package climatechange;

import java.util.*;

//
// Temperature Class implements Comparable<ITemperature> and ITemperature 
// It consists of important characteristics that create a temperature object and overrides methods that help compare objects.
//
public class Temperature implements Comparable<ITemperature>, ITemperature {

	private String country;
	private String letterCode;
	private String month;
	private int year;
	private double temp;
	TreeMap<String, Integer> monthMap;

	// Temperature constructor that initializes and takes in the param: temp, year, month, country, and country code.
	public Temperature(double temp, int year, String month, String country, String letterCode) {
		this.country = country;
		this.letterCode = letterCode;
		this.month = month;
		this.year = year;
		this.temp = temp;
		
		//Uses a Treemap to be able to compare int month values in the CompareTo method.
		monthMap = new TreeMap<>();
		monthMap.put("Jan", 1);
		monthMap.put("Feb", 2);
		monthMap.put("Mar", 3);
		monthMap.put("Apr", 4);
		monthMap.put("May", 5);
		monthMap.put("Jun", 6);
		monthMap.put("Jul", 7);
		monthMap.put("Aug", 8);
		monthMap.put("Sep", 9);
		monthMap.put("Oct", 10);
		monthMap.put("Nov", 11);
		monthMap.put("Dec", 12);
	}

	// returns the name of the country
	public String getCountry() {
		return country;
	}

	// returns the 3-letter code of the country
	public String getCountry3LetterCode() {
		return letterCode;
	}

	// returns the month
	public String getMonth() {
		return month;
	}

	// returns the year
	public int getYear() {
		return year;
	}

	// takes in a boolean variable in order to convert celsius to fahrenheit
	// if true, fahrenheit will be returned. if false, celsius will be returned.
	public double getTemperature(boolean getFahrenheit) {
		double newTemp = temp;
		if (getFahrenheit) {
			newTemp = newTemp * (9.0 / 5) + 32;
		}
		return newTemp;
	}

	// Overrides the toString method in order to return the expected output of a temperature obj.
	@Override
	public String toString() {
		return String.format("%.2f", temp) + "(C) " + String.format("%.2f", this.getTemperature(true)) + "(F)" + ", "
				+ year + ", " + month + ", " + country + ", " + letterCode;
	}

	// Overrides the equals method in order to successfully use Comparable<ITemperature>
	// Takes in an Object and casts it to a Temperature object.
	// Returns true if temperature parameters are equal.
	@Override
	public boolean equals(Object x) {
		Temperature that = (Temperature) x;
		if (this.temp != that.year) {
			return false;
		}
		if (this.year != that.year) {
			return false;
		}
		if (this.country.compareTo(that.country) != 0) {
			return false;
		}
		if (this.monthMap.get(this.month) != that.monthMap.get(that.month)) {
			return false;
		}
		return true;
	}

	// Overrides the hashCode method and returns the integer sum of the temp and year.
	@Override
	public int hashCode() {
		return (int) (temp + year);
	}

	// Overrides the compareTo method in order to successfully use Comparable<ITemperature>
	// Compares in the order of : temp, country, year, and then month.
	@Override
	public int compareTo(ITemperature o) {
		if (this.temp != o.getTemperature(false)) {
			return Double.compare(this.temp, o.getTemperature(false));
		}
		else if (!this.country.equalsIgnoreCase(o.getCountry())) {
			return this.country.compareTo(o.getCountry());
		} 
		else if (this.year != o.getYear()) {
			return Integer.compare(this.year, o.getYear());
		}
		return Integer.compare(this.monthMap.get(month), this.monthMap.get(o.getMonth()));
	}

	// Main method used for testing certain methods
	public static void main(String[] args) {
		Temperature test2 = new Temperature(54, 2001, "Jan", "America", "USA");
		Temperature test3 = new Temperature(10, 1999, "Feb", "Korea", "KO");
		System.out.println(
				"Input " + test2.getTemperature(false) + "C : change to F : " + test2.getTemperature(true) + "F");
		System.out.println(test2.compareTo(test3) + " " + test2.equals(test3));
	}

}
