package cz.jalasoft.psaninastroji.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
@RestController
public class ExcerciseController {

    @GetMapping("/hello")
    public String hello() {
        return "Cus";
    }
}
