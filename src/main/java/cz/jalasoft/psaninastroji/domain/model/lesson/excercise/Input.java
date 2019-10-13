package cz.jalasoft.psaninastroji.domain.model.lesson.excercise;

import java.time.LocalTime;

/**
 * @author Jan Lastovicka
 * @since 13/10/2019
 */
public final class Input {

    private final char key;
    private final LocalTime time;
    private final boolean isTypo;

    Input(char key, boolean isTypo) {
        this.key = key;
        this.time = LocalTime.now();
        this.isTypo = isTypo;
    }

    public char key() {
        return key;
    }

    public LocalTime time() {
        return time;
    }

    public boolean isTypo() {
        return isTypo;
    }
}
