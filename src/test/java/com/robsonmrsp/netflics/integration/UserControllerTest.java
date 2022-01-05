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
import com.robsonmrsp.netflics.model.User;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-User.sql")
public class UserControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/users";

	@BeforeAll
	public static void setUp() {
		FixtureUtils.init();
	}

	@BeforeEach
	public void before() {
	}

	@Test
	public void testAddUser() throws Exception {

		User user = Fixture.from(User.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<User> responseEntity = withBasicAuth.postForEntity(URL, user, User.class);

		HttpStatus status = responseEntity.getStatusCode();
		User resultUser = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultUser);
		assertNotNull("A not null gender identifier should be returned:", resultUser.getId());
	}

	@Test
	public void testGetUser() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<User> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", User.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		User resultUser = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultUser);
		assertEquals("A id gender == 1 must be returned: ", resultUser.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerUser() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<User> resultPagerUser = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerUser);
	}

	@Test
	public void testGetUserNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<User> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", User.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		User resultUser = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultUser);
	}

	@Test
	public void testGetUserByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<User>> responseEntity = withBasicAuth.exchange(URL + "?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<User>>() {}, "name user1");
		Pager<User> users = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		users.getItems().forEach(new Consumer<User>() {
			@Override
			public void accept(User user) {
				assertEquals("A not null User should be returned white the 'name' = 'name user1'", user.getName(), "name user1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of User should be returned ", users.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateUser() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		User user = Fixture.from(User.class).gimme("novo");
		user.setId(1);

		HttpEntity<User> requestEntity = new HttpEntity<User>(user);

		ResponseEntity<User> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, User.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteUser() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<User> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", User.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		User resultUser = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the user id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultUser);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
	
	@TestConfiguration
	static class MyTestConfig {

	}
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56