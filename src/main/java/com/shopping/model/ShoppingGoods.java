package com.shopping.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "shopping_goods")
public class ShoppingGoods implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2185787183085251150L;
	@Id
	@Column(name = "Id", nullable = false, length = 40)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	protected String id;
	@Column(name = "name", length = 40)
	protected String name;
	@Column(name = "content", length = 40)
	protected String content;
	
	protected Image titleImages;
	protected List<Image> showImages;
	protected Double price;
	protected Double realPrice;
	
}
