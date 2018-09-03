package cap.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * SysCategory entity. @author Hibernate Tools
 */

public class SysCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String categoryName;
	private Integer articals;
	private Boolean isDelete;
	private Set articles = new HashSet(0);

	// Constructors

	/** default constructor */
	public SysCategory() {
	}

	/** minimal constructor */
	public SysCategory(String categoryName, Boolean isDelete) {
		this.categoryName = categoryName;
		this.isDelete = isDelete;
	}

	/** full constructor */
	public SysCategory(String categoryName, Integer articals, Boolean isDelete,
			Set articles) {
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