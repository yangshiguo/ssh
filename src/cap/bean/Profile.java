package cap.bean;

/**
 * Profile entity. @author Hibernate Tools
 */

public class Profile implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String firstName;
	private String lastName;
	private Boolean gender;
	private String strGender;
	private String telephone;

	// Constructors

	/** default constructor */
	public Profile() {
	}

	/** full constructor */
	public Profile(User user, String firstName, String lastName,
			Boolean gender, String telephone) {
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.telephone = telephone;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStrGender() {
		return strGender;
	}

	public void setStrGender(String strGender) {
		this.strGender = strGender;
	}
	

}