package com.example.bookstore.Model;

import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private String title;
    private String publisherName;
    private List<String> authorNames;
    private List<String> genreNames;

}
