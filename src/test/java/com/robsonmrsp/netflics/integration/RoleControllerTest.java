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
import com.robsonmrsp.netflics.model.Role;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Role.sql")
public class RoleControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/roles";

	@BeforeAll
	public static void setUp() {
		FixtureUtils.init();
	}

	@BeforeEach
	public void before() {
	}

	@Test
	public void testAddRole() throws Exception {

		Role role = Fixture.from(Role.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Role> responseEntity = withBasicAuth.postForEntity(URL, role, Role.class);

		HttpStatus status = responseEntity.getStatusCode();
		Role resultRole = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultRole);
		assertNotNull("A not null gender identifier should be returned:", resultRole.getId());
	}

	@Test
	public void testGetRole() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Role> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Role.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Role resultRole = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultRole);
		assertEquals("A id gender == 1 must be returned: ", resultRole.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerRole() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Role> resultPagerRole = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerRole);
	}

	@Test
	public void testGetRoleNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Role> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Role.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Role resultRole = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultRole);
	}

	@Test
	public void testGetRoleByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Role>> responseEntity = withBasicAuth.exchange(URL + "?authority={authority}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Role>>() {}, "authority role1");
		Pager<Role> roles = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		roles.getItems().forEach(new Consumer<Role>() {
			@Override
			public void accept(Role role) {
				assertEquals("A not null Role should be returned white the 'name' = 'authority role1'", role.getAuthority(), "authority role1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Role should be returned ", roles.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateRole() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Role role = Fixture.from(Role.class).gimme("novo");
		role.setId(1);

		HttpEntity<Role> requestEntity = new HttpEntity<Role>(role);

		ResponseEntity<Role> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Role.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteRole() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Role> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Role.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Role resultRole = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the role id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultRole);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
	
	@TestConfiguration
	static class MyTestConfig {

	}
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56