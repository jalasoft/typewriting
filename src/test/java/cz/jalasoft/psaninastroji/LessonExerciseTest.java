package cz.jalasoft.psaninastroji;

import cz.jalasoft.psaninastroji.domain.model.lesson.*;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.Exercise;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseId;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseResult;
import cz.jalasoft.psaninastroji.infrastructure.memory.InMemoryExerciseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jan Lastovicka
 * @since 13/10/2019
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LessonExerciseTest {

    private Lesson lesson;
    private ExerciseRepository exerciseRepository;

    @BeforeAll
    public void init() {
        lesson = new Lesson(
                23,
                new Instructions("Zadej text"),
                new Pattern("asdfgh"),
                TyposDrivingMovementValidationRule.newRule()
                        .typosEqualTo(0, new LessonNumber(24))
                        .typosGreaterThan(0, new LessonNumber(23))
                        .get()
        );

        exerciseRepository = new InMemoryExerciseRepository();
    }

    @Test
    public void exerciseWithoutTypoIsSignaledCorrectly() {

        ExerciseId id = exerciseRepository.nextId();
        Exercise exercise = lesson.newExercise(id);
        exercise.acceptKey('a');
        exercise.acceptKey('s');
        exercise.acceptKey('d');
        exercise.acceptKey('f');
        exercise.acceptKey('g');
        exercise.acceptKey('h');

        assertTrue(exercise.isFinished());
        ExerciseResult result = exercise.result();

        assertEquals(0, result.typosCount());
    }

    @Test
    public void exerciseWithOneTypoIsSignaledCorrectly() {
        ExerciseId id = exerciseRepository.nextId();
        Exercise exercise = lesson.newExercise(id);
        exercise.acceptKey('a');
        exercise.acceptKey('s');
        exercise.acceptKey('d');
        exercise.acceptKey('d');
        exercise.acceptKey('f');
        exercise.acceptKey('g');
        exercise.acceptKey('h');

        assertTrue(exercise.isFinished());

        ExerciseResult result = exercise.result();
        assertEquals(1, result.typosCount());
    }
}
