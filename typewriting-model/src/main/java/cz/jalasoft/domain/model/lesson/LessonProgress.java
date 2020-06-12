package cz.jalasoft.domain.model.lesson;

import java.util.List;
import java.util.stream.Stream;

public final class LessonProgress {

    private final List<ProgressRule> rules;

    public LessonProgress(List<ProgressRule> rules) {
        this.rules = rules;
    }

    public LessonNumber progress(LessonResult result) {
        return rules.stream()
                .flatMap(r -> r.evaluate(result).stream())
                .findFirst()
                .orElseThrow();
    }

    public Stream<ProgressRule> rules() {
        return rules.stream();
    }
}
