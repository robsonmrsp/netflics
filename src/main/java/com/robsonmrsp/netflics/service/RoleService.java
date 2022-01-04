/* generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20 */
package com.robsonmrsp.netflics.service;
import java.util.Optional;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.robsonmrsp.netflics.core.model.Tenant;
import com.robsonmrsp.netflics.model.Role;
import com.robsonmrsp.netflics.model.filter.FilterRole;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;

public interface RoleService {

	
	public Optional<Role> get(Integer id) ;

	public Pager<Role> get(SearchParameters<FilterRole> searchParams) ;
	
	public Boolean delete(Integer id);

	public Role save(Role entity) ;

	public Role update(Role entity) ;

}
//generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20