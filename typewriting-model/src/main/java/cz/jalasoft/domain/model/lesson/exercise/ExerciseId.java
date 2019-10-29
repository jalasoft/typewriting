package cz.jalasoft.domain.model.lesson.exercise;

/**
 * @author Jan Lastovicka
 * @since 19/10/2019
 */
public final class ExerciseId {

    private final String value;

    public ExerciseId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
