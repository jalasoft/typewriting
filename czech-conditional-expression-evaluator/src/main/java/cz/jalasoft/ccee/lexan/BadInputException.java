package cz.jalasoft.ccee.lexan;

public final class BadInputException extends Exception {

    private final char symbol;

    public BadInputException(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getMessage() {
        return "Unexpected symbol '" + symbol + "' found in input sequence.";
    }
}
