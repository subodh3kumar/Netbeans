package workshop;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class Hello {

    @PostConstruct
    public void init() {
        System.out.println("init() method called.");
        Thread.dumpStack();
    }

    public String message() {
        Thread.dumpStack();
        return "Hello, Subodh!!!";
    }
}
