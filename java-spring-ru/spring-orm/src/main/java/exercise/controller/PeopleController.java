package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PersonRepository personRepository;

    @Autowired
    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id);
    }

    @GetMapping
    public Iterable<Person> getPeople() {
        return personRepository.findAll();
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        personRepository.save(person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void patchPerson(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        personRepository.save(person);
    }
}
