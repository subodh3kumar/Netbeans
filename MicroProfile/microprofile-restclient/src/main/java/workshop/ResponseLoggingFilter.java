package workshop;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class ResponseLoggingFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        System.out.println("ResponseLoggingFilter - filter() method called");
        System.out.println("ResponseLoggingFilter - status code is: " + responseContext.getStatus());
    }
}
