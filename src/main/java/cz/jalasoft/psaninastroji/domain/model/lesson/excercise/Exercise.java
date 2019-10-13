package cz.jalasoft.psaninastroji.domain.model.lesson.excercise;

import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
public final class Exercise {

    private final Lesson lesson;
    private final List<Input> inputs;

    private int index;

    public Exercise(Lesson lesson) {
        this.lesson = lesson;
        this.inputs = new ArrayList<>();
        this.index = 0;
    }

    public void acceptKey(char key) {

        if (isFinished()) {
            throw new IllegalStateException("Exercise is finished.");
        }

        char expectedChar = lesson.pattern().at(index);
        char currentChar = key;

        boolean isTypo = expectedChar != currentChar;

        if (!isTypo) {
            index++;
        }

        this.inputs.add(new Input(currentChar, isTypo));
    }

    public ExerciseResult result() {
        if (!isFinished()) {
            throw new IllegalStateException("Exercise is not yet finished.");
        }

        return new ExerciseResult(lesson, inputs);
    }

    public boolean isFinished() {
        return index == lesson.pattern().length();
    }
}
