package cz.jalasoft.psaninastroji.config;

import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseRepository;
import cz.jalasoft.psaninastroji.infrastructure.memory.InMemoryExerciseRepository;
import cz.jalasoft.psaninastroji.infrastructure.xml.XmlLessonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author Jan Lastovicka
 * @since 15/10/2019
 */
@Configuration
public class InfrastructureConfig {

    @Bean
    public LessonRepository lessonRepository() {
        Supplier<InputStream> dataSupplier = () -> getClass().getClassLoader().getResourceAsStream("lessons.xml");
        return new XmlLessonRepository(dataSupplier);
    }

    @Bean
    public ExerciseRepository exerciseRepository() {
        return new InMemoryExerciseRepository();
    }
}
