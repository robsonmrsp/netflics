/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
package com.robsonmrsp.netflics.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.robsonmrsp.netflics.core.model.Tenant ;

import com.robsonmrsp.netflics.model.Movie;
import com.robsonmrsp.netflics.persistence.MovieRepository;
import com.robsonmrsp.netflics.persistence.MovieSpecificationHelper;
import com.robsonmrsp.netflics.model.filter.FilterMovie;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.rs.exception.ValidationException;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.DateUtil;
import com.robsonmrsp.netflics.core.utils.Util;

@Service
@Transactional
public class MovieServiceImp implements MovieService {

	public static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImp.class);
	
	@Autowired
	MovieRepository movieRepository;
	
	public Optional<Movie> get(Integer id, Tenant tenant) {
		return movieRepository.findOne(MovieSpecificationHelper.fromId(id, tenant));
	}

	public Pager<Movie> get(SearchParameters<FilterMovie> searchParams, Tenant tenant) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Movie> page = movieRepository.findAll(MovieSpecificationHelper.filter(searchParams, tenant), pageRequest);

		return new Pager<Movie>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}

	public Boolean delete(Integer id, Tenant tenant) {
		Optional<Movie> optional = this.get(id, tenant);
		if (optional.isPresent()) {
			movieRepository.delete(optional.get());
		}
		return true;
	}

	public Movie save(Movie entity) {
		return movieRepository.save(entity);
	}

	public Movie update(Movie entity) {
		return movieRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56