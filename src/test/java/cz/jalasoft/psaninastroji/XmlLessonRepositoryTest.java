package cz.jalasoft.psaninastroji;

import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.TyposDrivingMovementValidationRule;
import cz.jalasoft.psaninastroji.infrastructure.xml.XmlLessonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jan Lastovicka
 * @since 10/10/2019
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XmlLessonRepositoryTest {

    private static final String LESSONS_XML = "lessons.xml";

    private LessonRepository repository;

    @BeforeAll
    public void init() {
        Supplier<InputStream> dataSupplier = () -> getClass().getClassLoader().getResourceAsStream(LESSONS_XML);
        repository = new XmlLessonRepository(dataSupplier);
    }

    @Test
    public void firstTestIsObtained() {
        Mono<Lesson> lessonMono = repository.byNumber(1);

        Lesson lesson = lessonMono.block();

        assertNotNull(lesson);
        assertEquals(lesson.number(), 1);
        assertEquals(lesson.pattern().value(), "k a l a d a s l a d k a j d a l k s a l a j a d a s a s a k s a j");
        assertTrue(lesson.validationRule() instanceof TyposDrivingMovementValidationRule);

    }
}
