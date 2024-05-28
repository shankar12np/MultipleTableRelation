package com.example.bookstore.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
