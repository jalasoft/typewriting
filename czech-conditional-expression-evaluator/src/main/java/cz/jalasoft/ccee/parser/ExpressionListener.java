package cz.jalasoft.ccee.parser;

public interface ExpressionListener {

    enum BinaryOperator {
        LESS("<"),
        LESS_OR_EQUAL("<="),
        GREATER(">"),
        GREATER_OR_EQUAL(">="),
        EQUAL("=");

        private String mark;

        BinaryOperator(String mark) {
            this.mark = mark;
        }

        public String mark() {
            return mark;
        }
    }

    enum UnaryOperator {
        IDENTITY(""),
        NEGATION("!");

        private final String mark;

        UnaryOperator(String mark) {
            this.mark = mark;
        }

        public String mark() {
            return mark;
        }
    }

    void binaryExpression(String lOperandIdent, BinaryOperator operator, int rOperand);

    void binaryExpression(String lOperandIdent, BinaryOperator operator, String rOperandIdent);

    void unaryExpression(String operandIdent, UnaryOperator operator);

    void and();

    void or();
}
