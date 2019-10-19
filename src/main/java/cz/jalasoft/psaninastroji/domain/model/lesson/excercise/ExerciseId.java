package cz.jalasoft.psaninastroji.domain.model.lesson.excercise;

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
