package com.robsonmrsp.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.robsonmrsp.netflics.model.Language;
import com.robsonmrsp.netflics.model.filter.FilterLanguage;

import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import com.robsonmrsp.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class LanguageSpecificationHelper {

	public static Specification<Language> fromId(Integer id, Tenant tenant) {
		return new Specification<Language>() {
			@Override
			public Predicate toPredicate(Root<Language> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<Language> filter(SearchParameters<FilterLanguage> searchParam, Tenant tenant) {
		return new Specification<Language>() {

			@Override
			public Predicate toPredicate(Root<Language> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterLanguage filterLanguage = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterLanguage.getName() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("name")), Util.wrapSufix(filterLanguage.getName().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
