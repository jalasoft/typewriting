package cz.jalasoft.domain.model.lesson;

/**
 * @author Jan Lastovicka
 * @since 11/10/2019
 */
public final class StandardProgressRule implements LessonProgress {

    @Override
    public LessonNumber progress(Lesson lesson, LessonResult result) {
        return null;
    }

    public ProgressDescription description() {
        return null;
    }

    /*
    public static Builder newRule() {
        return new Builder();
    }

    //-----------------------------------------------------------------------
    //INSTANCE SCOPE
    //-----------------------------------------------------------------------

    private final List<Function<LessonValidationResult, Optional<LessonNumber>>> router;

    public StandardProgressRule(List<Function<LessonValidationResult, Optional<LessonNumber>>> router) {
        this.router = router;
    }

    @Override
    public LessonNumber progress(LessonValidationResult result) {

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

        private final List<Function<LessonValidationResult, Optional<LessonNumber>>> router = new ArrayList<>();

        public Builder typosEqualTo(int typosCount, LessonNumber lessonNumber) {
            Function<LessonValidationResult, Optional<LessonNumber>> f =  r -> r.typosCount() == typosCount ? Optional.of(lessonNumber) : Optional.empty();
            router.add(f);
            return this;
        }

        public Builder typosGreaterThan(int typosCount, LessonNumber lessonNumber) {
            Function<LessonValidationResult, Optional<LessonNumber>> f = r -> r.typosCount() > typosCount ? Optional.of(lessonNumber) : Optional.empty();
            router.add(f);
            return this;
        }

        public ProgressRule get() {
            return new StandardProgressRule(router);
        }
    }*/
}
