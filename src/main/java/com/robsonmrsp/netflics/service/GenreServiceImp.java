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

import com.robsonmrsp.netflics.model.Genre;
import com.robsonmrsp.netflics.persistence.GenreRepository;
import com.robsonmrsp.netflics.persistence.GenreSpecificationHelper;
import com.robsonmrsp.netflics.model.filter.FilterGenre;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.rs.exception.ValidationException;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.DateUtil;
import com.robsonmrsp.netflics.core.utils.Util;

@Service
@Transactional
public class GenreServiceImp implements GenreService {

	public static final Logger LOGGER = LoggerFactory.getLogger(GenreServiceImp.class);
	
	@Autowired
	GenreRepository genreRepository;
	
	public Optional<Genre> get(Integer id, Tenant tenant) {
		return genreRepository.findOne(GenreSpecificationHelper.fromId(id, tenant));
	}

	public Pager<Genre> get(SearchParameters<FilterGenre> searchParams, Tenant tenant) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Genre> page = genreRepository.findAll(GenreSpecificationHelper.filter(searchParams, tenant), pageRequest);

		return new Pager<Genre>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}

	public Boolean delete(Integer id, Tenant tenant) {
		Optional<Genre> optional = this.get(id, tenant);
		if (optional.isPresent()) {
			genreRepository.delete(optional.get());
		}
		return true;
	}

	public Genre save(Genre entity) {
		return genreRepository.save(entity);
	}

	public Genre update(Genre entity) {
		return genreRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56