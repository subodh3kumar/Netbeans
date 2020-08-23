package workshop;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address implements Serializable {

    @Id
    @Column(name = "address_id", updatable = false, nullable = false)
    private String id;

    @Column
    private String locality;

    @Column
    private String city;

    public Address() {
    }

    public Address(String id, String locality, String city) {
        this.id = id;
        this.locality = locality;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", locality=" + locality + ", city=" + city + '}';
    }
}
