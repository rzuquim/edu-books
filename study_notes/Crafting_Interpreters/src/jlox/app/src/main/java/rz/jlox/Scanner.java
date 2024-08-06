package rz.jlox;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private ErrorReporter error;

    public Scanner(ErrorReporter error) {
        this.error = error;
    }

    public List<Token> scanTokens(String fileContent) {
        var tokens = new ArrayList<Token>();

        var currCharIdx = 0;
        var currLine = 1;
        var length = fileContent.length();

        while (currCharIdx < length) {
            var c = fileContent.charAt(currCharIdx);
            currCharIdx++;

            var simpleToken = Token.maybeSimple(c, currLine);
            if (simpleToken != null) {
                tokens.add(simpleToken);
                continue;
            }

            // NOTE: if reached this far could not parse token from char
            this.error.report(currLine, "Unexpected character %s", c);
        }

        tokens.add(new Token(TokenType.EOF, "", null, currLine));
        return tokens;
    }
}
