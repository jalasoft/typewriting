package cz.jalasoft.typewriting.reactive.config;

import cz.jalasoft.typewriting.reactive.domain.model.lesson.LessonRepository;
import cz.jalasoft.typewriting.reactive.infrastructure.xml.XmlLessonRepository;
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

    /*
    @Bean
    public ConnectionFactory connectionFactory() {

        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "h2")
                .option(ConnectionFactoryOptions.USER, "sa")
                .option(ConnectionFactoryOptions.PASSWORD, "")
                .build();

        return ConnectionFactories.get(options);
    }

    @Bean
    public ExerciseRepository exerciseRepository() {
        return new InMemoryExerciseRepository();
    }*/
}
