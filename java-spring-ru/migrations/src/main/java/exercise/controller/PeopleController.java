package exercise.controller;

import exercise.config.PersonRowMapper;
import exercise.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/people")
public class PeopleController {
    private final JdbcTemplate jdbc;

    @Autowired
    public PeopleController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        String query = "insert into person (first_name, last_name) values (?, ?)";
        jdbc.update(query, person.firstname(), person.lastname());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getPeople() {
        String query = "select * from person";
        List<Person> people = jdbc.query(query, new PersonRowMapper());
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable int id) {
        String query = "select * from person where id = " + id;
        Person person = jdbc.queryForObject(query, new PersonRowMapper());
        return ResponseEntity.ok(person);
    }
}
