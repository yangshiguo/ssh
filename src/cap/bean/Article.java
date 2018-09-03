package cap.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Article entity. @author Hibernate Tools
 */

public class Article implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private SysCategory sysCategory;
	private Category category;
	private String title;
	private String content;
	private String summary;
	private Timestamp publishTime;
	private Boolean isTop;
	private Boolean isDelete;
	private Integer count;
	private Set comments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(User user, SysCategory sysCategory, Category category,
			String title, String content, String summary,
			Timestamp publishTime, Boolean isTop, Boolean isDelete,
			Integer count) {
		this.user = user;
		this.sysCategory = sysCategory;
		this.category = category;
		this.title = title;
		this.content = content;
		this.summary = summary;
		this.publishTime = publishTime;
		this.isTop = isTop;
		this.isDelete = isDelete;
		this.count = count;
	}

	/** full constructor */
	public Article(User user, SysCategory sysCategory, Category category,
			String title, String content, String summary,
			Timestamp publishTime, Boolean isTop, Boolean isDelete,
			Integer count, Set comments) {
		this.user = user;
		this.sysCategory = sysCategory;
		this.category = category;
		this.title = title;
		this.content = content;
		this.summary = summary;
		this.publishTime = publishTime;
		this.isTop = isTop;
		this.isDelete = isDelete;
		this.count = count;
		this.comments = comments;
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

	public SysCategory getSysCategory() {
		return this.sysCategory;
	}

	public void setSysCategory(SysCategory sysCategory) {
		this.sysCategory = sysCategory;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Boolean getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	public Boolean getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

}