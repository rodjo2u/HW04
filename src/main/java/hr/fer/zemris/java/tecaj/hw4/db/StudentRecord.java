package hr.fer.zemris.java.tecaj.hw4.db;

/**
 * Class representing one row of the emulated database. It contains all data
 * describing single student.
 * <p>
 * Class contains strings: jmbag, lastName, firstName, finalGrade. Methods
 * equals, hashCode and compare all work with only jmbag field.
 * </p>
 * 
 * @author Oberon
 * 
 */
public class StudentRecord implements Comparable<StudentRecord> {

	private String jmbag;
	private String lastName;
	private String firstName;
	private String finalGrade;

	public StudentRecord(String jmbag, String lastName, String firstName,
			String finalGrade) {
		super();
		this.jmbag = jmbag;
		this.lastName = lastName;
		this.firstName = firstName;
		this.finalGrade = finalGrade;
	}

	@Override
	public String toString() {
		return "StudentRecord [jmbag=" + jmbag + ", lastName=" + lastName
				+ ", firstName=" + firstName + ", finalGrade=" + finalGrade
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		return true;
	}

	/**
	 * @return the finalGrade
	 */
	public String getFinalGrade() {
		return finalGrade;
	}

	/**
	 * @param finalGrade
	 *            the finalGrade to set
	 */
	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	/**
	 * @return the jmbag
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	@Override
	public int compareTo(StudentRecord o) {
		if (jmbag.compareTo(o.jmbag) > 0) {
			return 1;
		}
		return -1;
	}

}
