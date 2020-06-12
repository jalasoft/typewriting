package cz.jalasoft.domain.model.lesson;

import java.util.Optional;

public final class ProgressRule {

    private final LessonNumber nextLesson;
    private final String progressExpression;

    public ProgressRule(LessonNumber nextLesson, String progressExpression) {
        this.nextLesson = nextLesson;
        this.progressExpression = progressExpression;
    }

    public Optional<LessonNumber> evaluate(LessonResult result) {

        return null;
    }

    public String text() {
        return progressExpression;
    }
}
