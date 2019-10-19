package cz.jalasoft.psaninastroji.infrastructure.memory;

import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.Exercise;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseId;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jan Lastovicka
 * @since 19/10/2019
 */
public final class InMemoryExerciseRepository implements ExerciseRepository {

    private final AtomicInteger idGenerator = new AtomicInteger(0);

    private final Collection<Exercise> exercises = new ArrayList<>();

    @Override
    public ExerciseId nextId() {
        int nextInt = idGenerator.getAndIncrement();
        String value = "exercise_" + nextInt;
        return new ExerciseId(value);
    }

    @Override
    public void safe(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("Exercise must not be null");
        }
        this.exercises.add(exercise);
    }
}
