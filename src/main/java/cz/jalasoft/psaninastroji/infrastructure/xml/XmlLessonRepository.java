package cz.jalasoft.psaninastroji.infrastructure.xml;

import cz.jalasoft.psaninastroji.domain.model.lesson.Instructions;
import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.Pattern;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
public final class XmlLessonRepository implements LessonRepository {

    private final SAXParser parser;
    private final Path file;

    public XmlLessonRepository(Path file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
            this.parser = factory.newSAXParser();
            this.file = file;
        } catch (ParserConfigurationException | SAXException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public Mono<Lesson> byNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Excercise number must not be negative.");
        }

        Flux<Lesson> flux = Flux.create(sink -> {
            try {
                parser.parse(file.toFile(), new LessonsHandler(sink));
                sink.complete();
            } catch (SAXException | IOException exc) {
                sink.error(exc);
            }
        });

        return flux.filter(l -> l.number() == number).next();
    }

    //---------------------------------------------------------------------------------------------------------------
    //XML HANDLER
    //---------------------------------------------------------------------------------------------------------------

    private static final class LessonsHandler extends DefaultHandler {

        private static final String LESSON_ELEMENT = "lesson";
        private static final String INSTRUCTIONS_ELEMENT = "instructions";
        private static final String TEXT_ELEMENT = "text";
        private static final String VALIDATION_ELEMENT = "validation";

        private final FluxSink<Lesson> sink;

        private Integer lessonNumber;
        private boolean instructionsElement;
        private String instructions;
        private boolean textElement;
        private String text;

        public LessonsHandler(FluxSink<Lesson> sink) {
            this.sink = sink;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case LESSON_ELEMENT:
                    lessonNumber = Integer.parseInt(attributes.getValue("number"));
                    break;

                case INSTRUCTIONS_ELEMENT:
                    instructionsElement = true;
                    break;

                case TEXT_ELEMENT:
                    textElement = true;
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
        	if (instructionsElement) {
        	    instructions = new String(ch);
            }

        	if (textElement) {
        	    text = new String(ch);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (localName) {
                case LESSON_ELEMENT:
                    buildLesson();
                    lessonNumber = null;

                case INSTRUCTIONS_ELEMENT:
                    instructionsElement = false;

                case TEXT_ELEMENT:
                    textElement = false;
            }
        }

        private void buildLesson() {
            Instructions instructions = new Instructions(this.instructions);
            Pattern pattern = new Pattern(this.text);
            Lesson lesson = new Lesson(lessonNumber, instructions, pattern, null);

            sink.next(lesson);
        }
    }
}
