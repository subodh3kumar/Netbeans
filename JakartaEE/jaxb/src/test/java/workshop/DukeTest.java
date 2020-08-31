package workshop;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Subodh Kumar
 */
public class DukeTest {

    private JAXBContext context;

    @BeforeEach
    public void init() throws JAXBException {
        this.context = JAXBContext.newInstance(Duke.class);
    }

    @Test
    public void serialization() throws JAXBException {
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new Duke("Java", 25), new File("duke.xml"));
        
        Unmarshaller unmarshaller = this.context.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(new File("duke.xml"));
        System.out.println("unmarshalled: " + unmarshalled.toString());
    }
}
