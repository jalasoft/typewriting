package cz.jalasoft.psaninastroji.domain.model.lesson;

import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author Jan Lastovicka
 * @since 11/10/2019
 */
public final class TyposDrivingMovementValidationRule implements ValidationRule {

    public static Builder newRule() {
        return new Builder();
    }

    //-----------------------------------------------------------------------
    //INSTANCE SCOPE
    //-----------------------------------------------------------------------

    private final List<Function<ExerciseResult, Optional<LessonNumber>>> router;

    public TyposDrivingMovementValidationRule(List<Function<ExerciseResult, Optional<LessonNumber>>> router) {
        this.router = router;
    }

    @Override
    public LessonNumber validate(ExerciseResult result) {

        LessonNumber nextLesson = router.stream()
                .map(f -> f.apply(result))
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No next lesson."));

        return nextLesson;
    }

    //--------------------------------------------------------------------------
    //BUILDER
    //--------------------------------------------------------------------------

    public static final class Builder {

        private final List<Function<ExerciseResult, Optional<LessonNumber>>> router = new ArrayList<>();

        public Builder typosEqualTo(int typosCount, LessonNumber lessonNumber) {
            Function<ExerciseResult, Optional<LessonNumber>> f =  r -> r.typosCount() == typosCount ? Optional.of(lessonNumber) : Optional.empty();
            router.add(f);
            return this;
        }

        public Builder typosGreaterThan(int typosCount, LessonNumber lessonNumber) {
            Function<ExerciseResult, Optional<LessonNumber>> f = r -> r.typosCount() > typosCount ? Optional.of(lessonNumber) : Optional.empty();
            router.add(f);
            return this;
        }

        public ValidationRule get() {
            return new TyposDrivingMovementValidationRule(router);
        }
    }
}
