package cz.jalasoft.domain.model.lesson;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
public final class Instructions {

    private String text;

    public Instructions(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}
