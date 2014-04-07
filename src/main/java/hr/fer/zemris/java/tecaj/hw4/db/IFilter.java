package hr.fer.zemris.java.tecaj.hw4.db;

/**
 * Class for all possible filters of Student records.
 * @author Igor Petkovski
 *
 */
public interface IFilter {

	/**
	 * Returns true if given student record complies with instantiated Filter.
	 * 
	 * @param record - student record, must be not null
	 * @return true or false
	 */
	public boolean accepts(StudentRecord record);
	
}
