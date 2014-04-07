package hr.fer.zemris.java.tecaj.hw4.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Program reads file from current directory from file database.txt.
 * <p>
 * Later program calls StudentDatabase which parses the given data and creates
 * database emulated trough list of instances of type {@link StudentRecord}.
 * </p>
 * 
 * @author Igor Petkovski
 * 
 */
public class StudentDB {

	private static StudentDatabase sd;

	public static void main(String[] args) throws IOException {

		createDatabase(args[0]);

		waitForQueries();

	}

	private static void createDatabase(String path) throws IOException {
		try {
			List<String> lines = Files.readAllLines(Paths.get(path),
					StandardCharsets.UTF_8);

			sd = new StudentDatabase(lines);
		} catch (Exception e) {
			System.out
					.println("File datoteka.txt does not exist or is not formatted well!");
			e.printStackTrace();
			System.in.read();
			System.exit(1);
		}

	}

	/**
	 * Loop method which receives and responds to queries form command line.
	 */
	private static void waitForQueries() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			String input;

			while ((input = br.readLine()) != null) {
				if (input.equals("quit")) {
					break;
				}

				Pattern p = Pattern
						.compile(
								" *query *(lastName *= *\\\"(\\*\\w+|\\w+\\*\\w+|\\w+\\*|\\w+|\\*)\\\"|jmbag *= *\\\"\\d+\\\")",
								Pattern.UNICODE_CHARACTER_CLASS);
				Matcher m = p.matcher(input);
				if (!m.matches()) {
					System.out
							.println("You must enter query in form of:    query jmbag=  or  query lastName= ."
									+ "Last name query only accepts one wildcard charcter");
				} else {
					input = input.replaceFirst(" *query *", "");

					if (input.startsWith("jmbag")) {
						parseJmbagQuery(input.replaceFirst("jmbag *= *", "")
								.replaceAll("\"", ""));
					} else {
						parseLastNameQuery(input.replaceFirst("lastName *= *",
								"").replaceAll("\"", ""));
					}
				}

			}

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private static void parseJmbagQuery(String jmbag) {
		displayJmbagQuery(sd.forJMBAG(jmbag));
	}

	/**
	 * Method for formatting of output for single student.
	 * 
	 * @param student
	 */
	private static void displayJmbagQuery(StudentRecord student) {
		if (student == null) {
			System.out.println("Records selected: 0");
		} else {
			String firstAndLastLine = "+="
					+ buildString('=', student.getJmbag().length()) + "=+="
					+ buildString('=', student.getLastName().length()) + "=+="
					+ buildString('=', student.getFirstName().length())
					+ "=+===+";
			System.out.println(firstAndLastLine);
			displayStudent(student, student.getLastName().length(), student
					.getFirstName().length());
			System.out.println(firstAndLastLine);
			System.out.println("Records selected: 1");
		}
	}

	private static void parseLastNameQuery(String lastNameRegex) {
		LastNameFilter lnFilter = new LastNameFilter(lastNameRegex);
		List<StudentRecord> filteredStudentRecords = sd.filter(lnFilter);

		String firstAndLastLine = "+==========="
				+ "=+="
				+ buildString('=',
						sd.getLongestLastName(filteredStudentRecords)) + "=+="
				+ buildString('=', sd.getLongestName(filteredStudentRecords))
				+ "=+===+";

		System.out.println(firstAndLastLine);
		int longestLastName = sd.getLongestLastName(filteredStudentRecords);
		int longestName = sd.getLongestName(filteredStudentRecords);
		Collections.sort(filteredStudentRecords);
		for (StudentRecord student : filteredStudentRecords) {
			displayStudent(student, longestLastName, longestName);
		}
		System.out.println(firstAndLastLine);
		System.out
				.println("Records selected: " + filteredStudentRecords.size());
	}

	private static void displayStudent(StudentRecord student,
			int lastNameLength, int nameLength) {
		System.out
				.println("| "
						+ student.getJmbag()
						+ " | "
						+ student.getLastName()
						+ buildString(' ', lastNameLength
								- student.getLastName().length())
						+ " | "
						+ student.getFirstName()
						+ buildString(' ', nameLength
								- student.getFirstName().length()) + " | "
						+ student.getFinalGrade() + " |");
	}

	/**
	 * Concatenates char c times times into string.
	 * 
	 * @param c
	 *            char to concatenate
	 * @param times
	 *            times to concatenate
	 * @return new String
	 */
	private static String buildString(char c, int times) {
		if (times == 0) {
			return "";
		}
		String output = String.valueOf(c);
		for (int i = 1; i < times; i++) {
			output = output.concat(String.valueOf(c));
		}
		return output;
	}
}
