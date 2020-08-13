package climatechange;

import java.util.*;
import java.io.*;

//
// WeatherIO class implements IWeatherIO
// This class reads given data files, creates a new file and adds headers, and writes data results back into a new file.
//
public class WeatherIO implements IWeatherIO {

	// read all data from the weather data file and returns an arrayList of
	// ITemperature objects.
	// takes in the filename that contains all the data.
	public ArrayList<ITemperature> readDataFromFile(String fileName) throws IOException {
		ArrayList<ITemperature> myList = new ArrayList<>();
		File file = new File(fileName);

		// Checks to see if the file exists!
		if (!file.exists()) {
			System.out.println("File cannot be found or does not exist!");
			return null;
		}

		Scanner sc = new Scanner(file);
		sc.nextLine(); // This makes sure to skip over the header/topic and go straight to temperature
					   // data!

		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			// Splits the line into an array in order to turn it into a temperature object
			String[] currentLine = line.split(",\\s*");

			// creates a Temperature obj and adds it to the ArrayList
			double temp = Double.parseDouble(currentLine[0]);
			int year = Integer.parseInt(currentLine[1]);
			String month = currentLine[2];
			String country = currentLine[3];
			String code = currentLine[4];
			ITemperature obj = new Temperature(temp, year, month, country, code);
			myList.add(obj);
		}

		sc.close();

		return myList;
	}

	// writes the subject header before dumping data returned from each
	// ClimateAnalyzer method
	// takes in a filename that would be turned into a new file if it does not exist
	// yet. The subject header is put into the file.
	public void writeSubjectHeaderInFile(String filename, String subject) throws IOException {

		try {
			FileWriter fw = new FileWriter(filename, true); // if filename exists, it will be appendable.
			BufferedWriter bw = new BufferedWriter(fw); // a buffered writer writes to the file.

			bw.write(subject);
			bw.newLine();

			bw.close();
			fw.close();
		} catch (Exception e) { // If there is any issue, a statement will be returned to the user.
			System.out.println("The given exception: " + e);
			e.printStackTrace();
		}
	}

	// writes the data into an existing/new file under "filename".
	// takes in a topic, which describes the data that will be returned onto the
	// file.
	// takes in an arraylist with the desired data and adds it to the file.
	public void writeDataToFile(String filename, String topic, ArrayList<ITemperature> theWeatherList)
			throws IOException {

		try {
			FileWriter fw = new FileWriter(filename, true); // if filename exists, it will be appendable.
			BufferedWriter bw = new BufferedWriter(fw); // buffered writer takes in the file writer.

			bw.write(topic);
			bw.newLine();
			for (int i = 0; i < theWeatherList.size(); i++) {
				bw.write(theWeatherList.get(i).toString());
				bw.newLine();
			}

			bw.newLine(); // adds a new line to the file so the next task given will be more visible to
							// the user.

			bw.close();
			fw.close();

		} catch (Exception e) { // If there is any issue, a statement will be returned to the user.
			System.out.println("The given exception: " + e);
			e.printStackTrace();
		}
	}

	// Used to test the functions above!
	public static void main(String[] args) throws IOException {
		System.out.println("Starting");
		String filename = "data/Hello.csv";
		WeatherIO obj = new WeatherIO();
		ArrayList<ITemperature> list = new ArrayList<>();
		ArrayList<ITemperature> list2 = new ArrayList<>();
		Temperature test2 = new Temperature(54.9381209381, 2001, "Jan", "America", "USA");
		Temperature test3 = new Temperature(10.324876234, 1999, "Feb", "Korea", "KO");
		list.add(test3);
		list.add(test2);
		list2.add(test3);

		obj.writeSubjectHeaderInFile(filename, "THIS IS MY HEADER");
		obj.writeDataToFile(filename, "Temp, Year, Month, Country, Country_Code", list);

		System.out.println("Done");
	}
}
