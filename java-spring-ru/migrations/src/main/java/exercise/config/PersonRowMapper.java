package exercise.config;

import exercise.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.builder()
                .id(rs.getInt("id"))
                .firstname(rs.getString("first_name"))
                .lastname(rs.getString("last_name"))
                .build();
    }
}
