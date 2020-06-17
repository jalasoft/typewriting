package cz.jalasoft.ccee.semantic;

import cz.jalasoft.ccee.parser.ExpressionListener;

import java.io.PrintStream;

public final class PrintingExpressionListener implements ExpressionListener {

    private final PrintStream out;

    public PrintingExpressionListener(PrintStream out) {
        this.out = out;
    }

    @Override
    public void binaryExpression(String lOperandIdent, BinaryOperator operator, int rOperand) {
        out.printf("%s %s %d", lOperandIdent, operator.mark(), rOperand);
    }

    @Override
    public void binaryExpression(String lOperandIdent, BinaryOperator operator, String rOperandIdent) {
        out.printf("%s %s %s", lOperandIdent, operator.mark(), rOperandIdent);
    }

    @Override
    public void unaryExpression(String operandIdent, UnaryOperator operator) {
        out.printf("%s%s", operator.mark(), operandIdent);
    }

    @Override
    public void and() {
        out.printf(" AND ");
    }

    @Override
    public void or() {
        out.printf(" OR ");
    }
}
