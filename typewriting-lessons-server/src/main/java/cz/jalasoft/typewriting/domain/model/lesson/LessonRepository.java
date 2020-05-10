package cz.jalasoft.typewriting.domain.model.lesson;

import cz.jalasoft.domain.model.lesson.Lesson;
import cz.jalasoft.domain.model.lesson.LessonNumber;

import java.util.Optional;

public interface LessonRepository {

	Optional<Lesson> byNumber(LessonNumber number);
}
