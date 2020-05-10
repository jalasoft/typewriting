package cz.jalasoft.typewriting.config;

import cz.jalasoft.typewriting.domain.model.lesson.LessonRepository;
import cz.jalasoft.typewriting.infrastructure.xml.XmlLessonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.function.Supplier;

@Configuration
public class InfrastructureConfig {

	private static final String LESSONS_FILE = "lessons.xml";

	@Bean
	public LessonRepository lessonRepository() {
		Supplier<InputStream> source = () -> getClass().getClassLoader().getResourceAsStream(LESSONS_FILE);
		return new XmlLessonRepository(source);
	}
}
