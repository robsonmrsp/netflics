/** generated: 4 de jan de 2022 23:12:56 **/
package com.robsonmrsp.netflics.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.model.Genre;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Genre.sql")
public class GenreControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/genres";

	@BeforeAll
	public static void setUp() {
		FixtureUtils.init();
	}

	@BeforeEach
	public void before() {
	}

	@Test
	public void testAddGenre() throws Exception {

		Genre genre = Fixture.from(Genre.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Genre> responseEntity = withBasicAuth.postForEntity(URL, genre, Genre.class);

		HttpStatus status = responseEntity.getStatusCode();
		Genre resultGenre = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultGenre);
		assertNotNull("A not null gender identifier should be returned:", resultGenre.getId());
	}

	@Test
	public void testGetGenre() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Genre> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Genre.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Genre resultGenre = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultGenre);
		assertEquals("A id gender == 1 must be returned: ", resultGenre.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerGenre() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Genre> resultPagerGenre = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerGenre);
	}

	@Test
	public void testGetGenreNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Genre> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Genre.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Genre resultGenre = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultGenre);
	}

	@Test
	public void testGetGenreByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Genre>> responseEntity = withBasicAuth.exchange(URL + "?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Genre>>() {}, "name genre1");
		Pager<Genre> genres = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		genres.getItems().forEach(new Consumer<Genre>() {
			@Override
			public void accept(Genre genre) {
				assertEquals("A not null Genre should be returned white the 'name' = 'name genre1'", genre.getName(), "name genre1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Genre should be returned ", genres.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateGenre() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Genre genre = Fixture.from(Genre.class).gimme("novo");
		genre.setId(1);

		HttpEntity<Genre> requestEntity = new HttpEntity<Genre>(genre);

		ResponseEntity<Genre> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Genre.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteGenre() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Genre> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Genre.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Genre resultGenre = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the genre id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultGenre);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
	
	@TestConfiguration
	static class MyTestConfig {

	}
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56