package exercise.controller;
import com.querydsl.core.types.Predicate;
import exercise.model.User;
import exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@QuerydslPredicate(root = User.class) Predicate predicate) {
        Iterable<User> users = userService.getUsers(predicate);
        return ResponseEntity.ok(users);
    }
}

