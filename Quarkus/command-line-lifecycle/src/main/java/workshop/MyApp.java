package workshop;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import org.jboss.logging.Logger;

/**
 * @author Subodh Kumar
 */
public class MyApp implements QuarkusApplication {

    private static final Logger LOGGER = Logger.getLogger(MyApp.class);

    @Override
    public int run(String... args) throws Exception {
        LOGGER.info("run() method called");
        Quarkus.waitForExit();
        return 10;
    }
}
