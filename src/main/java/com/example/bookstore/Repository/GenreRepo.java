package com.example.bookstore.Repository;

import com.example.bookstore.Entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo extends JpaRepository<Genre,Long> {
    public Genre findByName(String name);
}
