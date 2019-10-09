package cz.jalasoft.psaninastroji.domain.model.excercise;

import cz.jalasoft.psaninastroji.domain.model.EvaluationResult;
import cz.jalasoft.psaninastroji.domain.model.EvaluationService;
import cz.jalasoft.psaninastroji.domain.model.InputText;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
public final class Excercise {

    private final ExcerciseId id;
    private final Instructions instructions;
    private final Pattern pattern;
    private final EvaluationService rules;

    public ExcerciseId id() {
        return id;
    }

    public Instructions instructions() {
        return instructions;
    }

    public Pattern pattern() {
        return pattern;
    }

    public EvaluationResult evaluate(InputText input) {

    }
}
