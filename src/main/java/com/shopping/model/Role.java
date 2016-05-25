package com.shopping.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7799245658379972773L;
	@Id
	@Column(name = "Id", nullable = false, length = 40)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;
	@Column(name = "name", length = 40)
	protected String name;
	@Column(name = "namekey", length = 40)
	protected String namekey;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "role_has_url", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "url_id", nullable = false, updatable = false) })
	protected List<Url> urls = new ArrayList<Url>(0);
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

	public String getNamekey() {
		return namekey;
	}

	public void setNamekey(String namekey) {
		this.namekey = namekey;
	}

	
}
