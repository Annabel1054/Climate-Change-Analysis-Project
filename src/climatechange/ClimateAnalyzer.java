package climatechange;

import java.io.IOException;
import java.io.*;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

//
// ClimateAnalyzer class extends WeatherIO and implements IClimateAnalyzer
// Includes methods that will sort the given filename's data and return the desired data
//
public class ClimateAnalyzer extends WeatherIO implements IClimateAnalyzer {

	ArrayList<ITemperature> list;
	WeatherIO weatherObj;
	TreeMap<Integer, String> monthMap;

	// Constructor that takes in the filename and initializes a WeatherIO obj and an Arraylist with the given data in filename.
	// Also initializes a treeMap that contains the month's int and string form.
	public ClimateAnalyzer(String filename) throws IOException {
		super();
		try {
			weatherObj = new WeatherIO();
			list = weatherObj.readDataFromFile(filename);
		} catch (Exception e) {
			System.out.println("This is the exception: " + e);
			e.printStackTrace();
		}
		monthMap = new TreeMap<>();
		monthMap.put(1, "Jan");
		monthMap.put(2, "Feb");
		monthMap.put(3, "Mar");
		monthMap.put(4, "Apr");
		monthMap.put(5, "May");
		monthMap.put(6, "Jun");
		monthMap.put(7, "Jul");
		monthMap.put(8, "Aug");
		monthMap.put(9, "Sep");
		monthMap.put(10, "Oct");
		monthMap.put(11, "Nov");
		monthMap.put(12, "Dec");
	}

	// TASK A-1 Part 1
	// for all the data that matches the specified month and country, 
	// the lowest temperature reading is returned as an ITemperature object
	public ITemperature getLowestTempByMonth(String country, int month) {
		ITemperature lowestTempObj = null;
		double lowestTemp = Integer.MAX_VALUE; // lowestTemp is set to Integer.MAX_VALUE as a placeholder!
		for (ITemperature s : list) {
			if (s.getCountry().equalsIgnoreCase(country) && s.getMonth().equalsIgnoreCase(monthMap.get(month))) {
				double compareTemp = s.getTemperature(false);
				if (lowestTemp > compareTemp) {
					lowestTemp = compareTemp;
					lowestTempObj = s;
				}
			}
		}
		return lowestTempObj;
	}

	// TASK A-1 Part 2
	// for all the data that matches the specified month and country, 
	// the highest temperature reading is returned as an ITemperature object
	public ITemperature getHighestTempByMonth(String country, int month) {
		ITemperature highestTempObj = null;
		double highestTemp = Integer.MIN_VALUE; // highestTemp is set to Integer.MIN_VALUE as a placeholder!
		for (ITemperature s : list) {
			if (s.getCountry().equalsIgnoreCase(country) && s.getMonth().equalsIgnoreCase(monthMap.get(month))) {
				double compareTemp = s.getTemperature(false);
				if (highestTemp < compareTemp) {
					highestTemp = compareTemp;
					highestTempObj = s;
				}
			}
		}
		return highestTempObj;
	}

	// TASK A-2 Part 1
	// for all data that matches the specified year and country, 
	// the lowest temperature reading is returned as an ITemperature object
	public ITemperature getLowestTempByYear(String country, int year) {
		ITemperature lowestTempObj = null;
		double lowestTemp = Integer.MAX_VALUE; // lowestTemp is set to Integer.MAX_VALUE as a placeholder!
		for (ITemperature s : list) {
			if (s.getCountry().equalsIgnoreCase(country) && s.getYear() == year) {
				double compareTemp = s.getTemperature(false);
				if (lowestTemp > compareTemp) {
					lowestTemp = compareTemp;
					lowestTempObj = s;
				}
			}
		}
		return lowestTempObj;
	}

	// TASK A-2 Part 2
	// for all data that matches the specified year and country, 
	// the highest temperature reading is returned as an ITemperature object
	public ITemperature getHighestTempByYear(String country, int year) {
		ITemperature highestTempObj = null;
		double highestTemp = Integer.MIN_VALUE; // highestTemp is set to Integer.MIN_VALUE as a placeholder!
		for (ITemperature o : list) {
			if (o.getCountry().equalsIgnoreCase(country) && o.getYear() == year) {
				double compareTemp = o.getTemperature(false);
				if (highestTemp < compareTemp) {
					highestTemp = compareTemp;
					highestTempObj = o;
				}
			}
		}
		return highestTempObj;
	}

