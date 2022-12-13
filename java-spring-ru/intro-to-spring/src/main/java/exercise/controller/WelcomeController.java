package exercise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring";
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }
}
