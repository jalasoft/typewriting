package cz.jalasoft.typewriting.reactive.infrastructure.db;

import cz.jalasoft.domain.model.lesson.LessonNumber;
import cz.jalasoft.domain.model.lesson.exercise.Exercise;
import cz.jalasoft.domain.model.lesson.exercise.ExerciseId;
import cz.jalasoft.typewriting.reactive.domain.model.lesson.ExerciseRepository;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class H2DbExerciseRepository implements ExerciseRepository {

	private final ConnectionFactory connectionFactory;

	@Autowired
	public H2DbExerciseRepository(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public ExerciseId nextId() {
		return null;
	}

	@Override
	public Mono<Exercise> byId(ExerciseId id) {
		/*
		Mono.from(connectionFactory.create())
				.flatMap(c -> {
					Mono.from(c.beginTransaction())
							.then()
							.flatMap(v -> findByIdReactively(c, id))



				})*/

		return null;
	}

	private Mono<Result> findByIdReactively(Connection c, ExerciseId id) {
		return Mono.from(c.createStatement("select * from exercise where id = $id")
				.bind("$id", id.value())
				.execute());
	}

	private Mono<Exercise> map(Result result) {
		return Mono.from(result.map((r, m) -> {
			ExerciseId id = new ExerciseId(r.get("id", String.class));
			LessonNumber lessonNumber = new LessonNumber(r.get("lesson_number", Integer.class));

			return new Exercise(id, lessonNumber, null);
		}));
	}

	@Override
	public Mono<Void> safe(Exercise exercise) {

		return Mono.from(connectionFactory.create())
				.flatMap(c -> Mono.from(c.beginTransaction())
						.then()
						.flatMap(v -> insertExerciseReactively(c, exercise))
						.delayUntil(r -> c.commitTransaction())
						.doFinally(s -> c.close())
				).then();
	}

	private Mono<Result> insertExerciseReactively(Connection c, Exercise exercise) {
		return Mono.from(c.createStatement("insert into exercise(id, lesson_number, input) values ($id, $lesson_number, $input)")
								.bind("$id", exercise.id().value())
								.bind("$lesson_number", exercise.lessonNumber().value())
								.bind("$input", exercise.input())
								.returnGeneratedValues("id")
								.execute());
	}
}
