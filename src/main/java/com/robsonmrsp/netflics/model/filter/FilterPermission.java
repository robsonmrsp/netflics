package com.robsonmrsp.netflics.model.filter;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
public class FilterPermission implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;  			
	private String description;  			
	private String operation;  			
	private String tagReminder;  			

	private Integer item;		
	
	public  FilterPermission() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getTagReminder() {
		return tagReminder;
	}

	public void setTagReminder(String tagReminder) {
		this.tagReminder = tagReminder;
	}
		
	public Integer getItem() {
		return item;
	}
	
	public void setItem(Integer item) {
		this.item = item;
	}
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56