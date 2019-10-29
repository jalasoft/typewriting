package cz.jalasoft.typewriting.web;

public class LessonNotFoundException extends RuntimeException {

	private final int lessonNumber;

	public LessonNotFoundException(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}
}
