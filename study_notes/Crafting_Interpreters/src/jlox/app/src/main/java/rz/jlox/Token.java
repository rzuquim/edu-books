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

    public String toString() {
        if (literal == null) {
            return this.type + " " + this.lexeme + " @ " + this.lineIdx;
        }

        return this.type + " " + this.lexeme + " " + this.literal + " @ " + this.lineIdx;
    }
}
