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
import com.robsonmrsp.netflics.model.Movie;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Movie.sql")
public class MovieControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/movies";

	@BeforeAll
	public static void setUp() {
		FixtureUtils.init();
	}

	@BeforeEach
	public void before() {
	}

	@Test
	public void testAddMovie() throws Exception {

		Movie movie = Fixture.from(Movie.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Movie> responseEntity = withBasicAuth.postForEntity(URL, movie, Movie.class);

		HttpStatus status = responseEntity.getStatusCode();
		Movie resultMovie = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultMovie);
		assertNotNull("A not null gender identifier should be returned:", resultMovie.getId());
	}

	@Test
	public void testGetMovie() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Movie> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Movie.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Movie resultMovie = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultMovie);
		assertEquals("A id gender == 1 must be returned: ", resultMovie.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerMovie() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Movie> resultPagerMovie = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerMovie);
	}

	@Test
	public void testGetMovieNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Movie> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Movie.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Movie resultMovie = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultMovie);
	}

	@Test
	public void testGetMovieByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Movie>> responseEntity = withBasicAuth.exchange(URL + "?sinopsis={sinopsis}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Movie>>() {}, "sinopsis movie1");
		Pager<Movie> movies = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		movies.getItems().forEach(new Consumer<Movie>() {
			@Override
			public void accept(Movie movie) {
				assertEquals("A not null Movie should be returned white the 'name' = 'sinopsis movie1'", movie.getSinopsis(), "sinopsis movie1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Movie should be returned ", movies.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateMovie() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Movie movie = Fixture.from(Movie.class).gimme("novo");
		movie.setId(1);

		HttpEntity<Movie> requestEntity = new HttpEntity<Movie>(movie);

		ResponseEntity<Movie> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Movie.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteMovie() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Movie> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Movie.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Movie resultMovie = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the movie id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultMovie);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
	
	@TestConfiguration
	static class MyTestConfig {

	}
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56