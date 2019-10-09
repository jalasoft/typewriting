package cz.jalasoft.psaninastroji.domain.model.excercise;

/**
 * @author Jan Lastovicka
 * @since 09/10/2019
 */
public interface ExcerciseRepository {

    Excercise byNumber(int number);
}
