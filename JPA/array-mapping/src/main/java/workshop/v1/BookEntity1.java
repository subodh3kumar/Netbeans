package workshop.v1;

import java.io.Serializable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author Subodh Kumar
 */
@Data
@Entity
@Table(name = "book_entity1")
public class BookEntity1 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @ElementCollection
    @OrderColumn(name = "pos")
    private String[] topics;
}
