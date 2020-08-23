package workshop.model;

import lombok.Data;

@Data
public class Book {

    private int id;
    private String name;
    private String author;
    private int publicationYear;
    private double price;
    private String publisher;
    private String language;
    private String isbn;

}
