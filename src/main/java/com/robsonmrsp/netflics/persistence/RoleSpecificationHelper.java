package com.robsonmrsp.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.robsonmrsp.netflics.model.Role;
import com.robsonmrsp.netflics.model.filter.FilterRole;

import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
public class RoleSpecificationHelper {

	public static Specification<Role> filter(SearchParameters<FilterRole> searchParam) {
		return new Specification<Role>() {

			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterRole filterRole = searchParam.getFilter();
				if (filterRole.getAuthority() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("authority")), Util.wrapSufix(filterRole.getAuthority().toUpperCase(), searchParam.isExact())));
				}  
				if (filterRole.getDescription() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("description")), Util.wrapSufix(filterRole.getDescription().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
