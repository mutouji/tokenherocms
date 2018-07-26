package org.delphy.tokenherocms.pojo;

import lombok.Data;

/**
 * @author mutouji
 */
@Data
public class Book {
    private String name;
    private Integer count;
    private String author;
    private String publisher;

    public Book() {
    }

    public Book(String name, Integer count, String author, String publisher) {
        this.name = name;
        this.count = count;
        this.author = author;
        this.publisher = publisher;
    }
}
