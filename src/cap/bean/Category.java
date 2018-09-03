package cap.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Category entity. @author Hibernate Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String categoryName;
	private Integer articals;
	private Boolean isDelete;
	private Set articles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(User user, String categoryName, Integer articals,
			Boolean isDelete) {
		this.user = user;
		this.categoryName = categoryName;
		this.articals = articals;
		this.isDelete = isDelete;
	}

	/** full constructor */
	public Category(User user, String categoryName, Integer articals,
			Boolean isDelete, Set articles) {
		this.user = user;
		this.categoryName = categoryName;
		this.articals = articals;
		this.isDelete = isDelete;
		this.articles = articles;
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

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getArticals() {
		return this.articals;
	}

	public void setArticals(Integer articals) {
		this.articals = articals;
	}

	public Boolean getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Set getArticles() {
		return this.articles;
	}

	public void setArticles(Set articles) {
		this.articles = articles;
	}

}