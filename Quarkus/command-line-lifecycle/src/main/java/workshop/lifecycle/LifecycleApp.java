package workshop.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.jboss.logging.Logger;

/**
 * @author Subodh Kumar
 */
@ApplicationScoped
public class LifecycleApp {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    public void onStart(@Observes StartupEvent event) {
        LOGGER.info("The application is starting...");
    }

    public void onStop(@Observes ShutdownEvent event) {
        LOGGER.info("The application is stopping...");
    }
}
