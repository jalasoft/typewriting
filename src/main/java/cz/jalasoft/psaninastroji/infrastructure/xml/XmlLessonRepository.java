package cz.jalasoft.psaninastroji.infrastructure.xml;

import com.thoughtworks.xstream.XStream;
import cz.jalasoft.psaninastroji.domain.model.lesson.LessonRepository;
import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
public final class XmlLessonRepository implements LessonRepository {

    private static XStream xStream = new XStream();

    static {
        xStream.alias("exercise", LessonElement.class);
        xStream.alias("text", TextElement.class);
    }

    @Override
    public Lesson byNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Excercise number must not be negative.");
        }


        return null;
    }
}