	// TASK A-3
	// for all temperature data that matches the specified country and fall within the given temp range,
	// it is returned in the form of a TreeSet
	public TreeSet<ITemperature> getTempWithinRange(String country, double rangeLowTemp, double rangeHighTemp) {
		TreeSet<ITemperature> inRangeList = new TreeSet<>();
		if (rangeLowTemp > rangeHighTemp) { // if rangeHighTemp is less than rangeLowTemp, the values will swap!
			double temp = rangeLowTemp;
			rangeLowTemp = rangeHighTemp;
			rangeHighTemp = temp;
		}
		
		for (ITemperature o : list) {
			if (o.getCountry().equalsIgnoreCase(country)) {
				double currentTemp = o.getTemperature(false);
				if (currentTemp <= rangeHighTemp && currentTemp >= rangeLowTemp) {
					inRangeList.add(o);
				}
			}
		}
		return inRangeList;
	}

	// TASK A-4 Part 1
	// Returns the lowest temperature reading amongst all data for the specified country
	public ITemperature getLowestTempYearByCountry(String country) {
		ITemperature lowestTempObj = null;
		double lowest = Integer.MAX_VALUE;
		for (ITemperature l : list) {
			if (l.getCountry().equalsIgnoreCase(country)) {
				double compareTemp = l.getTemperature(false);
				if (lowest > compareTemp) {
					lowest = compareTemp;
					lowestTempObj = l;
				}
			}
		}
		return lowestTempObj;
	}

	// TASK A-4 Part 2
	// Returns the highest temperature reading amongst all data for the specified country
	public ITemperature getHighestTempYearByCountry(String country) {
		ITemperature highestTempObj = null;
		double highest = Integer.MIN_VALUE;
		for (ITemperature l : list) {
			if (l.getCountry().equalsIgnoreCase(country)) {
				double compareTemp = l.getTemperature(false);
				if (highest < compareTemp) {
					highest = compareTemp;
					highestTempObj = l;
				}
			}
		}
		return highestTempObj;
	}

	// TASK B-1 Part 1
	// Gets the top 10 countries with the lowest temp for the specified month
	// Returns an Arraylist with 10 temperature obj that has the lowest temp
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp(int month) {
		TreeSet<ITemperature> lowestTempAllCountry = new TreeSet<>();
		TreeSet<ITemperature> top10List = new TreeSet<>();
		HashSet<String> countryList = new HashSet<>();

		ITemperature lowestTempObj;
		for (ITemperature s : list) { // Looks for country objects that have not be registered in countryList.
			if (!countryList.contains(s.getCountry()) && s.getMonth().equalsIgnoreCase(monthMap.get(month))) {
				countryList.add(s.getCountry());
				lowestTempObj = s;

				for (ITemperature s2 : list) { // For the country that's being compared, it compares until the lowest temp is recorded.
					if (lowestTempObj.getCountry().equalsIgnoreCase(s2.getCountry())
							&& s2.getMonth().equalsIgnoreCase(monthMap.get(month))) {
						if (lowestTempObj.getTemperature(false) > s2.getTemperature(false)) {
							lowestTempObj = s2;
						}
					}
				}
				lowestTempAllCountry.add(lowestTempObj); // The lowest temp country obj is added to another tree set that holds all lowest temp country obj.
			}
		}
		for (ITemperature f : lowestTempAllCountry) { // gets the first 10 obj of the TreeSet with all countries with their lowest temps
			if (top10List.size() < 10) {              // because it is organized from low temp - highest temp
				top10List.add(f);
			}
		}
		return new ArrayList<ITemperature>(top10List);
	}

