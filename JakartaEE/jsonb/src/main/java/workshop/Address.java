package workshop;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author Subodh Kumar
 */
public class Address {

    @JsonbProperty(value = "home_city")
    public String city;
    public String state;

    public Address() {
    }

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" + "city=" + city + ", state=" + state + '}';
    }
}
