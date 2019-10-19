package cz.jalasoft.psaninastroji.domain.model.lesson.excercise;

/**
 * @author Jan Lastovicka
 * @since 19/10/2019
 */
public interface ExerciseRepository {

    ExerciseId nextId();

    void safe(Exercise exercise);


}
