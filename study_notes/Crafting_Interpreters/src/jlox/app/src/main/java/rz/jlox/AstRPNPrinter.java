
package rz.jlox;

import rz.jlox.Expr.Binary;
import rz.jlox.Expr.Grouping;
import rz.jlox.Expr.Literal;
import rz.jlox.Expr.Unary;

public class AstRPNPrinter implements Expr.Visitor<String> {
    @Override
    public String visitBinaryExpr(Binary expr) {
        return expr.left.accept(this) + " " + expr.right.accept(this) + " " + expr.operator.lexeme;
    }

    @Override
    public String visitGroupingExpr(Grouping expr) {
        return expr.expression.accept(this) + " group";
    }

    @Override
    public String visitLiteralExpr(Literal expr) {
        return expr.value == null ? "nil" : expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Unary expr) {
        return expr.right.accept(this) + " " + expr.operator.lexeme;
    }
}
