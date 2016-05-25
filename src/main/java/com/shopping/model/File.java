package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "file")
public class File implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7283423860338560990L;
	@Id
	@Column(name = "Id", nullable = false, length = 40)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;
	protected String name;
	protected String url;
	protected String localte;
}