	// TASK B-1 Part 2
	// Gets the top 10 countries with the highest temp for the specified month
	// Returns an Arraylist with 10 temperature obj that has the highest temp
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp(int month) {
		TreeSet<ITemperature> highestTempAllCountry = new TreeSet<>();
		TreeSet<ITemperature> top10List = new TreeSet<>();
		HashSet<String> countryList = new HashSet<>();

		ITemperature highestTempObj;
		for (ITemperature s : list) { // Looks for country objects that have not be registered in countryList.
			if (!countryList.contains(s.getCountry()) && s.getMonth().equalsIgnoreCase(monthMap.get(month))) {
				countryList.add(s.getCountry());
				highestTempObj = s;

				for (ITemperature s2 : list) { // For the country that's being compared, it compares until the highest temp is recorded.
					if (highestTempObj.getCountry().equalsIgnoreCase(s2.getCountry())
							&& s2.getMonth().equalsIgnoreCase(monthMap.get(month))) {
						if (highestTempObj.getTemperature(false) < s2.getTemperature(false)) {
							highestTempObj = s2;
						}
					}
				}
				highestTempAllCountry.add(highestTempObj); // The highest temp country obj is added to another tree set that holds all highest temp country obj.
			}
		}

		ArrayList<ITemperature> indexedArray = new ArrayList<>(highestTempAllCountry);   // gets the last 10 obj of the TreeSet with all countries with their highest temps
		if (highestTempAllCountry.size() >= 10) {                                        // because it is organized from low temp - highest temp
			for (int i = highestTempAllCountry.size() - 10; i < highestTempAllCountry.size(); i++) {
				top10List.add(indexedArray.get(i));
			}
		} else { // if tree set size is less than 10, it will return an array with less than 10 temp obj
			System.out.println("You have less than 10 countries in your data.");
			for (int i = 0; i < highestTempAllCountry.size(); i++) {
				top10List.add(indexedArray.get(i));
			}
		}
		return new ArrayList<ITemperature>(top10List);
	}

	// TASK B-2 Part 1
	// Returns an Arraylist<ITemperature> of top 10 countries with the lowest temperature.
	public ArrayList<ITemperature> allCountriesGetTop10LowestTemp() {
		TreeSet<ITemperature> lowestTempAllCountry = new TreeSet<>();
		TreeSet<ITemperature> top10List = new TreeSet<>();
		HashSet<String> countryList = new HashSet<>();

		ITemperature lowestTempObj;
		for (ITemperature s : list) { // Checks for obj with countries that have not been recorded.
			if (!countryList.contains(s.getCountry())) {
				countryList.add(s.getCountry());
				lowestTempObj = s;

				for (ITemperature s2 : list) { // looks through every obj with the specific country and finds the lowest temp
					if (lowestTempObj.getCountry().equalsIgnoreCase(s2.getCountry())) {
						if (lowestTempObj.getTemperature(false) > s2.getTemperature(false)) {
							lowestTempObj = s2;
						}
					}
				}
				lowestTempAllCountry.add(lowestTempObj); // adds the lowest temp country obj into this tree set
			}
		}
		for (ITemperature f : lowestTempAllCountry) { // The first 10 objects in this tree set holds the lowest temp for each country
			if (top10List.size() < 10) {
				top10List.add(f);
			}
		}
		return new ArrayList<ITemperature>(top10List);
	}

	// TASK B-2
	// Returns an Arraylist<ITemperature> of top 10 countries with the highest temperature.
	public ArrayList<ITemperature> allCountriesGetTop10HighestTemp() {
		TreeSet<ITemperature> highestTempAllCountry = new TreeSet<>();
		TreeSet<ITemperature> top10List = new TreeSet<>();
		HashSet<String> countryList = new HashSet<>();

		ITemperature highestTempObj;
		for (ITemperature s : list) { // Checks for obj with countries that have not been recorded.
			if (!countryList.contains(s.getCountry())) {
				countryList.add(s.getCountry());
				highestTempObj = s;

				for (ITemperature s2 : list) {  // looks through every obj with the specific country and finds the highest temp
					if (highestTempObj.getCountry().equalsIgnoreCase(s2.getCountry())) {
						if (highestTempObj.getTemperature(false) < s2.getTemperature(false)) {
							highestTempObj = s2;
						}
					}
				}
				highestTempAllCountry.add(highestTempObj); // adds the highest temp country obj into this tree set
			}
		}

		ArrayList<ITemperature> indexedArray = new ArrayList<>(highestTempAllCountry);
		if (highestTempAllCountry.size() >= 10) { // adds the last 10 obj into the final tree set that will be returned later
			for (int i = highestTempAllCountry.size() - 10; i < highestTempAllCountry.size(); i++) {
				top10List.add(indexedArray.get(i));
			}
		} else { // if arrayList size is less than 10, it will return an array with less than 10 temp obj
			System.out.println("You have less than 10 countries in your data.");
			for (int i = 0; i < highestTempAllCountry.size(); i++) {
				top10List.add(indexedArray.get(i));
			}
		}

		return new ArrayList<ITemperature>(top10List);
	}

