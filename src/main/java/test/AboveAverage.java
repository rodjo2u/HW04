package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Reads decimal numbers from standard input till "quit" is entered. Then writes
 * out ascendingly sorted numbers which are at least 20% larger than the
 * average.
 * 
 * @author Igor Petkovski
 * 
 */
public class AboveAverage {

	public static void main(String[] args) {

		List<Double> numbers = new ArrayList<Double>();
		double sum = 0.0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			String input;

			while ((input = br.readLine()) != null) {
				if (input.equals("quit")) {
					break;
				}
				numbers.add(Double.parseDouble(input));
				sum += Double.parseDouble(input);
			}

		} catch (IOException io) {
			io.printStackTrace();
		}

		double avg = sum / numbers.size();
		List<Double> newList = new ArrayList<Double>();
		for (Double number : numbers) {
			if (number > 1.2 * avg || number.compareTo(avg * 1.2) == 0) {
				newList.add(number);
			}
		}
		Collections.sort(newList);

		System.out.println("1.2 * average is: " + 1.2 * avg);
		for (double number : newList) {
			System.out.println(number);
		}
	}

}
