package cz.jalasoft.psaninastroji.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.jalasoft.psaninastroji.domain.model.lesson.Lesson;

/**
 * @author Jan Lastovicka
 * @since 15/10/2019
 */
public class LessonResource {

    private final int number;
    private final String instructions;
    private final String pattern;

    public LessonResource(int number, String instructions, String pattern) {
        this.number = number;
        this.instructions = instructions;
        this.pattern = pattern;
    }

    public LessonResource(Lesson lesson) {
        this(lesson.number(), lesson.instructions().text(), lesson.pattern().value());
    }

    @JsonProperty(value = "number")
    public int getNumber() {
        return number;
    }

    @JsonProperty(value = "instructions")
    public String getInstructions() {
        return instructions;
    }

    @JsonProperty(value = "pattern")
    public String getPattern() {
        return pattern;
    }
}
