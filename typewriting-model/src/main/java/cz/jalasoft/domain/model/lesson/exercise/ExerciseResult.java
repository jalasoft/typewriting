package cz.jalasoft.domain.model.lesson.exercise;

import cz.jalasoft.domain.model.lesson.Lesson;

import java.util.List;

/**
 * @author Jan Lastovicka
 * @since 11/10/2019
 */
public final class ExerciseResult {

    private final Lesson lesson;
    private final List<KeyInput> inputs;

    public ExerciseResult(Lesson lesson, List<KeyInput> inputs) {
        this.lesson = lesson;
        this.inputs = inputs;
    }

    public long typosCount() {
        //return inputs.stream().filter(KeyInput::isTypo).count();
        return 0;
    }
}
