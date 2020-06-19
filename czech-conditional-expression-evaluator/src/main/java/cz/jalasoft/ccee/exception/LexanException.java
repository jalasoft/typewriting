package cz.jalasoft.ccee.exception;

public final class LexanException extends ExpressionException {

    private final char symbol;

    public LexanException(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getMessage() {
        return "Unexpected symbol '" + symbol + "' found in input sequence.";
    }
}
