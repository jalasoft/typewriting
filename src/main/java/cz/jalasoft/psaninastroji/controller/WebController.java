package cz.jalasoft.psaninastroji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
@Controller
public class WebController {

    @GetMapping("/static/index.html")
    public String hello() {
        return "index";
    }
}
