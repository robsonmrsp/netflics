package com.robsonmrsp.netflics.model.filter;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
public class FilterUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;  			
	private String username;  			
	private String email;  			
	private String password;  			
	private Boolean enable;  			
	private String image;  			

	
	public  FilterUser() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
		
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56