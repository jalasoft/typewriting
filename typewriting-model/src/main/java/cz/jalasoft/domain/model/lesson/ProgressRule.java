package cz.jalasoft.domain.model.lesson;

public interface ProgressRule {

    LessonNumber progress(LessonValidationResult result);

}
