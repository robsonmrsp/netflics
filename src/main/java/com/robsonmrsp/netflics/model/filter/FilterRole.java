package com.robsonmrsp.netflics.model.filter;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
public class FilterRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private String authority;  			
	private String description;  			

	
	public  FilterRole() {
		
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
		
}
//generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20