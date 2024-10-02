package com.milan.catalog_service.web;

import com.milan.catalog_service.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    public void serializationTest() throws Exception {
        var objectBook = new Book("0123456789", "Title", "Author", 9.24);
        var jsonBook = json.write(objectBook);
        assertThat(jsonBook).extractingJsonPathStringValue("@.isbn").isEqualTo(objectBook.isbn());
        assertThat(jsonBook).extractingJsonPathStringValue("@.title").isEqualTo(objectBook.title());
        assertThat(jsonBook).extractingJsonPathStringValue("@.author").isEqualTo(objectBook.author());
        assertThat(jsonBook).extractingJsonPathNumberValue("@.price").isEqualTo(objectBook.price());
    }

    @Test
    public void deserializeTest() throws Exception {
        var jsonContent = """
                {
                    "isbn": "0123456789",
                    "title": "Title",
                    "author": "Author",
                    "price": 9.25
                }
                """;
        assertThat(json.parse(jsonContent))
                .usingRecursiveComparison()
                .isEqualTo(new Book("0123456789", "Title", "Author", 9.25));
    }
}
