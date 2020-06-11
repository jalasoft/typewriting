package cz.jalasoft.domain.model.lesson;

public interface ProgressRule {

    ProgressDescription description();

    LessonNumber progress(int typosCount, boolean inTime);

}
