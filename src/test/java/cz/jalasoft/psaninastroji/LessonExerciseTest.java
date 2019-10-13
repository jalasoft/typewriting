package cz.jalasoft.psaninastroji;

import cz.jalasoft.psaninastroji.domain.model.lesson.*;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.Exercise;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseResult;
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
    }

    @Test
    public void exerciseWithoutTypoIsSignaledCorrectly() {

        Exercise exercise = lesson.newExercise();
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

        Exercise exercise = lesson.newExercise();
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
