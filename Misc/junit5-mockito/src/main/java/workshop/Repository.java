package workshop;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Subodh Kumar
 */
public class Repository {

    public List<String> getStuff() throws SQLException {
        return List.of("one", "two", "three");
    }
}
