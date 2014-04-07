package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Program demonstrates usage of HasMap<K,V>.
 * <p>
 * Program reads the list of names form standard input and stops when "quit" is
 * entered. Program writes out list of names and number of their occurances.
 * 
 * @author Igor Petkovski
 * 
 */
public class NamesCounter {

	public static void main(String[] args) {

		Map<String, Integer> names = new HashMap<>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			String input;

			while ((input = br.readLine()) != null) {
				if (input.equals("quit")) {
					break;
				}
				if (names.containsKey(input)) {
					names.put(input, names.get(input).shortValue() + 1);
				} else
					names.put(input, 1);
			}

		} catch (IOException io) {
			io.printStackTrace();
		}

		System.out.println("Popis imena i broj koliko su puta unešena:");
		for (String name : names.keySet()) {
			System.out.println(name + ": " + names.get(name));
		}

	}

}
