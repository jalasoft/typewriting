package cz.jalasoft.psaninastroji.application;

import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
@Component
public class ApplicationService {

    private final LessonRepository repository;
    private final EvaluationService evaluationService;

    @Autowired
    public ApplicationService(LessonRepository repository, EvaluationService evaluationService) {
        this.repository = repository;
        this.evaluationService = evaluationService;
    }

    public Lesson lessonByNumber(int number) {
        return repository.byNumber(number);
    }

    public Exercise newExercise(int number) {
        Lesson lesson = repository.byNumber(number);
        Exercise exercise = lesson.newExercise();
        return exercise;
    }
}
