package hr.fer.zemris.java.tecaj.hw4.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implements filter for student record last name regex with only one wildcard
 * char '*' last name query.
 * 
 * @author Igor Petkovski
 * 
 */
public class LastNameFilter implements IFilter {

	private String regex;

	/**
	 * Initializes regex string which will later be used to filter last names.
	 * 
	 * @param filter
	 *            user inputed string containing only 1 wildcard * character
	 *            anywhere in the string.
	 */
	public LastNameFilter(String filter) {
		regex = filter.replaceAll("\\*", "\\\\w\\*-?w\\*\\\\s?\\\\w*-?w\\*");
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj.hw04.db.IFilter#accepts(boolean)
	 */
	@Override
	public boolean accepts(StudentRecord record) {
		Pattern p = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
		Matcher m = p.matcher(record.getLastName());
		if (m.matches()) {
			return true;
		}
		return false;
	}

}
