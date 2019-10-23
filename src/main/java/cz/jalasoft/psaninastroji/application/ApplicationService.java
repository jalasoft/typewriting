package cz.jalasoft.psaninastroji.application;

import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonNumber;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
@Component
public class ApplicationService {

    private final LessonRepository lessonRepository;

    @Autowired
    public ApplicationService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Mono<Lesson> lessonByNumber(int lessonNumber) {
        LessonNumber number = new LessonNumber(lessonNumber);
        return lessonRepository.byNumber(number);
    }

    /*
    public Mono<ExerciseId> newExercise(int lessonNumber) {
        return lessonRepository.byNumber(lessonNumber).map(lesson -> {
            ExerciseId exerciseId = exerciseRepository.nextId();
            Exercise exercise = lesson.newExercise(exerciseId);
            exerciseRepository.safe(exercise);
            return exerciseId;
        });
    }*/
}
