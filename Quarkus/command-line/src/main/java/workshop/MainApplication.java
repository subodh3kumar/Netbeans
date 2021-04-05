package workshop;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

/**
 * @author Subodh Kumar
 */
@QuarkusMain
public class MainApplication implements QuarkusApplication{

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello World !!!");
        return 10;
    }
}
