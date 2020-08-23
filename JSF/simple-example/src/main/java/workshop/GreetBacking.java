package workshop;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class GreetBacking {

    public String greet() {
        return "Hello, Welocme to Jakarta Faces !!!";
    }
}
