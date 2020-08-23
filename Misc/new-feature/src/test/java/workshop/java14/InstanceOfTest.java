package workshop.java14;
 
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Subodh Kumar
 */
public class InstanceOfTest {

    @Test
    public void testPatternMatching() {
        InstanceOf obj = new InstanceOf();
        Assertions.assertEquals("valid name", obj.patternMatching("subodh"));
        Assertions.assertEquals("not valid name", obj.patternMatching("juli"));
    }
    
}