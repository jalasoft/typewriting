package cz.jalasoft.psaninastroji;

import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.Exercise;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.infrastructure.xml.XmlLessonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @author Jan Lastovicka
 * @since 10/10/2019
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XmlLessonRepositoryTest {

    private LessonRepository repository;

    @BeforeAll
    public void init() {
        repository = new XmlLessonRepository();
    }

    @Test
    public void firstTestIsObtained() {
        Lesson lesson = repository.byNumber(1);

    }
}
