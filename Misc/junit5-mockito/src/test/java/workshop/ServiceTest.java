package workshop;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Subodh Kumar
 */
@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private Repository repository;

    @InjectMocks
    private Service service;

    @Test
    public void testSuccess() {
        List<String> list = Arrays.asList("one", "two", "hundred", "seven", "four");
        try {
            Mockito.when(repository.getStuff()).thenReturn(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        List<String> stuff = service.getStuffWithLengthLessThanFive();
        Assertions.assertNotNull(stuff);
        Assertions.assertEquals(3, stuff.size());
    }

    @Test
    public void testException() {
        try {
            Mockito.when(repository.getStuff())
                    .thenThrow(new SQLException("connection exception"));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String> stuff = service.getStuffWithLengthLessThanFive();
        Assertions.assertNotNull(stuff);
        Assertions.assertEquals(0, stuff.size());
    }
}
