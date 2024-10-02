package com.milan.catalog_service;

import com.milan.catalog_service.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostBookThenBookCreated(){
		var modelBook = new Book("0123456789", "Title", "Author", 7.56);
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(modelBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(returnedBook -> {
					assertThat(returnedBook.isbn()).isEqualTo(modelBook.isbn());
				});
	}

}
