/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
package com.robsonmrsp.netflics.service;
import java.util.Optional;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.robsonmrsp.netflics.core.model.Tenant;
import com.robsonmrsp.netflics.model.Genre;
import com.robsonmrsp.netflics.model.filter.FilterGenre;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;

public interface GenreService {

	
	public Optional<Genre> get(Integer id, Tenant tenant) ;

	public Pager<Genre> get(SearchParameters<FilterGenre> searchParams, Tenant tenant) ;
	
	public Boolean delete(Integer id, Tenant tenant);

	public Genre save(Genre entity) ;

	public Genre update(Genre entity) ;

}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56