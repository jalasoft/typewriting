package cz.jalasoft.ccee;


import cz.jalasoft.ccee.exception.EvaluationException;
import jdk.jshell.EvalException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CzechConditionalStatementEvaluatorTest {

    private CzechConditionalStatementsEvaluator evaluator;

    @BeforeAll
    public void init() {
        evaluator = new CzechConditionalStatementsEvaluator();
    }

    public Object[][] expressions() {
        return new Object[][] {
                { "je včas" },
                { "prom je rovno 4" },
                { "proměnná je větší než 67 "},
                { "čísílko je větší nebo rovno -854" },
                { "chyb je více než 78 " },
                { "chyb je více nebo rovno 2" },
                { "c je menší než 7" },
                { "číslo je menší nebo rovno 8" },
                { "b je méně než 6" },
                { "prom1 je méně nebo rovno 785544" },
                { "prom1 je rovno 8 nebo prom1 je větší než 89" },
                { "číslo je menší nebo rovno 5 nebo číslo2 je větší než číslo1" },
                { "chyb je více než 5 nebo je včas" },
                { "chyb je méně než 2 nebo není včas" },
                { "číslo1 je menší jak 3 a je validni " },
                { "chyb je míň jak 3 nebo chyb je víc jak 5 a je včas" },
                { "je včas nebo chyb je stejně jako 5" }
        };
    }

    @ParameterizedTest()
    @MethodSource("expressions")
    public void inputExpressionsAreSuccessfullyEvaluated(String expression) throws EvaluationException {
        evaluator.evaluate(expression);
    }
}
