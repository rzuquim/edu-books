package rz.jlox;

public class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int lineIdx;

    public Token(TokenType type, String lexeme, Object literal, int lineIdx) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.lineIdx = lineIdx;
    }

    public static Token maybeSimple(char c, int lineIdx) {
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

    public String toString() {
        if (literal == null) {
            return this.type + " " + this.lexeme + " @ " + this.lineIdx;
        }

        return this.type + " " + this.lexeme + " " + this.literal + " @ " + this.lineIdx;
    }
}
