package com.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "image")
public class Image implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6551205821183545935L;
	@Id
	@Column(name = "Id", nullable = false, length = 40)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;
	protected String name;
	protected File file;

}
