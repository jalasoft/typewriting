package cz.jalasoft.psaninastroji.domain.model.lesson;

import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.Exercise;

/**
 * @author Jan Lastovicka
 * @since 10/10/2019
 */
public final class Lesson {

    private final int number;
    private final Instructions instructions;
    private final Pattern pattern;
    private final ValidationRule validationRule;

    public Lesson(int number, Instructions instructions, Pattern pattern, ValidationRule validationRule) {
        this.number = number;
        this.instructions = instructions;
        this.pattern = pattern;
        this.validationRule = validationRule;
    }

    public int number() {
        return number;
    }

    public Instructions instructions() {
        return instructions;
    }

    public Pattern pattern() {
        return pattern;
    }

    public Exercise newExercise() {
        throw new UnsupportedOperationException();
    }
}
