package workshop.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Subodh Kumar
 */
@Entity
public class Movie extends PanacheEntity {

    @Column(length = 100)
    public String title;

    @Column(length = 500)
    public String description;

    public String director;

    public String country;
}
