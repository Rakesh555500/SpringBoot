package com.sogeti.api.filmland.model;

import static com.sogeti.api.filmland.constant.Constants.SIMPLE_DATE_FORMAT;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rakesh
 *
 */
public class SubscribedCategoryDto {
	private String name;
	private int remainingContent;
	private String startDate;
	private double price;

	public static SubscribedCategoryDto fromEntity(SubscribeCategory subscribeCategory) {
		SubscribedCategoryDto dto = new SubscribedCategoryDto();
		dto.setName(subscribeCategory.getCategory().getName());
		dto.setRemainingContent(subscribeCategory.getRemainingContent());
		dto.setPrice(subscribeCategory.getCategory().getPrice());
		dto.setStartDate(SIMPLE_DATE_FORMAT.format(subscribeCategory.getStartDate()));
		return dto;
	}

	public static List<SubscribedCategoryDto> fromEntityList(List<SubscribeCategory> subscribeCategories) {
		List<SubscribedCategoryDto> dtoList = new ArrayList<>();
		subscribeCategories.forEach(item -> {
			SubscribedCategoryDto dto = fromEntity(item);
			dtoList.add(dto);
		});
		return dtoList;
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
	 * @return the remainingContent
	 */
	public int getRemainingContent() {
		return remainingContent;
	}

	/**
	 * @param remainingContent the remainingContent to set
	 */
	public void setRemainingContent(int remainingContent) {
		this.remainingContent = remainingContent;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
