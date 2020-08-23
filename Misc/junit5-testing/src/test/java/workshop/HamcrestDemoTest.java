package workshop;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Subodh Kumar
 */
public class HamcrestDemoTest {

    @Test
    @DisplayName("string example")
    public void stringTest() {
        String s1 = "hello";
        String s2 = "hello";

        assertThat("comparing strings", s1, is(s2));
        assertThat(s1, equalTo(s2));
        assertThat(s1, sameInstance(s2));
        assertThat("ABCDE", containsString("BC"));
        assertThat("ABCDE", not(containsString("EF")));
    }

    @Test
    @DisplayName("list example")
    public void listTest() {
        List<String> list = new ArrayList<>();

        assertThat(list, isA(List.class));
        assertThat(list, empty());

        list.add("one");
        list.add("two");

        assertThat(list, not(empty()));
        assertThat(list, hasSize(2));
        assertThat(list, contains("one", "two"));
        assertThat(list, containsInAnyOrder("two", "one"));
        assertThat(list, hasItem("one"));
    }

    @Test
    @DisplayName("number example")
    public void numberTest() {
        assertThat(5, lessThan(10));
        assertThat(5, lessThanOrEqualTo(5));
        assertThat(5.01, closeTo(5.0, 0.01));
    }
}
