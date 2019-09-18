package com.sogeti.api.filmland.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author C28278
 *
 */
@Entity
public class Category {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	
	private String name;
	private int availableContent;
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private double price;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the availableContent
	 */
	public int getAvailableContent() {
		return availableContent;
	}

	/**
	 * @param availableContent the availableContent to set
	 */
	public void setAvailableContent(int availableContent) {
		this.availableContent = availableContent;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}