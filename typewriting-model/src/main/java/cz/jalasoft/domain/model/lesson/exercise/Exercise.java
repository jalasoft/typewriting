package cz.jalasoft.domain.model.lesson.exercise;


import cz.jalasoft.domain.model.lesson.LessonNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
public final class Exercise {

    private final ExerciseId id;
    private final LessonNumber lessonNumber;
    private final List<KeyInput> inputs;

    public Exercise(ExerciseId id, LessonNumber lessonNumber, List<KeyInput> inputs) {
        this.id = id;
        this.lessonNumber = lessonNumber;
        this.inputs = inputs;
    }

    public Exercise(ExerciseId id, LessonNumber lessonNumber) {
        this(id, lessonNumber, new ArrayList<>());
    }

    public ExerciseId id() {
        return id;
    }

    public void acceptKey(char key) {

    	/*
        if (isFinished()) {
            throw new IllegalStateException("Exercise is finished.");
        }

        char expectedChar = lesson.pattern().at(index);
        char currentChar = key;

        boolean isTypo = expectedChar != currentChar;

        if (!isTypo) {
            index++;
        }*/

        this.inputs.add(new KeyInput(key));
    }

    public LessonNumber lessonNumber() {
    	return lessonNumber;
	}

	public String input() {
        return inputs.stream().reduce(new StringBuilder(), (sb, ki) -> sb.append(ki.key()), StringBuilder::append).toString();
    }

	//public ExerciseResult validate(Lesson lesson) {
    //    return null;
    //}

    /*
    public ExerciseResult result() {
        if (!isFinished()) {
            throw new IllegalStateException("Exercise is not yet finished.");
        }

        return new ExerciseResult(lesson, inputs);
    }

    public boolean isFinished() {
        return index == lesson.pattern().length();
    }*/
}
