package cz.jalasoft.psaninastroji.application;

import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
@Component
public class ApplicationService {

    private final LessonRepository repository;

    @Autowired
    public ApplicationService(LessonRepository repository) {
        this.repository = repository;
    }

    public Mono<Lesson> lessonByNumber(int number) {
        return repository.byNumber(number);
    }

    public Mono<Exercise> newExercise(int number) {
        Mono<Exercise> exerciseMono = repository.byNumber(number).map(Lesson::newExercise);
        return exerciseMono;
    }
}
