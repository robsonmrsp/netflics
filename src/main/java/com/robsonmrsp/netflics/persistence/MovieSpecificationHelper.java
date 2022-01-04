package com.robsonmrsp.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.robsonmrsp.netflics.model.Movie;
import com.robsonmrsp.netflics.model.filter.FilterMovie;

import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import com.robsonmrsp.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class MovieSpecificationHelper {

	public static Specification<Movie> fromId(Integer id, Tenant tenant) {
		return new Specification<Movie>() {
			@Override
			public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Movie> filter(SearchParameters<FilterMovie> searchParam, Tenant tenant) {
		return new Specification<Movie>() {

			@Override
			public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterMovie filterMovie = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterMovie.getSinopsis() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("sinopsis")), Util.wrapSufix(filterMovie.getSinopsis().toUpperCase(), searchParam.isExact())));
				}  
				if (filterMovie.getReleaseDate() != null) {
					predicates.add(criteriaBuilder.equal(root.get("releaseDate"), filterMovie.getReleaseDate()));
				}				
				if (filterMovie.getBudget() != null) {
					predicates.add(criteriaBuilder.equal(root.get("budget"), filterMovie.getBudget()));
				}				
				if (filterMovie.getTitle() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("title")), Util.wrapSufix(filterMovie.getTitle().toUpperCase(), searchParam.isExact())));
				}  
				if (filterMovie.getLanguage() != null) {
					predicates.add(criteriaBuilder.equal(root.get("language").get("id"), filterMovie.getLanguage()));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
