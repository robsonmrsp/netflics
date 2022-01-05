/** generated: 4 de jan de 2022 23:12:56 **/
package com.robsonmrsp.netflics.integration;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.security.SpringSecurityUserContext;
import com.robsonmrsp.netflics.core.rs.exception.ValidationException;

import com.robsonmrsp.netflics.json.JsonPermission;
import com.robsonmrsp.netflics.model.Permission;
import com.robsonmrsp.netflics.rs.PermissionController;
import com.robsonmrsp.netflics.service.PermissionService;
import com.robsonmrsp.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(PermissionController.class)
public class PermissionErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PermissionService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingPermissionById() throws Exception {
		when(service.get(any(Integer.class))).thenThrow(new RuntimeException("Error Getting Permission"));
		this.mockMvc.perform(get("/rs/crud/permissions/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Permission")));
	}

	@Test
	public void errorGetitingAllPagerPermission() throws Exception {
		when(service.get(any(SearchParameters.class))).thenThrow(new RuntimeException("Error Getting Permission"));

		this.mockMvc.perform(get("/rs/crud/permissions").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Permission")));
	}

	@Test
	public void errorSavingPermission() throws Exception {
		when(service.save(any(Permission.class))).thenThrow(new RuntimeException("Error creating Permission"));

		this.mockMvc.perform(post("/rs/crud/permissions").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Permission")));
	}
	
	@Test
	public void errorSavingWithValidationPermission() throws Exception {
		when(service.save(any(Permission.class))).thenThrow(new ValidationException("Error creating-validating Permission"));

		this.mockMvc.perform(post("/rs/crud/permissions").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Permission")));
	}
	
	@Test
	public void errorUpdatingPermission() throws Exception {
		when(service.update(any(Permission.class))).thenThrow(new RuntimeException("Error updating Permission"));

		this.mockMvc.perform(put("/rs/crud/permissions/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Permission")));
	}
	
	@Test
	public void errorUpdatingWithValidationPermission() throws Exception {
		when(service.update(any(Permission.class))).thenThrow(new ValidationException("Error updating-validating Permission"));

		this.mockMvc.perform(put("/rs/crud/permissions/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Permission")));
	}

	@Test
	public void errorDeletingPermission() throws Exception {
		when(service.delete(any(Integer.class))).thenThrow(new RuntimeException("Error removing Permission"));
		this.mockMvc.perform(delete("/rs/crud/permissions/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Permission")));
	}

}