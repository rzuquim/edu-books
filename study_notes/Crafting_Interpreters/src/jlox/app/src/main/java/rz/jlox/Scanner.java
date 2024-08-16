package rz.jlox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {
    private ErrorReporter error;

    public Scanner(ErrorReporter error) {
        this.error = error;
    }

    public List<Token> scanTokens(String text) {
        var tokens = new ArrayList<Token>();

        var currCharIdx = 0;
        var lineIdx = 1;
        var length = text.length();

        while (currCharIdx < length) {
            var c = text.charAt(currCharIdx);

            // NOTE: spaces and line breaks
            switch (c) {
                case ' ':
                case '\r':
                case '\t':
                    currCharIdx++;
                    continue;
                case '\n':
                    currCharIdx++;
                    lineIdx++;
                    continue;
            }

            var token = maybeSingleCharToken(c, lineIdx);
            if (token != null) {
                tokens.add(token);
                currCharIdx += token.lexeme.length();
                continue;
            }

            var c1 = peek(currCharIdx + 1, text);
            token = maybeDoubleCharToken(c, c1, text, lineIdx);
            if (token != null) {
                tokens.add(token);
                currCharIdx += token.lexeme.length();
                continue;
            }

            token = maybeCommentOrSingleSlash(c, c1, text, currCharIdx, lineIdx);
            if (token != null) {
                tokens.add(token);
                currCharIdx += token.lexeme.length();
                continue;
            }

            token = maybeStringLiteral(c, text, currCharIdx, lineIdx);
            if (token != null) {
                tokens.add(token);
                currCharIdx += token.lexeme.length();
                continue;
            }

            token = maybeNumberLiteral(c, text, currCharIdx, lineIdx);
            if (token != null) {
                tokens.add(token);
                currCharIdx += token.lexeme.length();
                continue;
            }

            token = maybeIdentifier(c, text, currCharIdx, lineIdx);
            if (token != null) {
                tokens.add(token);
                currCharIdx += token.lexeme.length();
                continue;
            }

            // NOTE: if reached this far could not parse token from char
            this.error.report(lineIdx, "Unexpected character %s", c);
            currCharIdx++;
        }

        tokens.add(new Token(TokenType.EOF, "", null, lineIdx));
        return tokens;
    }

    private Token maybeSingleCharToken(char c, int lineIdx) {
        switch (c) {
            case '(':
                return new Token(TokenType.LEFT_PAREN, "(", null, lineIdx);
            case ')':
                return new Token(TokenType.RIGHT_PAREN, ")", null, lineIdx);
            case '{':
                return new Token(TokenType.LEFT_BRACE, "{", null, lineIdx);
            case '}':
                return new Token(TokenType.RIGHT_BRACE, "}", null, lineIdx);
            case ',':
                return new Token(TokenType.COMMA, ",", null, lineIdx);
            case '.':
                return new Token(TokenType.DOT, ".", null, lineIdx);
            case '-':
                return new Token(TokenType.MINUS, "-", null, lineIdx);
            case '+':
                return new Token(TokenType.PLUS, "+", null, lineIdx);
            case ';':
                return new Token(TokenType.SEMICOLON, ";", null, lineIdx);
            case '*':
                return new Token(TokenType.STAR, "*", null, lineIdx);
        }
        return null;
    }

    private Token maybeDoubleCharToken(char c0, char c1, String fileContent, int lineIdx) {
        switch (c0) {
            case '!':
                return c1 == '='
                        ? new Token(TokenType.BANG_EQUAL, "!=", null, lineIdx)
                        : new Token(TokenType.BANG, "!", null, lineIdx);
            case '=':
                return c1 == '='
                        ? new Token(TokenType.EQUAL_EQUAL, "==", null, lineIdx)
                        : new Token(TokenType.EQUAL, "=", null, lineIdx);
            case '<':
                return c1 == '='
                        ? new Token(TokenType.LESS_EQUAL, "<=", null, lineIdx)
                        : new Token(TokenType.LESS, "!=", null, lineIdx);
            case '>':
                return c1 == '='
                        ? new Token(TokenType.GREATER_EQUAL, ">=", null, lineIdx)
                        : new Token(TokenType.GREATER, "!=", null, lineIdx);
        }

        return null;
    }

    private Token maybeCommentOrSingleSlash(char c0, char c1, String text, int charIdx, int lineIdx) {
        if (c0 != '/') {
            return null;
        }

        if (c1 != '/') {
            return new Token(TokenType.SLASH, "/", null, lineIdx);
        }

        var commentContent = new StringBuilder();
        commentContent.append("//");

        var i = charIdx + 2; // NOTE: skipping both slashes
        var commentChar = peek(i, text);

        while (commentChar != '\n' && commentChar != '\0') {
            commentContent.append(commentChar);
            i++;
            commentChar = peek(i, text);
        }

        return new Token(TokenType.COMMENT, commentContent.toString(), null, lineIdx);
    }

    private Token maybeStringLiteral(char c, String text, int charIdx, int lineIdx) {
        if (c != '"') {
            return null;
        }

        var stringContent = new StringBuilder();
        stringContent.append("\"");

        var i = charIdx + 1;
        do {
            c = peek(i, text);
            stringContent.append(c);
            i++;
            // TODO: support multiline string
        } while (c != '"' && c != '\0');

        if (c != '"') {
            this.error.report(lineIdx, "Missing closing string literal quotes");
            return null;
        }

        var withQuotes = stringContent.toString();
        var withoutQuotes = withQuotes.substring(1, withQuotes.length() - 1);
        return new Token(TokenType.STRING, withQuotes, withoutQuotes, lineIdx);
    }

    private Token maybeNumberLiteral(char c, String text, int charIdx, int lineIdx) {
        if (!isDigit(c)) {
            return null;
        }

        var numberLiteral = new StringBuilder();
        var i = charIdx;
        while (isDigit(c) || (c == '.' && isDigit(peek(i + 1, text)))) {
            numberLiteral.append(c);
            i++;
            c = peek(i, text);
        }

        var number = numberLiteral.toString();
        return new Token(TokenType.NUMBER, number, Double.parseDouble(number), lineIdx);
    }

    private Token maybeIdentifier(char c, String text, int currCharIdx, int lineIdx) {
        // NOTE: first char on an identifier must be a letter (but it might contain
        // numbers later on)
        if (!isAlpha(c)) {
            return null;
        }

        var content = new StringBuilder();
        content.append(c);

        var i = currCharIdx;
        while (true) {
            i++;
            c = peek(i, text);
            if (!isAlpha(c) && !isDigit(c)) {
                break;
            }
            content.append(c);
        }
        var lexeme = content.toString();
        var keyword = keywords.get(lexeme);

        return keyword == null
                ? new Token(TokenType.IDENTIFIER, lexeme, null, lineIdx)
                : new Token(keyword, lexeme, null, lineIdx);
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_');
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private char peek(int i, String text) {
        if (i >= text.length()) {
            return '\0';
        }
        return text.charAt(i);
    }

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("and", TokenType.AND);
        keywords.put("class", TokenType.CLASS);
        keywords.put("else", TokenType.ELSE);
        keywords.put("false", TokenType.FALSE);
        keywords.put("for", TokenType.FOR);
        keywords.put("fun", TokenType.FUN);
        keywords.put("if", TokenType.IF);
        keywords.put("nil", TokenType.NIL);
        keywords.put("or", TokenType.OR);
        keywords.put("print", TokenType.PRINT);
        keywords.put("return", TokenType.RETURN);
        keywords.put("super", TokenType.SUPER);
        keywords.put("this", TokenType.THIS);
        keywords.put("true", TokenType.TRUE);
        keywords.put("var", TokenType.VAR);
        keywords.put("while", TokenType.WHILE);
    }
}
