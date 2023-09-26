package com.dbd23.demo1.library;

import com.dbd23.demo1.library.model.Author;
import com.dbd23.demo1.library.model.Book;
import com.dbd23.demo1.library.model.PhysicalBook;
import com.dbd23.demo1.library.repositories.AuthorRepository;
import com.dbd23.demo1.library.services.AuthorService;
import com.dbd23.demo1.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback(false)
class LibraryApplicationTests {

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookService bookService;

	@Test
	void contextLoads() {
	}

	@Test
	void createAndGetAuthorTest() throws LibraryException {
		Author newAuthor = this.authorService.createAuthor("Julio Cortázar", LocalDate.of(1914, 8, 26));
		assertNotNull(newAuthor);
		Optional<Author> authorOptional = this.authorService.getById(newAuthor.getId());
		assertTrue(authorOptional.isPresent());
		Author author = authorOptional.get();
		assertEquals(newAuthor.getId(), author.getId());
		assertEquals("Julio Cortázar", author.getFullname());
	}

	@Test
	void createAndGetBooksTest() throws LibraryException {
		Author author = this.authorService.createAuthor("Julio Cortázar", LocalDate.of(1914, 8, 26));
		PhysicalBook newPhysicalBook = this.bookService.createPhysicalBook("0000111122223", "Rayuela", 1963, "Comentario", 200, "Seccion B", author);
		Optional<Book> optionalBook = this.bookService.findByIsbn("0000111122223");
		assertTrue(optionalBook.isPresent());
		Book book = optionalBook.get();
		assertEquals(book.getTitle(), "Rayuela");
		assertEquals(book.getAuthor().getFullname(), "Julio Cortázar");

		// Para que la siguiente comprobación funcione se debe hacer especificar la clase como Rollback(true)
		// assertThrows(LibraryException.class, () -> this.bookService.createPhysicalBook("0000111122223", "Casa Tomada", 1963, "Comentario", 200, "Seccion C", author), "ConstraintViolation");

	}

	@Test
	void updateAuthor() throws LibraryException {
		Author author = this.authorService.createAuthor("Julioo Cortázar", LocalDate.of(1914, 8, 26));
		this.authorService.updateAuthor(author.getId(), "Julio Cortázar", author.getDateOfBirth());
		Author updatedAuthor = this.authorService.getById(author.getId()).orElse(null);
		assertEquals("Julio Cortázar", updatedAuthor.getFullname());
	}

	@Test
	void deleteAuthor() throws LibraryException {
		Author author1 = this.authorService.createAuthor("Julio Cortázar", LocalDate.of(1914, 8, 26));
		Author author2 = this.authorService.createAuthor("Jorge Luis Borges", LocalDate.of(1899, 8, 24));
		List<Author> authorList = this.authorService.getListOfAuthors();
		assertEquals(2, authorList.size());
		assertTrue(this.authorService.deleteAuthor(author2.getId()));
		List<Author> authorList2 = this.authorService.getListOfAuthors();
		assertEquals(1, authorList2.size());
	}

	@Test
	void getYoungAuthorList() throws LibraryException {
		this.authorService.createAuthor("Julio Cortázar", LocalDate.of(1914, 8, 26));
		this.authorService.createAuthor("J.K. Rowling", LocalDate.of(1965, 7, 31));
		this.authorService.createAuthor("John Doe", LocalDate.of(1980, 5, 15));
		this.authorService.createAuthor("Jane Smith", LocalDate.of(1950, 8, 22));
		this.authorService.createAuthor("Michael Johnson", LocalDate.of(1982, 3, 10));
		List<Author> authorList = this.authorService.getYoungAuthors();
		assertEquals(3, authorList.size());
	}

	@Test
	void getAuthorWithMoreBook() throws LibraryException{
		Author author1 = this.authorService.createAuthor("Julio Cortázar", LocalDate.of(1914, 8, 26));
		Author author2 = this.authorService.createAuthor("Jorge Luis Borges", LocalDate.of(1899, 8, 24));
		bookService.createDigitalBook("0000111122223", "Book Title 1", 2022, "Description 1", "PDF", 10.5f, author1);
		bookService.createDigitalBook("ISBN2", "Book Title 2", 2021, "Description 2", "EPUB", 8.2f, author2);
		bookService.createDigitalBook("ISBN3", "Book Title 3", 2023, "Description 3", "MOBI", 12.0f, author2);
		bookService.createPhysicalBook("ISBN4", "Book Title 4", 2022, "Description 11", 1.2f, "Seccion A", author1);
		bookService.createPhysicalBook("ISBN5", "Book Title 5", 2021, "Description 12", 1.5f, "Seccion B", author1);
		Author author = this.authorService.getAuthorWithMoreBooks();
		assertEquals(author.getId(), author1.getId());
		assertEquals(author.getFullname(), "Julio Cortázar");
		assertEquals(author.getBooks().size(), 3);
	}

	@Test
	void testGetAllBookofAuthor() throws LibraryException{
		Author author1 = this.authorService.createAuthor("Julio Cortázar", LocalDate.of(1914, 8, 26));
		Author author2 = this.authorService.createAuthor("Jorge Luis Borges", LocalDate.of(1899, 8, 24));
		bookService.createPhysicalBook("ISBN11", "Book Title 1", 2022, "Description 11", 1.2f, "Seccion A", author1);
		bookService.createPhysicalBook("ISBN12", "Book Title 2", 2021, "Description 12", 1.5f, "Seccion B", author1);
		bookService.createDigitalBook("ISBN1", "Book Title 1", 2022, "Description 1", "PDF", 10.5f, author1);
		bookService.createDigitalBook("ISBN2", "Book Title 2", 2021, "Description 2", "EPUB", 8.2f, author2);
		bookService.createDigitalBook("ISBN3", "Book Title 3", 2023, "Description 3", "MOBI", 12.0f, author2);

		List<Book> bookList = this.bookService.findByAuthor(author2);
		assertEquals(bookList.size(), 2);
	}
}
