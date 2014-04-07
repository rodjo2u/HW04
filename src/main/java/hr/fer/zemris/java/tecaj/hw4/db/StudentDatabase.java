package hr.fer.zemris.java.tecaj.hw4.db;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Class contains HasMap instance of all the students submitted trough list of
 * strings.
 * <p>
 * Every line must contain strings: jmbag, lastName, firstName and finalGrade.
 * </p>
 * 
 * @author Igor Petkovski
 * 
 */
public class StudentDatabase {

	private Map<String, StudentRecord> database = new HashMap<>();

	public StudentDatabase(List<String> lines) {
		// parse lines
		String[] student;
		for (String line : lines) {
			// and add them to database
			student = line.split("\t");
			database.put(student[0], new StudentRecord(student[0], student[1],
					student[2], student[3]));
		}
	}

	/**
	 * Method searches the database for the student with given JMBAG. If student
	 * does not exist method returns <code>null1</code>, else
	 * {@link StudentRecord}.
	 * 
	 * @param jmbag
	 *            - String student unique identifier
	 * @return - <code>null1</code>, or {@link StudentRecord}
	 */
	public StudentRecord forJMBAG(String jmbag) {
		if (database.get(jmbag) == null) {
			return null;
		} else {
			return database.get(jmbag);
		}
	}

	/**
	 * Filters trough students with submitted IFilter filter instance.
	 * 
	 * @param filter
	 * @return
	 */
	public List<StudentRecord> filter(IFilter filter) {
		List<StudentRecord> newStudentList = new LinkedList<>();
		for (StudentRecord student : database.values()) {
			if (filter.accepts(student)) {
				newStudentList.add(student);
			}
		}
		return newStudentList;
	}

	/**
	 * Returns int value of longest surname in submitted student list.
	 * 
	 * @param list
	 *            -students list
	 * @return int value
	 */
	public int getLongestName(List<StudentRecord> list) {
		int longestName = 0;
		for (StudentRecord student : list) {
			if (student.getFirstName().length() > longestName) {
				longestName = student.getFirstName().length();
			}
		}
		return longestName;
	}

	/**
	 * Returns int value of longest name in submitted student list.
	 * 
	 * @param list
	 *            -students list
	 * @return int value
	 */
	public int getLongestLastName(List<StudentRecord> list) {
		int longestName = 0;
		for (StudentRecord student : list) {
			if (student.getLastName().length() > longestName) {
				longestName = student.getLastName().length();
			}
		}
		return longestName;
	}
}
