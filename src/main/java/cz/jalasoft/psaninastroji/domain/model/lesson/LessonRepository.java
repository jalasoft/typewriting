package cz.jalasoft.psaninastroji.domain.model.lesson;

import reactor.core.publisher.Mono;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
public interface LessonRepository {

    Mono<Lesson> byNumber(int number);
}
