package cap.bean;

/**
 * BlogInfo entity. @author Hibernate Tools
 */

public class BlogInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String blogName;
	private String description;
	private String annoucement;

	// Constructors

	/** default constructor */
	public BlogInfo() {
	}

	/** minimal constructor */
	public BlogInfo(User user, String blogName) {
		this.user = user;
		this.blogName = blogName;
	}

	/** full constructor */
	public BlogInfo(User user, String blogName, String description,
			String annoucement) {
		this.user = user;
		this.blogName = blogName;
		this.description = description;
		this.annoucement = annoucement;
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

	public String getBlogName() {
		return this.blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnnoucement() {
		return this.annoucement;
	}

	public void setAnnoucement(String annoucement) {
		this.annoucement = annoucement;
	}

}