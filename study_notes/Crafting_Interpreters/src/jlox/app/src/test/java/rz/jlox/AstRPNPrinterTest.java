package rz.jlox;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AstRPNPrinterTest {
    @Test
    void can_print_simple_rpn_expr() {
        var printer = new AstRPNPrinter();

        var expression = new Expr.Binary(
                new Expr.Literal(1),
                new Token(TokenType.MINUS, "-", null, 0),
                new Expr.Binary(
                        new Expr.Literal(2),
                        new Token(TokenType.STAR, "*", null, 0),
                        new Expr.Literal(3)));
        assertEquals(expression.accept(printer), "1 2 3 * -");

        expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 0),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 0),
                new Expr.Grouping(
                        new Expr.Literal(45.67)));
        assertEquals(expression.accept(printer), "123 - 45.67 group *");
    }
}
