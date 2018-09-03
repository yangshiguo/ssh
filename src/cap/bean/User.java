package cap.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author Hibernate Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String username;
	private String password;
	private String newpassword1;
	private String newpassword2;
	private String password_confirm;
	private String email;
	private Boolean isApplied;
	private Boolean isDelete;
	private Boolean isProfile;
	private Set comments = new HashSet(0);
	private Set profiles = new HashSet(0);
	private Set categories = new HashSet(0);
	private Set blogInfos = new HashSet(0);
	private Set articles = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String email,
			Boolean isApplied, Boolean isDelete, Boolean isProfile) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.isApplied = isApplied;
		this.isDelete = isDelete;
		this.isProfile = isProfile;
	}

	/** full constructor */
	public User(String username, String password, String email,
			Boolean isApplied, Boolean isDelete, Boolean isProfile,
			Set comments, Set profiles, Set categories, Set blogInfos,
			Set articles) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.isApplied = isApplied;
		this.isDelete = isDelete;
		this.isProfile = isProfile;
		this.comments = comments;
		this.profiles = profiles;
		this.categories = categories;
		this.blogInfos = blogInfos;
		this.articles = articles;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsApplied() {
		return this.isApplied;
	}

	public void setIsApplied(Boolean isApplied) {
		this.isApplied = isApplied;
	}

	public Boolean getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsProfile() {
		return this.isProfile;
	}

	public void setIsProfile(Boolean isProfile) {
		this.isProfile = isProfile;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getProfiles() {
		return this.profiles;
	}

	public void setProfiles(Set profiles) {
		this.profiles = profiles;
	}

	public Set getCategories() {
		return this.categories;
	}

	public void setCategories(Set categories) {
		this.categories = categories;
	}

	public Set getBlogInfos() {
		return this.blogInfos;
	}

	public void setBlogInfos(Set blogInfos) {
		this.blogInfos = blogInfos;
	}

	public Set getArticles() {
		return this.articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

	public String getPassword_confirm() {
		return password_confirm;
	}

	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}

	public String getNewpassword1() {
		return newpassword1;
	}

	public void setNewpassword1(String newpassword1) {
		this.newpassword1 = newpassword1;
	}

	public String getNewpassword2() {
		return newpassword2;
	}

	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}
	
	

}