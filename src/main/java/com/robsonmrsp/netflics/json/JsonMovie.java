package com.robsonmrsp.netflics.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;


/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
public class JsonMovie implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String sinopsis;
	private LocalDate releaseDate;  			
	private Double budget;
	private String title;
	private JsonLanguage language;		
	private ArrayList<JsonActor> cast = new ArrayList<JsonActor>();		
	private ArrayList<JsonGenre> genres = new ArrayList<JsonGenre>();		
	
	public  JsonMovie() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}				
								
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public JsonLanguage getLanguage() {
		return language;
	}
	
	public void setLanguage(JsonLanguage language) {
		this.language = language;
	}
	public ArrayList<JsonActor> getCast() {
		return cast;
	}
	
	public void setCast(ArrayList<JsonActor> actor) {
		this.cast = actor;
	}

	public ArrayList<JsonGenre> getGenres() {
		return genres;
	}
	
	public void setGenres(ArrayList<JsonGenre> genre) {
		this.genres = genre;
	}


}
//generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20