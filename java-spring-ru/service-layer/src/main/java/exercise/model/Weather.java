package exercise.model;


public record Weather(
        String name,
        String temperature,
        String cloudy,
        String wind,
        String humidity
) {
}
