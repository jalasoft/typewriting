package cz.jalasoft.psaninastroji.domain.model.lesson.excercise;

import java.time.LocalTime;

/**
 * @author Jan Lastovicka
 * @since 13/10/2019
 */
public final class KeyInput {

    private final char key;
    private final LocalTime time;

    KeyInput(char key) {
        this.key = key;
        this.time = LocalTime.now();
    }

    public char key() {
        return key;
    }

    public LocalTime time() {
        return time;
    }
}
