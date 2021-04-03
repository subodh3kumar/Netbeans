package workshop;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Subodh Kumar
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
@Entity
public class Student implements Serializable {

    @Id
    private int id;
    private String first_name;
    private String last_name;
}
