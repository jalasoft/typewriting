package cz.jalasoft.psaninastroji.domain.model.lesson;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
public interface LessonRepository {

    Lesson byNumber(int number);
}
