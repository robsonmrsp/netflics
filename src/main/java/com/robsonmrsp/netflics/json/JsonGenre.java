package com.robsonmrsp.netflics.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;


/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
public class JsonGenre implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	public  JsonGenre() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
//generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20