package cz.jalasoft.psaninastroji.domain.model.lesson;

/**
 * @author Jan Lastovicka
 * @since 11/10/2019
 */
public final class LessonNumber {

    private final int value;

    public LessonNumber(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Lesson number must be a positive number.");
        }
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof LessonNumber)) {
            return false;
        }

        LessonNumber that = (LessonNumber) obj;

        return this.value == that.value;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + Integer.hashCode(value);

        return result;
    }

    @Override
    public String toString() {
        return "LessonNumber[" + value + "]";
    }
}