	// TASK B-3
	// Returns an Arraylist that contains all the temperature objects that fall within the temp range (param)
	// The list is sorted from lowest to highest temperature
	public ArrayList<ITemperature> allCountriesGetAllDataWithinTempRange(double lowRangeTemp, double highRangeTemp) {
		if (lowRangeTemp > highRangeTemp) { // makes sure this method still runs if lowRangeTemp is bigger than highRangeTemp
			double temp = highRangeTemp;
			highRangeTemp = lowRangeTemp;
			lowRangeTemp = temp;
		}
		TreeSet<ITemperature> myList = new TreeSet<>();
		for (ITemperature a : list) {
			if (a.getTemperature(false) >= lowRangeTemp && a.getTemperature(false) <= highRangeTemp) { // makes sure the obj has a temp within the range
				myList.add(a);
			}
		}
		return new ArrayList<ITemperature>(myList);
	}

	// TASK C-1
	// Returns an ArrayList<ITemperature> that holds top 10 countries with the highest temperature change between the two given years and specified month.
	public ArrayList<ITemperature> allCountriesTop10TempDelta(int month, int year1, int year2) {
		TreeSet<ITemperature> top10Delta = new TreeSet<>();
		TreeSet<ITemperature> deltadObj = new TreeSet<>();
		TreeSet<ITemperature> year1Set = new TreeSet<>();
		TreeSet<ITemperature> year2Set = new TreeSet<>();
		HashSet<String> countryList = new HashSet<>();

		ITemperature obj;

		// Checks the data list to find a new country
		for (ITemperature s : list) {

			// if country has not been examined...
			if (!countryList.contains(s.getCountry()) && s.getMonth().equalsIgnoreCase(monthMap.get(month))
					&& (s.getYear() == year2 || s.getYear() == year1)) {
				countryList.add(s.getCountry());
				obj = s;
				double biggestDelta = Integer.MIN_VALUE;

				for (ITemperature s2 : list) {
					if (obj.getCountry().equalsIgnoreCase(s2.getCountry())
							&& s2.getMonth().equalsIgnoreCase(monthMap.get(month))) { // && year1Obj.getYear() == year1
						// gathers all year 1 objects in a set
						if (s2.getYear() == year1) {
							year1Set.add(s2);
						}
						// gathers all year 2 objects in a set
						else if (s2.getYear() == year2) {
							year2Set.add(s2);
						}
					}

				}

				// Find biggest delta between two year sets (uses abs value)
				for (ITemperature one : year1Set) {
					for (ITemperature two : year2Set) {
						double change = Math.abs(two.getTemperature(false) - one.getTemperature(false));
						if (biggestDelta < change) {
							biggestDelta = change;
						}
					}
				}

				// Create a new temperature obj with the biggest delta
				double deltaTemp = biggestDelta;
				ITemperature deltad = new Temperature(deltaTemp, Math.abs(year2 - year1), monthMap.get(month), obj.getCountry(),
						obj.getCountry3LetterCode());
				deltadObj.add(deltad);
				year2Set.removeAll(year2Set); //removes information from year2Set to be replaced with the next country's data
				year1Set.removeAll(year1Set); //removes information from year1Set to be replaced with the next country's data
			}
		}

		ArrayList<ITemperature> indexedArray = new ArrayList<>(deltadObj);
		if (deltadObj.size() >= 10) {  // if the treeset has a size larger than 10, it will add the last 10 obj into the final tree set.
			for (int i = deltadObj.size() - 10; i < deltadObj.size(); i++) {
				top10Delta.add(indexedArray.get(i));
			}
		} else { // if the treeSet does not contain 10 obj, it will return less than that.
			System.out.println("There are less than 10 countries");
			for (int i = 0; i < deltadObj.size(); i++) {
				top10Delta.add(indexedArray.get(i));
			}
		}
		return new ArrayList<>(top10Delta);
	}

