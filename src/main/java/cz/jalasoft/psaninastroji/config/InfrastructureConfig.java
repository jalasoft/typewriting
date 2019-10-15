package cz.jalasoft.psaninastroji.config;

import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.infrastructure.xml.XmlLessonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

/**
 * @author Jan Lastovicka
 * @since 15/10/2019
 */
@Configuration
public class InfrastructureConfig {

    @Bean
    public LessonRepository lessonRepository() {
        Path file = getClass().getClassLoader().getResource("lessons.xml").
        return new XmlLessonRepository();
    }
}
