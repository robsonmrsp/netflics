/** generated: 3 de jan de 2022 23:41:20 **/
package com.robsonmrsp.netflics.integration.controller;

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

import com.robsonmrsp.netflics.core.model.Tenant;

import com.robsonmrsp.netflics.core.persistence.pagination.SearchParameters;
import com.robsonmrsp.netflics.core.security.SpringSecurityUserContext;
import com.robsonmrsp.netflics.core.rs.exception.ValidationException;

import com.robsonmrsp.netflics.json.JsonLanguage;
import com.robsonmrsp.netflics.model.Language;
import com.robsonmrsp.netflics.rs.LanguageController;
import com.robsonmrsp.netflics.service.LanguageService;
import com.robsonmrsp.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(LanguageController.class)
public class LanguageErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LanguageService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingLanguageById() throws Exception {
		when(service.get(any(Integer.class), any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Language"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(get("/rs/crud/languages/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Language")));
	}

	@Test
	public void errorGetitingAllPagerLanguage() throws Exception {
		when(service.get(any(SearchParameters.class),any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Language"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(get("/rs/crud/languages").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Language")));
	}

	@Test
	public void errorSavingLanguage() throws Exception {
		when(service.save(any(Language.class))).thenThrow(new RuntimeException("Error creating Language"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/languages").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Language")));
	}
	
	@Test
	public void errorSavingWithValidationLanguage() throws Exception {
		when(service.save(any(Language.class))).thenThrow(new ValidationException("Error creating-validating Language"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/languages").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Language")));
	}
	
	@Test
	public void errorUpdatingLanguage() throws Exception {
		when(service.update(any(Language.class))).thenThrow(new RuntimeException("Error updating Language"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/languages/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Language")));
	}
	
	@Test
	public void errorUpdatingWithValidationLanguage() throws Exception {
		when(service.update(any(Language.class))).thenThrow(new ValidationException("Error updating-validating Language"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/languages/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Language")));
	}

	@Test
	public void errorDeletingLanguage() throws Exception {
		when(service.delete(any(Integer.class),any(Tenant.class))).thenThrow(new RuntimeException("Error removing Language"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(delete("/rs/crud/languages/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Language")));
	}

}