	// This method starts the climate-change task activities and asks users for required info for each task (subtasks are within each task)!
	// User only has to input once for each task (ex: Task A1, Task A2...)
	// Input is read by a scanner and includes exception handling fixes.
	// Each method called will return data, and these results are written to data files!
	public void runClimateAnalyzer() throws IOException {
	    // initializes important variables that will be later used for exception handling and inputs!
		Scanner in = new Scanner(System.in);
		String country = null;
		int month = 0;
		int year = 0;
		double temp = 0;
		int maxYear = 0;
		int minYear = 0;
		double minTemp = Integer.MAX_VALUE;
		double maxTemp = Integer.MIN_VALUE;
		
		// Adds all countries and years and temp given in data into their respective set.
		HashSet<String> allCountries = new HashSet<>();
		TreeSet<Integer> allYears = new TreeSet<>();
		TreeSet<Double> allTemps = new TreeSet<>();
		
		if (list == null) { //checks if data's array is empty
			System.out.println("Your data file is empty.");
			return;
		}
		
		for (ITemperature c : list) { // adds country, year, and temp to respective sets
			allCountries.add(c.getCountry().toLowerCase());    // country is made lowercase and user input for country is made lowercase for easy comparison
			allYears.add(c.getYear());
			allTemps.add(c.getTemperature(false));
		}

		ArrayList<Integer> allYearsGet = new ArrayList<>(allYears); // gets max and min year
		maxYear = allYearsGet.get(allYearsGet.size() - 1);
		minYear = allYearsGet.get(0);
		
		ArrayList<Double> allTempsGet = new ArrayList<>(allTemps);  // gets min and max temp
		minTemp = allTempsGet.get(0);
		maxTemp = allTempsGet.get(allTempsGet.size() -1);
		
		// Task A1: Lowest AND Highest Temperature given country and month
		System.out.println("Task A1 : Lowest AND Highest Temperature By Country and Month");
		System.out.println("Enter a country: "); 
		while (in.hasNext()) {                          // checks for country. if it is invalid, user must enter another country input until correct
			country = in.nextLine().toLowerCase();
			if (!allCountries.contains(country)) {
				System.out.println("Not a valid country in the database. Re-enter one: ");
				country = in.nextLine().toLowerCase();
				
			}
			if (allCountries.contains(country)) {       // if country is valid, the code will jump to the next user input Q
				break;
			}
			System.out.println("Not a valid country in the database. Re-enter one: ");
			
		}
		System.out.println("Enter a month's number (1-12): ");
		while (!in.hasNextInt()) {                      //checks for month. if it is invalid, user must enter another month input until correct
			System.out.println("Invalid Month. Re-enter one (1-12): ");
			in.next();
		}
		month = in.nextInt();
		while ((month > 12 || month < 1)) {
			System.out.println("Invalid Month. Make sure it is 1-12: "); // This output tells user to input a number from 1-12 for clarity 
			if (in.hasNextInt()) {
				month = in.nextInt();
				if (month >= 1 && month <=12) {
					break;
				}
			}
			else {
				in.next();
			}
		}
		ArrayList<ITemperature> taskA1p1 = new ArrayList<>();                         // Task A1 Part 1 - Lowest Temp given country and month
		taskA1p1.add(this.getLowestTempByMonth(country, month));
		weatherObj.writeSubjectHeaderInFile("data/taskA1_climate_info.csv",           // header is entered into specified filename
				"Task A1: Lowest Temperature For " + country + " and " + monthMap.get(month));
		weatherObj.writeDataToFile("data/taskA1_climate_info.csv",                    // data is appended onto the new file
				"Temperature, Year, Month, Country, Country_Code", taskA1p1);
		ArrayList<ITemperature> taskA1p2 = new ArrayList<>();                         // Task A1 Part 2 - Highest Temp given country and month
		taskA1p2.add(this.getHighestTempByMonth(country, month));
		weatherObj.writeSubjectHeaderInFile("data/taskA1_climate_info.csv",           // same process as above! Except it will append more info onto the file
				"Task A1: Highest Temperature For " + country + " and " + monthMap.get(month));
		weatherObj.writeDataToFile("data/taskA1_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", taskA1p2);

		
		// Task A2 : Lowest AND Highest Temperature given country and year
		System.out.println("Task A2 : Lowest AND Highest Temperature By Country and Year");
		in.nextLine();
		System.out.println("Enter a country: "); 
		while (in.hasNext()) {                                        //checks for country. if it is invalid, user must enter another country input until correct
			country = in.nextLine().toLowerCase();
			if (!allCountries.contains(country)) {
				System.out.println("Not a valid country in the database. Re-enter one: ");
				country = in.nextLine().toLowerCase();
				if (allCountries.contains(country)) {
					break;
				}
				
			}
			if (allCountries.contains(country)) {
				break;
			}
			System.out.println("Not a valid country in the database. Re-enter one: ");
			
		}
		System.out.println("Enter a year between " + minYear + " and " + maxYear + ":");
		while (!in.hasNextInt()) {                                //checks for year. if it is invalid, user must enter another year input until correct
			System.out.println("Not a valid year in the database. Re-enter one between " + minYear + " and " + maxYear + ":");
			in.next();                                            // if not valid, compiler will give user a range of years to choose from.
		}
		while (in.hasNextInt()) {                                 // if user enters a number but it is not a correct num, it will request user to resubmit a valid year
			year = in.nextInt();
			if (!allYears.contains(year)) {
				System.out.println("Not a valid year in the database. Re-enter one between " + minYear + " and " + maxYear + ":");
			} else if (allYears.contains(year) && (year >= minYear && year <= maxYear)){
				break;
			}
		}
		ArrayList<ITemperature> taskA2p1 = new ArrayList<>();                    // A2 Part 1 - Lowest Temp by country and year
		taskA2p1.add(this.getLowestTempByYear(country, year));                   // ITemperature obj returned is added into an array
		weatherObj.writeSubjectHeaderInFile("data/taskA2_climate_info.csv",      // writes the subject header onto a specified file
				"Task A2: Lowest Temperature For " + country + " in " + year);
		weatherObj.writeDataToFile("data/taskA2_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", taskA2p1);    // writes data onto the specified file using taskA2P1 array
		ArrayList<ITemperature> taskA2p2 = new ArrayList<>();                    // A2 Part 2 - Highest Temp by country and year
		taskA2p2.add(this.getHighestTempByYear(country, year));
		weatherObj.writeSubjectHeaderInFile("data/taskA2_climate_info.csv",      // writes subject header onto the same file as above
				"Task A2: Highest Temperature For " + country + " in " + year);
		weatherObj.writeDataToFile("data/taskA2_climate_info.csv",               // writes data onto the specified file using taskA2P2 array
				"Temperature, Year, Month, Country, Country_Code", taskA2p2);

		
		// Task A3 - Temperature Data given a country and two temp ranges
		System.out.println("Task A3 : Temperature Data given a country and two temperature ranges");
		in.nextLine();
		System.out.println("Enter a country: "); 
		while (in.hasNext()) {                                              //checks for country. if it is invalid, user must enter another country input until correct
			country = in.nextLine().toLowerCase();
			if (!allCountries.contains(country)) {
				System.out.println("Not a valid country in the database. Re-enter one: ");
				country = in.nextLine().toLowerCase();
				if (allCountries.contains(country)) {
					break;
				}
				
			}
			if (allCountries.contains(country)) {
				break;
			}
			System.out.println("Not a valid country in the database. Re-enter one: ");
			
		}
		System.out.println("Enter the lowest CELSIUS temperature range you want between " + minTemp + " and " + maxTemp + ":");
		while (!in.hasNextDouble()) {                                    // checks for a valid temperature
			System.out.println("Invalid temperature. Please enter a temperature between the given range: " + minTemp + " to " + maxTemp);
			in.next();                                                   // gives user a selection of valid temps to use if not valid
		}
		temp = in.nextDouble();
		while (temp < minTemp || temp > maxTemp) {
			System.out.println("Invalid temperature. Please enter a temperature between the given range: " + minTemp + " to " + maxTemp);
			temp = in.nextDouble();
		}
		System.out.println("Enter the highest CELSIUS temperature range you want between " + temp + " and " + maxTemp + ":");
		while (!in.hasNextDouble()) {
			System.out.println("Invalid temperature. Please enter a temperature between the given range: " + minTemp + " to " + maxTemp);
			in.next();
		}
		double temp2 = in.nextDouble();                                 // another temp variable is created to hold the second temp
		while (temp2 < minTemp || temp2 > maxTemp || temp >= temp2) {   // same process is used to check for valid temp
			System.out.println("Invalid temperature. Please enter a temperature between the range: " + temp + " to " + maxTemp);
			temp2 = in.nextDouble();
		}
		ArrayList<ITemperature> taskA3 = new ArrayList<>(this.getTempWithinRange(country, temp, temp2));     // same process as other tasks. Uses arrayList to store temperature obj
		weatherObj.writeSubjectHeaderInFile("data/taskA3_climate_info.csv", "Task A3: All Temperature Data for "
				+ country + " between the two temperatures: " + temp + "C and " + temp2 + "C");
		weatherObj.writeDataToFile("data/taskA3_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", taskA3);


		// Task A4 : Lowest AND Highest Temperature by Country
		System.out.println("Task A4 : Lowest AND Highest Temp By Country");
		in.nextLine();
		System.out.println("Enter a country: "); 
		while (in.hasNext()) {
			country = in.nextLine().toLowerCase();
			if (!allCountries.contains(country)) {
				System.out.println("Not a valid country in the database. Re-enter one: ");
				country = in.nextLine().toLowerCase();
				if (allCountries.contains(country)) {
					break;
				}
				
			}
			if (allCountries.contains(country)) {
				break;
			}
			System.out.println("Not a valid country in the database. Re-enter one: ");
			
		}
		ArrayList<ITemperature> taskA4p1 = new ArrayList<>();                          // Task A4 Part 1 - Lowest Temp by country
		taskA4p1.add(this.getLowestTempYearByCountry(country));
		weatherObj.writeSubjectHeaderInFile("data/taskA4_climate_info.csv",
				"Task A4: Lowest Temperature For " + country);
		weatherObj.writeDataToFile("data/taskA4_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", taskA4p1);

		ArrayList<ITemperature> taskA4p2 = new ArrayList<>();                         // Task A4 Part 2 - Highest Temp by country
		taskA4p2.add(this.getHighestTempYearByCountry(country));
		weatherObj.writeSubjectHeaderInFile("data/taskA4_climate_info.csv",
				"Task A4: Highest Temperature For " + country);
		weatherObj.writeDataToFile("data/taskA4_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", taskA4p2);


		// Task B1 : Top 10 countries with the lowest AND highest temp by month
		System.out.println("Task B1 : Top 10 countries with the lowest AND highest Temperature by month");
		System.out.println("Enter a month's number (1-12): ");                        // checks for a valid month
		while (!in.hasNextInt()) {
			System.out.println("Invalid Month. Re-enter one (1-12): ");
			in.next();
		}
		month = in.nextInt();
		while ((month > 12 || month < 1)) {
			System.out.println("Invalid Month. Make sure it is 1-12: ");
			if (in.hasNextInt()) {
				month = in.nextInt();
				if (month >= 1 && month <=12) {
					break;
				}
			}
			else {
				in.next();
			}
		}
		weatherObj.writeSubjectHeaderInFile("data/taskB1_climate_info.csv",                              
				"Task B1: Top 10 countries with the lowest temperature for " + monthMap.get(month));
		weatherObj.writeDataToFile("data/taskB1_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", this.allCountriesGetTop10LowestTemp(month));  // Task B1 Part 1: Top 10 Lowest Temp

		weatherObj.writeSubjectHeaderInFile("data/taskB1_climate_info.csv",
				"Task B1: Top 10 countries with the highest temperature for " + monthMap.get(month));
		weatherObj.writeDataToFile("data/taskB1_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", this.allCountriesGetTop10HighestTemp(month)); // Task B1 Part 2: Top 10 Highest Temp

		// Task B2
		// Task B2 Part 1 - Top 10 countries with the lowest temp overall
		System.out.println("Task B2 Part 1 : Top 10 countries with the lowest temp (COMPLETED- no action required)");
		weatherObj.writeSubjectHeaderInFile("data/taskB2_climate_info.csv",
				"Task B2 Part 1: Top 10 countries with the lowest temperature");
		weatherObj.writeDataToFile("data/taskB2_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", this.allCountriesGetTop10LowestTemp());

		// Task B2 Part 2 - Top 10 countries with the highest temp overall
		System.out.println("Task B2 Part 2 : Top 10 countries with the highest temp (COMPLETED - no action required)");
		weatherObj.writeSubjectHeaderInFile("data/taskB2_climate_info.csv",
				"Task B2 Part 1: Top 10 countries with the highest temperature");
		weatherObj.writeDataToFile("data/taskB2_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code", this.allCountriesGetTop10HighestTemp());

		// Task B3 - Get all countries with temperatures between the temperature range
		System.out.println("Task B3 : Temperature Data given two temperature ranges");
		System.out.println("Enter the lowest CELSIUS temperature range you want between " + minTemp + " and " + maxTemp + ":");
		while (!in.hasNextDouble()) {
			System.out.println("Invalid temperature. Please enter a temperature between the given range: " + minTemp + " to " + maxTemp);
			in.next();
		}
		temp = in.nextDouble();
		while (temp < minTemp || temp > maxTemp) {
			System.out.println("Invalid temperature. Please enter a temperature between the given range: " + minTemp + " to " + maxTemp);
			temp = in.nextDouble();
		}
		System.out.println("Enter the highest CELSIUS temperature range you want between " + temp + " and " + maxTemp + ":");
		while (!in.hasNextDouble()) {
			System.out.println("Invalid temperature. Please enter a temperature between the given range: " + minTemp + " to " + maxTemp);
			in.next();
		}
		temp2 = in.nextDouble();
		while (temp2 < minTemp || temp2 > maxTemp || temp >= temp2) {
			System.out.println("Invalid temperature. Please enter a temperature between the range: " + temp + " to " + maxTemp);
			temp2 = in.nextDouble();
		}
		weatherObj.writeSubjectHeaderInFile("data/taskB3_climate_info.csv",
				"Task B3: All Temperature Data between the two temperatures: " + temp + "C and " + temp2 + "C");
		weatherObj.writeDataToFile("data/taskB3_climate_info.csv",
				"Temperature, Year, Month, Country, Country_Code",
				this.allCountriesGetAllDataWithinTempRange(temp, temp2));

		// Task C1 - gets 10 top countries with the largest delta temp given the month and 2 years
		System.out.println("Task C1 : Top 10 countries with the largest delta change given the month and 2 years");
		System.out.println("Enter a month's number (1-12): ");
		while (!in.hasNextInt()) {                                                //checks for a valid month
			System.out.println("Invalid Month. Re-enter one (1-12): ");
			in.next();
		}
		month = in.nextInt();      
		while ((month > 12 || month < 1)) {                                     
			System.out.println("Invalid Month. Make sure it is 1-12: ");
			if (in.hasNextInt()) {
				month = in.nextInt();
				if (month >= 1 && month <=12) {
					break;
				}
			}
			else {
				in.next();
			}
		}
		System.out.println("Enter a year between " + minYear + " and " + maxYear + ":"); // checks for valid year1. includes years available for users to see!
		while (!in.hasNextInt()) {
			System.out.println("Not a valid year in the database. Re-enter one between " + minYear + " and " + maxYear + ":");
			in.next();
		}
		while (in.hasNextInt()) {
			year = in.nextInt();
			if (!allYears.contains(year)) {
				System.out.println("Not a valid year in the database. Re-enter one between " + minYear + " and " + maxYear + ":");
			} 
			else if (allYears.contains(year) && (year >= minYear && year <= maxYear)){
				break;
			}
		}
		System.out.println("Enter a second year between " + minYear + " and " + maxYear + ":");  
		while (!in.hasNextInt()) {                                                     // checks for valid year2. includes years available for users to see!
			System.out.println("Not a valid year in the database. Re-enter one between " + minYear + " and " + maxYear + ":");
			in.next();
		}
		int year2 = 0;
		while (in.hasNextInt()) {                                                           
			year2 = in.nextInt();
			if (!allYears.contains(year2)) {
				System.out.println("Not a valid year in the database. Re-enter one between " + minYear + " and " + maxYear + ":");
			} else if (allYears.contains(year2) && (year2 >= minYear && year2 <= maxYear)){
				break;
			}
		}	
		weatherObj.writeSubjectHeaderInFile("data/taskC1_climate_info.csv",
				"Task C1: Top 10 countries with greatest temperature delta For " + monthMap.get(month) + " and " + year + " and " + year2);
		weatherObj.writeDataToFile("data/taskC1_climate_info.csv",
				"Delta Temperature, Delta Year, Month, Country, Country_Code",
				this.allCountriesTop10TempDelta(month, year, year2));
		
		// Last note to user so that they know what happened :)
		System.out.println("ALL DONE! You can now check files inside the data folder to see your results :)");
	}

	
	// Runs the program....
	// Creates a ClimateAnalyzer obj that takes in a data file and performs the runClimateAnalyzer method.
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
		ClimateAnalyzer obj = new ClimateAnalyzer("data/world_temp_2000-2016.csv");
		obj.runClimateAnalyzer();
		
	}
}
