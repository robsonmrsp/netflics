package com.robsonmrsp.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.robsonmrsp.netflics.model.User;
import com.robsonmrsp.netflics.model.filter.FilterUser;

import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

import com.robsonmrsp.netflics.core.model.Tenant;
@SuppressWarnings("serial")
public class UserSpecificationHelper {

	public static Specification<User> fromId(Integer id, Tenant tenant) {
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
					predicates.add(criteriaBuilder.equal(root.get("id"), id));

				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
	public static Specification<User> filter(SearchParameters<FilterUser> searchParam, Tenant tenant) {
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterUser filterUser = searchParam.getFilter();
				predicates.add(criteriaBuilder.equal(root.get("tenant"), tenant));
				if (filterUser.getName() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("name")), Util.wrapSufix(filterUser.getName().toUpperCase(), searchParam.isExact())));
				}  
				if (filterUser.getUsername() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("username")), Util.wrapSufix(filterUser.getUsername().toUpperCase(), searchParam.isExact())));
				}  
				if (filterUser.getEmail() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("email")), Util.wrapSufix(filterUser.getEmail().toUpperCase(), searchParam.isExact())));
				}  
				if (filterUser.getPassword() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("password")), Util.wrapSufix(filterUser.getPassword().toUpperCase(), searchParam.isExact())));
				}  
				if (filterUser.getEnable() != null) {
					predicates.add(criteriaBuilder.equal(root.get("enable"), filterUser.getEnable()));
				}				
				if (filterUser.getImage() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("image")), Util.wrapSufix(filterUser.getImage().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
