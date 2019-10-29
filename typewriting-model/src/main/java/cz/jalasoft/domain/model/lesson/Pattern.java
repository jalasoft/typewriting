package cz.jalasoft.domain.model.lesson;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
public final class Pattern {

    private final String value;

    public Pattern(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Pattern must not be null or empty.");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    public int length() {
        return value.length();
    }

    public char at(int index) {
        if (index < 0 || value.length() <= index) {
            throw new IllegalArgumentException("Index must be in [" + 0 + ", " + (length() - 1) + "]");
        }
        return this.value.charAt(index);
    }

    @Override
    public String toString() {
        return "Pattern[length=" + length() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pattern)) {
            return false;
        }

        Pattern that = (Pattern) obj;

        return this.value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + value.hashCode();

        return result;
    }
}
