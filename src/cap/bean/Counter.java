package cap.bean;

/**
 * Counter entity. @author Hibernate Tools
 */

public class Counter implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long num;

	// Constructors

	/** default constructor */
	public Counter() {
	}

	/** full constructor */
	public Counter(Long num) {
		this.num = num;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNum() {
		return this.num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

}