package cz.jalasoft.psaninastroji.domain.model.lesson;

/**
 * @author Jan Lastovicka
 * @since 10/10/2019
 */
public final class Lesson {

    private final LessonNumber number;
    private final Instructions instructions;
    private final Pattern pattern;
    private final ValidationRule validationRule;

    public Lesson(LessonNumber number, Instructions instructions, Pattern pattern, ValidationRule validationRule) {
        this.number = number;
        this.instructions = instructions;
        this.pattern = pattern;
        this.validationRule = validationRule;
    }

    public LessonNumber number() {
        return number;
    }

    public Instructions instructions() {
        return instructions;
    }

    public Pattern pattern() {
        return pattern;
    }

    public ValidationRule validationRule() {
        return validationRule;
    }

    /*
    public Exercise newExercise(ExerciseId id) {
        return new Exercise(id, this);
    }*/
}
