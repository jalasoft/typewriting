package cz.jalasoft.typewriting.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

	@GetMapping("/lesson.html")
	public ModelAndView index(@RequestParam("lesson") int lessonNumber) {

		Map<String, Object> model = new HashMap<>();
		model.put("lesson_number", lessonNumber);

		return new ModelAndView("lesson");
	}
}
