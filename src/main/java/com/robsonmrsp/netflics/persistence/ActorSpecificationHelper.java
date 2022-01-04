package com.robsonmrsp.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.robsonmrsp.netflics.model.Actor;
import com.robsonmrsp.netflics.model.filter.FilterActor;

import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import com.robsonmrsp.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class ActorSpecificationHelper {

	public static Specification<Actor> fromId(Integer id, Tenant tenant) {
		return new Specification<Actor>() {
			@Override
			public Predicate toPredicate(Root<Actor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Actor> filter(SearchParameters<FilterActor> searchParam, Tenant tenant) {
		return new Specification<Actor>() {

			@Override
			public Predicate toPredicate(Root<Actor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterActor filterActor = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterActor.getBirthDate() != null) {
					predicates.add(criteriaBuilder.equal(root.get("birthDate"), filterActor.getBirthDate()));
				}				
				if (filterActor.getName() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("name")), Util.wrapSufix(filterActor.getName().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
