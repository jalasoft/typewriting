package cz.jalasoft.ccee.parser;

public interface ExpressionListener {

    enum BinaryOperator {
        LESS("<") {
            @Override
            public boolean perform(int op1, int op2) {
                return op1 < op2;
            }
        },
        LESS_OR_EQUAL("<=") {
            @Override
            public boolean perform(int op1, int op2) {
                return op1 <= op2;
            }
        },
        GREATER(">") {
            @Override
            public boolean perform(int op1, int op2) {
                return op1 > op2;
            }
        },
        GREATER_OR_EQUAL(">=") {
            @Override
            public boolean perform(int op1, int op2) {
                return op1 >= op2;
            }
        },
        EQUAL("=") {
            @Override
            public boolean perform(int op1, int op2) {
                return op1 == op2;
            }
        };

        private String mark;

        BinaryOperator(String mark) {
            this.mark = mark;
        }

        public String mark() {
            return mark;
        }

        public abstract boolean perform(int op1, int op2);
    }

    enum UnaryOperator {
        IDENTITY("") {
            @Override
            public boolean perform(boolean op) {
                return op;
            }
        },
        NEGATION("!") {
            @Override
            public boolean perform(boolean op) {
               return !op;
            }
        };

        private final String mark;

        UnaryOperator(String mark) {
            this.mark = mark;
        }

        public String mark() {
            return mark;
        }

        public abstract boolean perform(boolean op);
    }

    void exp(String lOperandIdent, BinaryOperator operator, int rOperand);

    void exp(String lOperandIdent, BinaryOperator operator, String rOperandIdent);

    void exp(String operandIdent, UnaryOperator operator);

    void and();

    void or();
}
