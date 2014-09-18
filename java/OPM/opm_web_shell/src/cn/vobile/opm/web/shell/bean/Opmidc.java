package cn.vobile.opm.web.shell.bean;

import java.sql.Timestamp;

/**
 * Opmidc entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Opmidc implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String location;
	private String image;
	private String description;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Constructors

	/** default constructor */
	public Opmidc() {
	}

	/** minimal constructor */
	public Opmidc(String name, String location, Timestamp updatedAt) {
		this.name = name;
		this.location = location;
		this.updatedAt = updatedAt;
	}

	/** full constructor */
	public Opmidc(String name, String location, String image,
			String description, Timestamp createdAt, Timestamp updatedAt) {
		this.name = name;
		this.location = location;
		this.image = image;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
