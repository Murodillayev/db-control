package uz.pdp.dbcontrol;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoRepository repository;
    private final UserRepository userRepository;

    public TodoController(TodoRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Map<String, String> dto) {
        Todo todo = new Todo();
        String userId = dto.get("userId");
        String title = dto.get("title");
        AuthUser authUser = userRepository.findById(userId).orElse(null);

        List<Todo> todos = authUser.getTodos() == null ? new ArrayList<>() : authUser.getTodos();
        todos.add(todo);
        authUser.setTodos(todos);
        todo.setTitle(title);
        todo.setCompleted(false);
        AuthUser user = userRepository.save(authUser);
        todo.setUser(user);
        Todo save = repository.save(todo);
        return ResponseEntity.ok(save);
    }


}
