package cz.jalasoft.typewriting.reactive.application;

import cz.jalasoft.domain.model.lesson.Lesson;
import cz.jalasoft.domain.model.lesson.LessonNumber;
import cz.jalasoft.typewriting.reactive.domain.model.lesson.LessonRepository;
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
}
