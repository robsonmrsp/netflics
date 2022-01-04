/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
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

import com.robsonmrsp.netflics.model.Item;
import com.robsonmrsp.netflics.persistence.ItemRepository;
import com.robsonmrsp.netflics.persistence.ItemSpecificationHelper;
import com.robsonmrsp.netflics.model.filter.FilterItem;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.rs.exception.ValidationException;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.utils.DateUtil;
import com.robsonmrsp.netflics.core.utils.Util;

@Service
@Transactional
public class ItemServiceImp implements ItemService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImp.class);
	
	@Autowired
	ItemRepository itemRepository;
	
	public Optional<Item> get(Integer id) {
		return itemRepository.findById(id);
	}

	public Pager<Item> get(SearchParameters<FilterItem> searchParams) {
		Pageable pageRequest = searchParams.getPageRequest();

		Page<Item> page = itemRepository.findAll(ItemSpecificationHelper.filter(searchParams), pageRequest);

		return new Pager<Item>(page.getContent(), searchParams.getPage(), searchParams.getPageSize(),
		        searchParams.getOrder(), searchParams.getOrderBy(), page.getTotalElements());
	}
	
	public Boolean delete(Integer id) {
		Optional<Item> optional = this.get(id);
		if (optional.isPresent()) {
			itemRepository.delete(optional.get());
		}
		return true;
	}

	public Item save(Item entity) {
		return itemRepository.save(entity);
	}

	public Item update(Item entity) {
		return itemRepository.save(entity);
	}
}

//generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20