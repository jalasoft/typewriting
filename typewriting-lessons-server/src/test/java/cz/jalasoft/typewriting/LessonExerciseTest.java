package cz.jalasoft.typewriting;

import cz.jalasoft.domain.model.lesson.Instructions;
import cz.jalasoft.domain.model.lesson.Lesson;
import cz.jalasoft.domain.model.lesson.LessonNumber;
import cz.jalasoft.domain.model.lesson.Pattern;
import cz.jalasoft.domain.model.lesson.TyposBasedProgressRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

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
                new LessonNumber(23),
                new Instructions("Zadej text"),
                new Pattern("asdfgh"),
                TyposBasedProgressRule.newRule()
                        .typosEqualTo(0, new LessonNumber(24))
                        .typosGreaterThan(0, new LessonNumber(23))
                        .get()
        );
    }

    /*
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
    }*/
}
