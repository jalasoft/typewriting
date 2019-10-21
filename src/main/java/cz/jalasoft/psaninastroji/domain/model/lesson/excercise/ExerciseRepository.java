package cz.jalasoft.psaninastroji.domain.model.lesson.excercise;

import reactor.core.publisher.Mono;

/**
 * @author Jan Lastovicka
 * @since 19/10/2019
 */
public interface ExerciseRepository {

    ExerciseId nextId();

    Mono<Exercise> byId(ExerciseId id);

    Mono<Void> safe(Exercise exercise);


}
