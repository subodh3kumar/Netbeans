package workshop;

import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

public class GlobalClientHeaders implements ClientHeadersFactory {

    @Inject
    @ConfigProperty(name = "secret.value")
    private String secretValue;

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incoming, MultivaluedMap<String, String> outgoing) {
        System.out.println("GlobalClientHeaders - update() method called");

        System.out.println("GlobalClientHeaders - incoming headers");
        incoming.forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("GlobalClientHeaders - outgoing headers");
        outgoing.forEach((k, v) -> System.out.println(k + " : " + v));

        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        result.putAll(incoming);
        result.putAll(outgoing);
        result.add("X-Secret-Header", secretValue);
        result.add("X-Global-Header", "duke");
        result.add("X-Special-Header", "MicroProfile");

        System.out.println("GlobalClientHeaders - headers after merging");
        result.forEach((k, v) -> System.out.println(k + " : " + v));

        return result;
    }
}
