package cz.jalasoft.ccee.exception;

import cz.jalasoft.ccee.lexan.LexicalSymbol;

public final class ParserException extends EvaluationException {

    private final LexicalSymbol symbol;

    public ParserException(LexicalSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getMessage() {
        return "Unexpected lexical symbol found: '" + symbol + "'.";
    }
}
