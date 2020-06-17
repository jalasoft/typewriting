package cz.jalasoft.ccee;

import cz.jalasoft.ccee.exception.EvaluationException;
import cz.jalasoft.ccee.parser.Parser;
import cz.jalasoft.ccee.semantic.PrintingExpressionListener;

public final class CzechConditionalStatementsEvaluator {

    private final Parser parser;

    public CzechConditionalStatementsEvaluator() {
        this.parser = new Parser();
    }

    public BoolEvaluation evaluate(String input) throws EvaluationException {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Expression to evaluate must not be null or empty.");
        }

        parser.parse(input, new PrintingExpressionListener(System.out));

        return null;
    }
}
