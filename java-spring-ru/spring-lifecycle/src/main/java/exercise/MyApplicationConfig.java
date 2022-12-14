package exercise;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationConfig  {

    @Bean
    public Daytime getDateTime() {
        ZonedDateTime now = ZonedDateTime.now();
        int hour = now.getHour();

        if (hour >= 6 && hour < 12) {
            return new Morning();
        }

        if (hour >= 12 && hour < 18) {
            return new Day();
        }

        if (hour >= 18 && hour < 23) {
            return new Evening();
        }

        return new Night();
    }

}
