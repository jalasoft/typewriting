package cz.jalasoft.ccee.exception;

public final class BadInputException extends EvaluationException {

    private final char symbol;

    public BadInputException(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getMessage() {
        return "Unexpected symbol '" + symbol + "' found in input sequence.";
    }
}
