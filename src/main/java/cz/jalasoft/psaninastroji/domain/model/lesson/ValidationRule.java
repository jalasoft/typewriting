package cz.jalasoft.psaninastroji.domain.model.lesson;

import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseResult;

public interface ValidationRule {

    LessonNumber validate(ExerciseResult result);

}
