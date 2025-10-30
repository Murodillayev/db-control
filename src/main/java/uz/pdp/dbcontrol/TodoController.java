package uz.pdp.dbcontrol;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository repository;

    //    @SchemaMapping(typeName = "Mutation",value = "createTodo")
    @MutationMapping(value = "createTodo")
    public TodoDto create(@Argument TodoCreateDto dto) {
        Todo todo = new Todo();
        todo.setDescription(dto.getDescription());
        todo.setTitle(dto.getTitle());
        todo.setCompleted(false);
        Todo save = repository.save(todo);
        return TodoDto.builder()
                .title(save.getTitle())
                .description(save.getDescription())
                .completed(save.getCompleted())
                .id(save.getId())
                .build();
    }

    @MutationMapping(value = "deleteTodo")
    public Boolean delete(@Argument Integer id) {
        repository.deleteById(id);
        return true;
    }

    @QueryMapping(value = "getTodo")
    public TodoDto get(@Argument Integer id) {
        Todo todo = repository.findById(id).orElse(null);

        return todo == null ? null : TodoDto.builder()
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.getCompleted())
                .id(todo.getId())
                .build();
    }

    @QueryMapping(value = "getAllTodo")
    public List<TodoDto> getAll() {
        List<Todo> all = repository.findAll();
        return all.stream().map(
                todo -> TodoDto.builder()
                        .title(todo.getTitle())
                        .description(todo.getDescription())
                        .completed(todo.getCompleted())
                        .id(todo.getId())
                        .build()
        ).collect(Collectors.toList());
    }

}
