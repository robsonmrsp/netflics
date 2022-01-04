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

import com.robsonmrsp.netflics.json.JsonGenre;
import com.robsonmrsp.netflics.model.Genre;
import com.robsonmrsp.netflics.rs.GenreController;
import com.robsonmrsp.netflics.service.GenreService;
import com.robsonmrsp.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
public class GenreErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GenreService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingGenreById() throws Exception {
		when(service.get(any(Integer.class), any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Genre"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(get("/rs/crud/genres/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Genre")));
	}

	@Test
	public void errorGetitingAllPagerGenre() throws Exception {
		when(service.get(any(SearchParameters.class),any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Genre"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(get("/rs/crud/genres").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Genre")));
	}

	@Test
	public void errorSavingGenre() throws Exception {
		when(service.save(any(Genre.class))).thenThrow(new RuntimeException("Error creating Genre"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/genres").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Genre")));
	}
	
	@Test
	public void errorSavingWithValidationGenre() throws Exception {
		when(service.save(any(Genre.class))).thenThrow(new ValidationException("Error creating-validating Genre"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/genres").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Genre")));
	}
	
	@Test
	public void errorUpdatingGenre() throws Exception {
		when(service.update(any(Genre.class))).thenThrow(new RuntimeException("Error updating Genre"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/genres/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Genre")));
	}
	
	@Test
	public void errorUpdatingWithValidationGenre() throws Exception {
		when(service.update(any(Genre.class))).thenThrow(new ValidationException("Error updating-validating Genre"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/genres/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Genre")));
	}

	@Test
	public void errorDeletingGenre() throws Exception {
		when(service.delete(any(Integer.class),any(Tenant.class))).thenThrow(new RuntimeException("Error removing Genre"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(delete("/rs/crud/genres/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Genre")));
	}

}