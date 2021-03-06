/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
package com.robsonmrsp.netflics.service;
import java.util.Optional;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.robsonmrsp.netflics.core.model.Tenant;
import com.robsonmrsp.netflics.model.Group;
import com.robsonmrsp.netflics.model.filter.FilterGroup;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;

public interface GroupService {

	
	public Optional<Group> get(Integer id) ;

	public Pager<Group> get(SearchParameters<FilterGroup> searchParams) ;
	
	public Boolean delete(Integer id);

	public Group save(Group entity) ;

	public Group update(Group entity) ;

}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56