package workshop;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @Inject
    Hello hello;

    @PostConstruct
    public void init() {
        System.out.println("##### init() method called #####");
        Thread.dumpStack();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return this.hello.getClass().getName() + " ----> " + hello.message();
    }
}
