package workshop;

import org.eclipse.microprofile.config.spi.Converter;

public class TokenConverter implements Converter<Token> {

    @Override
    public Token convert(String value) {
        String[] chunks = value.split(",");
        return new Token(chunks[0], chunks[1]);
    }
}
