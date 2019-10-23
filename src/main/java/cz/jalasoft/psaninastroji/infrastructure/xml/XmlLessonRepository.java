package cz.jalasoft.psaninastroji.infrastructure.xml;

import cz.jalasoft.psaninastroji.domain.model.lesson.Instructions;
import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonNumber;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.Pattern;
import cz.jalasoft.psaninastroji.domain.model.lesson.TyposDrivingMovementValidationRule;
import cz.jalasoft.psaninastroji.domain.model.lesson.ValidationRule;
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
import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
public final class XmlLessonRepository implements LessonRepository {

    private final SAXParser parser;
    private final Supplier<InputStream> dataSupplier;

    public XmlLessonRepository(Supplier<InputStream> dataSupplier) {
        try {
            SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
            this.parser = factory.newSAXParser();
            this.dataSupplier = dataSupplier;
        } catch (ParserConfigurationException | SAXException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public Mono<Lesson> byNumber(LessonNumber number) {
        if (number.value() < 0) {
            throw new IllegalArgumentException("Excercise number must not be negative.");
        }

        Flux<Lesson> flux = Flux.create(sink -> {
            try (InputStream data = dataSupplier.get()) {
                parser.parse(data, new LessonsHandler(sink));
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
        private static final String TYPO_ELEMENT = "typo";

        private final FluxSink<Lesson> sink;

        private Integer lessonNumber;
        private boolean instructionsElement;
        private String instructions;
        private boolean textElement;
        private String text;

        private TyposDrivingMovementValidationRule.Builder builder;

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

                case VALIDATION_ELEMENT:
                    String type = attributes.getValue("type");

                    switch (type) {
                        case "typos_driving":
                            builder = TyposDrivingMovementValidationRule.newRule();
                            break;

                        default:
                            throw new IllegalStateException("Unknown validation type: " + type);
                    }
                    break;

                case TYPO_ELEMENT:
                    if (builder == null) {
                        throw new IllegalStateException();
                    }

                    int lesson = Integer.parseInt(attributes.getValue("lesson"));
                    LessonNumber lessonNumber = new LessonNumber(lesson);

                    String equalsStr = attributes.getValue("equals");
                    if (equalsStr != null) {
                        int equals = Integer.parseInt(equalsStr);
                        builder.typosEqualTo(equals, lessonNumber);
                    }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
        	if (instructionsElement) {
        	    instructions = new String(ch, start, length);
            }

        	if (textElement) {
        	    text = new String(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case LESSON_ELEMENT:
                    buildLesson();
                    lessonNumber = null;
                    builder = null;

                case INSTRUCTIONS_ELEMENT:
                    instructionsElement = false;

                case TEXT_ELEMENT:
                    textElement = false;
            }
        }

        private void buildLesson() {
            LessonNumber number = new LessonNumber(lessonNumber);
            Instructions instructions = new Instructions(this.instructions);
            Pattern pattern = new Pattern(this.text);
            ValidationRule validationRule = builder.get();

            Lesson lesson = new Lesson(number, instructions, pattern, validationRule);

            sink.next(lesson);
        }
    }
}
