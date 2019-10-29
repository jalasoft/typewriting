package cz.jalasoft.typewriting.reactive.domain.model.lesson;

import cz.jalasoft.domain.model.lesson.Lesson;
import cz.jalasoft.domain.model.lesson.LessonNumber;
import reactor.core.publisher.Mono;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
public interface LessonRepository {

    Mono<Lesson> byNumber(LessonNumber number);
}
