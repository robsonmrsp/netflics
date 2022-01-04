package com.robsonmrsp.netflics.core.security;

import java.util.List;

import com.robsonmrsp.netflics.model.Item;
import com.robsonmrsp.netflics.model.Permission;
import com.robsonmrsp.netflics.model.Role;
import com.robsonmrsp.netflics.model.User;

/**
*  generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20
**/

public interface AuthorizationService {


	public Boolean authorizeRestServiceAccess(String method, String requestURI);

	public List<Permission> getAllPermissions();

	public Boolean authorizeWebComponentsAccess(final String type, final String identifier);

	
}
