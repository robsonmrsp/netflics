package com.robsonmrsp.netflics.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.robsonmrsp.netflics.model.Item;
import com.robsonmrsp.netflics.model.filter.FilterItem;

import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.Util;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
public class ItemSpecificationHelper {

	public static Specification<Item> filter(SearchParameters<FilterItem> searchParam) {
		return new Specification<Item>() {

			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				FilterItem filterItem = searchParam.getFilter();
				if (filterItem.getName() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("name")), Util.wrapSufix(filterItem.getName().toUpperCase(), searchParam.isExact())));
				}  
				if (filterItem.getItemType() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("itemType")), Util.wrapSufix(filterItem.getItemType().toUpperCase(), searchParam.isExact())));
				}  
				if (filterItem.getIdentifier() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("identifier")), Util.wrapSufix(filterItem.getIdentifier().toUpperCase(), searchParam.isExact())));
				}  
				if (filterItem.getDescription() != null) {
					predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.<String>get("description")), Util.wrapSufix(filterItem.getDescription().toUpperCase(), searchParam.isExact())));
				}  
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
	}
}
