package cz.jalasoft.typewriting.infrastructure.xml;

import cz.jalasoft.domain.model.lesson.Instructions;
import cz.jalasoft.domain.model.lesson.Lesson;
import cz.jalasoft.domain.model.lesson.LessonNumber;
import cz.jalasoft.domain.model.lesson.Pattern;
import cz.jalasoft.domain.model.lesson.TyposBasedProgressRule;
import cz.jalasoft.typewriting.domain.model.lesson.LessonRepository;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class XmlLessonRepository implements LessonRepository {

	private final Supplier<InputStream> source;
	private final SAXParserFactory factory;

	public XmlLessonRepository(Supplier<InputStream> source) {
		this.source = source;
		this.factory = SAXParserFactory.newDefaultInstance();
	}

	@Override
	public Optional<Lesson> byNumber(LessonNumber number) {

		Collection<Lesson> lessons = parse(l -> l.number().equals(number));

		if (lessons.isEmpty()) {
			return Optional.empty();
		}

		if (lessons.size() > 1) {
			throw new IllegalStateException("More than one lesson with number " + number.value() + " has been found.");
		}

		Lesson lesson = lessons.iterator().next();
		return Optional.of(lesson);
	}

	private Collection<Lesson> parse(Predicate<Lesson> predicate) {

		try {
			SAXParser parser = factory.newSAXParser();

			try (InputStream input = source.get()) {
				XmlLessonParserHandler handler = new XmlLessonParserHandler(predicate);
				parser.parse(input, handler);
				return handler.lessons();
			}
		} catch (SAXException | IOException | ParserConfigurationException exc) {
			throw new RuntimeException(exc);
		}
	}

	//--------------------------------------------------------------------------------------------------------
	//XML PARSER HANDLER
	//--------------------------------------------------------------------------------------------------------

	private static final class XmlLessonParserHandler extends DefaultHandler {

		private static final String LESSON_ELEMENT = "lesson";
		private static final String INSTRUCTIONS_ELEMENT = "instructions";
		private static final String TEXT_ELEMENT = "text";
		private static final String VALIDATION_ELEMENT = "validation";
		private static final String TYPO_ELEMENT = "typo";

		private final Predicate<Lesson> lessonPredicate;

		private LessonNumber number;
		private boolean inInstructions;
		private Instructions instructions;
		private boolean inText;
		private Pattern pattern;
		private boolean inValidation;
		private TyposBasedProgressRule.Builder typosBasedRuleBuilder;

		private final Collection<Lesson> lessons = new ArrayList<>();

		public XmlLessonParserHandler(Predicate<Lesson> lessonPredicate) {
			this.lessonPredicate = lessonPredicate;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			switch (qName) {
				case LESSON_ELEMENT:
					int numberValue = Integer.parseInt(attributes.getValue("number"));
					number = new LessonNumber(numberValue);
					break;

				case INSTRUCTIONS_ELEMENT:
					inInstructions = true;
					break;

				case TEXT_ELEMENT:
					inText = true;
					break;

				case VALIDATION_ELEMENT:
					inValidation = true;
					typosBasedRuleBuilder = TyposBasedProgressRule.newRule();
					break;

				case TYPO_ELEMENT:
					LessonNumber lesson = new LessonNumber(Integer.parseInt(attributes.getValue("lesson")));
					String equals = attributes.getValue("equals");
					String greaterThan = attributes.getValue("greaterThan");

					if (equals != null) {
						int equalsNumber = Integer.parseInt(equals);
						typosBasedRuleBuilder.typosEqualTo(equalsNumber, lesson);
					}

					if (greaterThan != null) {
						int greaterThanNumber = Integer.parseInt(greaterThan);
						typosBasedRuleBuilder.typosGreaterThan(greaterThanNumber, lesson);
					}
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			switch (qName) {
				case LESSON_ELEMENT:
					buildLesson().ifPresent(lessons::add);
					number = null;
					instructions = null;
					pattern = null;
					typosBasedRuleBuilder = null;
					break;

				case INSTRUCTIONS_ELEMENT:
					inInstructions = false;
					break;

				case TEXT_ELEMENT:
					inText = false;
					break;

				case VALIDATION_ELEMENT:
					inValidation = false;
					break;
			}
		}

		private Optional<Lesson> buildLesson() {
			Lesson lesson = new Lesson(number, instructions, pattern, typosBasedRuleBuilder.get());

			if (lessonPredicate.test(lesson)) {
				return Optional.of(lesson);
			}

			return Optional.empty();
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (inInstructions) {
				instructions = new Instructions(new String(ch, start, length));
			}

			if (inText) {
				pattern = new Pattern(new String(ch, start, length));
			}
		}


		Collection<Lesson> lessons() {
			return lessons;
		}
	}
}
