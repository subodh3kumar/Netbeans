package workshop.entity;

import java.io.Serializable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "books")
public class BookEntity implements Serializable {

    @Id
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_author")
    private String author;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "price")
    private double price;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "language")
    private String language;

    @Column(name = "isbn")
    private String isbn;
}
