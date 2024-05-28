package com.example.bookstore.Repository;

import com.example.bookstore.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    public Publisher findByName(String name);
}
