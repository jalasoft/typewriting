package cz.jalasoft.typewriting.web;

import cz.jalasoft.domain.model.lesson.Lesson;
import cz.jalasoft.domain.model.lesson.LessonNumber;
import cz.jalasoft.typewriting.domain.model.lesson.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class WebController {

	private final LessonRepository lessonRepository;

	@Autowired
	public WebController(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	@GetMapping("/lesson.html")
	public ModelAndView index(@RequestParam("lesson") int lessonNumber) {

		Optional<Lesson> maybeLesson = lessonRepository.byNumber(new LessonNumber(lessonNumber));

		if (!maybeLesson.isPresent()) {
			throw new LessonNotFoundException(lessonNumber);
		}

		Lesson lesson = maybeLesson.get();

		Map<String, Object> model = new HashMap<>();
		model.put("lesson_number", lessonNumber);
		model.put("lesson_instructions", lesson.instructions().text());
		model.put("lesson_pattern", lesson.pattern().value());

		return new ModelAndView("lesson", model);
	}

	@ExceptionHandler(LessonNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(LessonNotFoundException exc) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson " + exc.getLessonNumber() + " not found.");
	}
}
