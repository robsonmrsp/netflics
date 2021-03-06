/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
package com.robsonmrsp.netflics.service;
import java.util.Optional;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.robsonmrsp.netflics.core.model.Tenant;
import com.robsonmrsp.netflics.model.Item;
import com.robsonmrsp.netflics.model.filter.FilterItem;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;

public interface ItemService {

	
	public Optional<Item> get(Integer id) ;

	public Pager<Item> get(SearchParameters<FilterItem> searchParams) ;
	
	public Boolean delete(Integer id);

	public Item save(Item entity) ;

	public Item update(Item entity) ;

}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56