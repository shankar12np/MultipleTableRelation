package com.example.bookstore.Service;

import com.example.bookstore.Entity.Author;
import com.example.bookstore.Entity.Book;
import com.example.bookstore.Entity.Genre;
import com.example.bookstore.Entity.Publisher;
import com.example.bookstore.Exceptions.BookNotFoundException;
import com.example.bookstore.Model.BookRequest;
import com.example.bookstore.Repository.AuthorRepo;
import com.example.bookstore.Repository.BookRepo;
import com.example.bookstore.Repository.GenreRepo;
import com.example.bookstore.Repository.PublisherRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private GenreRepo genreRepo;
    @Autowired
    private PublisherRepo publisherRepo;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Transactional
    public Book addBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());

        Publisher publisher = publisherRepo.findByName(bookRequest.getPublisherName());
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setName(bookRequest.getPublisherName());
            publisher = publisherRepo.save(publisher);
        }

        book.setPublisher(publisher);

        Set<Author> authors = new HashSet<>();
        for (String authorName : bookRequest.getAuthorNames()) {
            Author author = authorRepo.findByName(authorName);
            if (author == null) {
                author = new Author();
                author.setName(authorName);
                author = authorRepo.save(author);

            }
            authors.add(author);
        }
        book.setAuthors(authors);

        Set<Genre> genres = new HashSet<>();
        for (String genreName : bookRequest.getGenreNames()) {
            Genre genre = genreRepo.findByName(genreName);
            if (genre == null) {
                genre = new Genre();
                genre.setName(genreName);
                genre = genreRepo.save(genre);
            }
            genres.add(genre);
        }
        book.setGenres(genres);

        return bookRepo.save(book);
    }


    public List<Book> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        if (books.isEmpty()) {
            logger.warn("No Books found in the repository");
        } else {
            logger.info("Found {} Books", books.size());
        }
        return books;
    }

    public Book getBookByTitle(String title) {
        Book book = bookRepo.findByTitle(title);
        if (book == null) {
            throw new BookNotFoundException("Book with title " + title + " not found");
        }
        return book;
    }

    public Optional<Book> getBookById(Long id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isEmpty()) {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
        return book;
    }

    public List<Book> getBookByAuthor(String authorName) {
      List <Book> book = bookRepo.findByAuthorName(authorName);
       if (book.isEmpty()) {
           throw new BookNotFoundException("Book with author " + authorName + " not found");
       }
       return book;
    }

    public void deleteBookById(Long id) {
      if (!bookRepo.existsById(id)) {
          throw new BookNotFoundException("Book with id " + id + " not found");
      }
      bookRepo.deleteById(id);
    }

    @Transactional
    public Book updateBook(Long id, BookRequest bookRequest) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isEmpty()) {
            logger.error("Book with id {} not found", id);
            throw new BookNotFoundException("Book with id " + id + " not found");
        }

        Book book = optionalBook.get();
        book.setTitle(bookRequest.getTitle());

        Publisher publisher = publisherRepo.findByName(bookRequest.getPublisherName());
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setName(bookRequest.getPublisherName());
            publisher = publisherRepo.save(publisher);
        }
        book.setPublisher(publisher);

        Set<Author> authors = new HashSet<>();
        for (String authorName : bookRequest.getAuthorNames()) {
            Author author = authorRepo.findByName(authorName);
            if (author == null) {
                author = new Author();
                author.setName(authorName);
                author = authorRepo.save(author);
            }
            authors.add(author);
        }
        book.setAuthors(authors);

        Set<Genre> genres = new HashSet<>();
        for (String genreName : bookRequest.getGenreNames()) {
            Genre genre = genreRepo.findByName(genreName);
            if (genre == null) {
                genre = new Genre();
                genre.setName(genreName);
                genre = genreRepo.save(genre);
            }
            genres.add(genre);
        }
        book.setGenres(genres);

        return bookRepo.save(book);
    }

}
