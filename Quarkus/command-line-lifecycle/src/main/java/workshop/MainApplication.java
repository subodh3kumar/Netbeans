package workshop;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

/**
 * @author Subodh Kumar
 */
@QuarkusMain
public class MainApplication {

    private static final Logger LOGGER = Logger.getLogger(MainApplication.class);

    public static void main(String[] args) {
        LOGGER.info("main() method called");
        Quarkus.run(MyApp.class, args);
    }
}
