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
import com.robsonmrsp.netflics.model.Permission;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Permission.sql")
public class PermissionControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/permissions";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddPermission() throws Exception {

		Permission permission = Fixture.from(Permission.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Permission> responseEntity = withBasicAuth.postForEntity(URL, permission, Permission.class);

		HttpStatus status = responseEntity.getStatusCode();
		Permission resultPermission = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultPermission);
		assertNotNull("A not null gender identifier should be returned:", resultPermission.getId());
	}

	@Test
	public void testGetPermission() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Permission> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Permission.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Permission resultPermission = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPermission);
		assertEquals("A id gender == 1 must be returned: ", resultPermission.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerPermission() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Permission> resultPagerPermission = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerPermission);
	}

	@Test
	public void testGetPermissionNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Permission> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Permission.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Permission resultPermission = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultPermission);
	}

	@Test
	public void testGetPermissionByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Permission>> responseEntity = withBasicAuth.exchange(URL + "?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Permission>>() {}, "name permission1");
		Pager<Permission> permissions = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		permissions.getItems().forEach(new Consumer<Permission>() {
			@Override
			public void accept(Permission permission) {
				assertEquals("A not null Permission should be returned white the 'name' = 'name permission1'", permission.getName(), "name permission1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Permission should be returned ", permissions.getItems().size() > 0);
	}
	
	@Test
	public void testUpdatePermission() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Permission permission = Fixture.from(Permission.class).gimme("novo");
		permission.setId(1);

		HttpEntity<Permission> requestEntity = new HttpEntity<Permission>(permission);

		ResponseEntity<Permission> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Permission.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeletePermission() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Permission> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Permission.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Permission resultPermission = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the permission id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultPermission);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20