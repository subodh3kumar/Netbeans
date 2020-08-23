package workshop;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Provider;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class BasicConfiguration {

    @Inject
    private Config config;

    @Inject
    @ConfigProperty(name = "message")
    private String message;

    @Inject
    @ConfigProperty(name = "first.name", defaultValue = "Juli")
    private String firstName;

    @Inject
    @ConfigProperty(name = "last.name", defaultValue = "Kumar")
    private String lastName;

    @Inject
    @ConfigProperty(name = "city.name")
    private Optional<String> cityName;

    @Inject
    @ConfigProperty(name = "country.name")
    private Provider<String> countryName;

    @Inject
    @ConfigProperty(name = "user.list")
    private List<String> users;

    @Inject
    @ConfigProperty(name = "secret.token")
    private Token token;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object obj) {
        System.out.println("welcome message: " + config.getValue("welcome.message", String.class));
        System.out.println("first name: " + firstName);
        System.out.println("last name: " + lastName);
        System.out.println("city name: " + cityName.orElse("Medavakkam"));
        System.out.println("country name: " + countryName.get());
        System.out.println("users: " + users.stream().collect(Collectors.joining(", ")));
        System.out.println("token: " + token.toString());
        System.out.println("message [CustomConfigSourc]): " + message);
    }
}
