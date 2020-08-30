package workshop.v3;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Type;

/**
 * @author Subodh Kumar
 */
@Data
@Entity
@Table(name = "book_entity3")
public class BookEntity3 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @Column(columnDefinition = "text[]")
    @Type(type = "workshop.v3.PostgreSQLArrayType")
    private String[] topics;
}
