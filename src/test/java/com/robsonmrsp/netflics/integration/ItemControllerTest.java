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
import com.robsonmrsp.netflics.model.Item;
import com.robsonmrsp.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Item.sql")
public class ItemControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/items";

	@BeforeAll
	public static void setUp() {
		FixtureUtils.init();
	}

	@BeforeEach
	public void before() {
	}

	@Test
	public void testAddItem() throws Exception {

		Item item = Fixture.from(Item.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Item> responseEntity = withBasicAuth.postForEntity(URL, item, Item.class);

		HttpStatus status = responseEntity.getStatusCode();
		Item resultItem = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultItem);
		assertNotNull("A not null gender identifier should be returned:", resultItem.getId());
	}

	@Test
	public void testGetItem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Item> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Item.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Item resultItem = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultItem);
		assertEquals("A id gender == 1 must be returned: ", resultItem.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerItem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Item> resultPagerItem = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerItem);
	}

	@Test
	public void testGetItemNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Item> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Item.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Item resultItem = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultItem);
	}

	@Test
	public void testGetItemByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Item>> responseEntity = withBasicAuth.exchange(URL + "?name={name}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Item>>() {}, "name item1");
		Pager<Item> items = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		items.getItems().forEach(new Consumer<Item>() {
			@Override
			public void accept(Item item) {
				assertEquals("A not null Item should be returned white the 'name' = 'name item1'", item.getName(), "name item1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Item should be returned ", items.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateItem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Item item = Fixture.from(Item.class).gimme("novo");
		item.setId(1);

		HttpEntity<Item> requestEntity = new HttpEntity<Item>(item);

		ResponseEntity<Item> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Item.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteItem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Item> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Item.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Item resultItem = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the item id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultItem);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
	
	@TestConfiguration
	static class MyTestConfig {

	}
}
//generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56