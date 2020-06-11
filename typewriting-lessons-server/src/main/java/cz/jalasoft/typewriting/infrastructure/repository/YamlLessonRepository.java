package cz.jalasoft.typewriting.infrastructure.repository;

import cz.jalasoft.domain.model.lesson.Lesson;
import cz.jalasoft.domain.model.lesson.LessonNumber;
import cz.jalasoft.typewriting.domain.model.lesson.LessonRepository;

import java.util.Optional;

public final class YamlLessonRepository implements LessonRepository {



    @Override
    public Optional<Lesson> byNumber(LessonNumber number) {
        return Optional.empty();
    }
}
