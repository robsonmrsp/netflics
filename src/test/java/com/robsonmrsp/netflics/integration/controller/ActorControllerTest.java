/** generated: 3 de jan de 2022 23:41:20 **/
package com.robsonmrsp.netflics.integration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.robsonmrsp.netflics.core.persistence.pagination.Pager;
import com.robsonmrsp.netflics.model.Actor;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Actor.sql")
public class ActorControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/actors";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddActor() throws Exception {

		Actor actor = Fixture.from(Actor.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Actor> responseEntity = withBasicAuth.postForEntity(URL, actor, Actor.class);

		HttpStatus status = responseEntity.getStatusCode();
		Actor resultActor = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultActor);
		assertNotNull("A not null gender identifier should be returned:", resultActor.getId());
	}

	@Test
	public void testGetActor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Actor> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Actor.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Actor resultActor = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultActor);
		assertEquals("A id gender == 1 must be returned: ", resultActor.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerActor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Actor> resultPagerActor = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerActor);
	}

	@Test
	public void testGetActorNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Actor> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Actor.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Actor resultActor = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultActor);
	}

	@Test
	public void testGetActorByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Actor>> responseEntity = withBasicAuth.exchange(URL + "?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Actor>>() {}, "name actor1");
		Pager<Actor> actors = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		actors.getItems().forEach(new Consumer<Actor>() {
			@Override
			public void accept(Actor actor) {
				assertEquals("A not null Actor should be returned white the 'name' = 'name actor1'", actor.getName(), "name actor1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Actor should be returned ", actors.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateActor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Actor actor = Fixture.from(Actor.class).gimme("novo");
		actor.setId(1);

		HttpEntity<Actor> requestEntity = new HttpEntity<Actor>(actor);

		ResponseEntity<Actor> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Actor.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteActor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Actor> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Actor.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Actor resultActor = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the actor id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultActor);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20