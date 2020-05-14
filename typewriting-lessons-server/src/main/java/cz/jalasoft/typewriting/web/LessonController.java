package cz.jalasoft.typewriting.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonController {

    @GetMapping("/lesson")
    public ResponseEntity<LessonResource> getLesson(@RequestParam("number") int number) {

        LessonResource resource = new LessonResource("abcdef dfre ddfsd");
        return ResponseEntity.ok(resource);
    }
}
