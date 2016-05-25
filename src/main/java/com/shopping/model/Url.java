package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "url")
public class Url implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2418131129542516236L;
	@Id
	@Column(name = "Id", nullable = false, length = 40)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;
	@Column(name = "name", length = 40)
	protected String name;
	@Column(name = "name_url", length = 40)
	protected String nameUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameUrl() {
		return nameUrl;
	}
	public void setNameUrl(String nameUrl) {
		this.nameUrl = nameUrl;
	}
	
}
