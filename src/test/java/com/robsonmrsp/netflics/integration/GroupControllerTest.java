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
import com.robsonmrsp.netflics.model.Group;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Group.sql")
public class GroupControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/groups";

	@BeforeAll
	public static void setUp() {
		FixtureUtils.init();
	}

	@BeforeEach
	public void before() {
	}

	@Test
	public void testAddGroup() throws Exception {

		Group group = Fixture.from(Group.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Group> responseEntity = withBasicAuth.postForEntity(URL, group, Group.class);

		HttpStatus status = responseEntity.getStatusCode();
		Group resultGroup = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultGroup);
		assertNotNull("A not null gender identifier should be returned:", resultGroup.getId());
	}

	@Test
	public void testGetGroup() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Group> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Group.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Group resultGroup = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultGroup);
		assertEquals("A id gender == 1 must be returned: ", resultGroup.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerGroup() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Group> resultPagerGroup = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerGroup);
	}

	@Test
	public void testGetGroupNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Group> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Group.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Group resultGroup = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultGroup);
	}

	@Test
	public void testGetGroupByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Group>> responseEntity = withBasicAuth.exchange(URL + "?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Group>>() {}, "name group1");
		Pager<Group> groups = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		groups.getItems().forEach(new Consumer<Group>() {
			@Override
			public void accept(Group group) {
				assertEquals("A not null Group should be returned white the 'name' = 'name group1'", group.getName(), "name group1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Group should be returned ", groups.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateGroup() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Group group = Fixture.from(Group.class).gimme("novo");
		group.setId(1);

		HttpEntity<Group> requestEntity = new HttpEntity<Group>(group);

		ResponseEntity<Group> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Group.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteGroup() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Group> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Group.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Group resultGroup = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the group id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultGroup);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
	
	@TestConfiguration
	static class MyTestConfig {

	}
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56