package com.example.bookstore.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @ManyToMany(mappedBy = "authors")
//    private Set<Book> books = new HashSet<>();

    //when using JPA (Java Persistence API) with Lombok,
    // it’s important to be aware of potential issues with the @Data annotation.
    // The @Data annotation can cause problems with bidirectional relationships
    // because of the automatically generated hashCode() and toString() methods,
    // which can lead to infinite recursion and StackOverflow errors.
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
