package exercise;

import exercise.daytimes.Daytime;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(value = "meal")
public class Meal {

    @PostConstruct
    public void init() {
        System.out.println("Init bean meal");
    }

    public String getMealForDaytime(Daytime daytime) {

        return switch (daytime.getName()) {
            case "morning" -> "breakfast";
            case "day" -> "lunch";
            case "evening" -> "dinner";
            default -> "nothing :)";
        };
    }
}
