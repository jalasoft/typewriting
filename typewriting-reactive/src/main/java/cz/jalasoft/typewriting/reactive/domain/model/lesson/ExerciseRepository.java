package cz.jalasoft.typewriting.reactive.domain.model.lesson;

import cz.jalasoft.domain.model.lesson.exercise.Exercise;
import cz.jalasoft.domain.model.lesson.exercise.ExerciseId;
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
