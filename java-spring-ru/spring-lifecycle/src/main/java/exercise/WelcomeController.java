package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    private final Daytime daytime;
    private final Meal meal;

    @Autowired
    public WelcomeController(Daytime daytime, Meal meal) {
        this.daytime = daytime;
        this.meal = meal;
    }

    @GetMapping("/daytime")
    public String getDaytime() {
        return String.format("It is %s now. Enjoy your %s", daytime.getName(), meal.getMealForDaytime(daytime));
    }
}
