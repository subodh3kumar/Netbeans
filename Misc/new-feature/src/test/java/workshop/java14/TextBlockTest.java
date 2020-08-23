package workshop.java14;

import org.junit.jupiter.api.Test;

public class TextBlockTest {

    @Test
    public void testHtmlTextBlock() {
        String html = """
                <html>
                    <body>
                        <p>Hello World !!!</p>
                    </body>
                </html>
                """;
        System.out.println(html);
    }
}
