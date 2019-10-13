package cz.jalasoft.psaninastroji.domain.model.lesson.excercise;

import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;

import java.util.List;

/**
 * @author Jan Lastovicka
 * @since 11/10/2019
 */
public final class ExerciseResult {

    private final Lesson lesson;
    private final List<Input> inputs;

    public ExerciseResult(Lesson lesson, List<Input> inputs) {
        this.lesson = lesson;
        this.inputs = inputs;
    }

    public long typosCount() {
        return inputs.stream().filter(Input::isTypo).count();
    }
}
