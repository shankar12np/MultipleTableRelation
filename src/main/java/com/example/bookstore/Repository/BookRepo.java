package com.example.bookstore.Repository;

import com.example.bookstore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
   Book findByTitle(String title);
@Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :name")
 List<Book> findByAuthorName(@Param("name") String author);
}
