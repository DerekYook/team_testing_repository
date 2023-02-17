package soloToDoApp.soloToDoApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import soloToDoApp.soloToDoApp.dto.TodoPatchDto;
import soloToDoApp.soloToDoApp.dto.TodoPostDto;
import soloToDoApp.soloToDoApp.dto.TodoResponseDto;
import soloToDoApp.soloToDoApp.entity.Todo;
import soloToDoApp.soloToDoApp.mapper.TodoMapper;
import soloToDoApp.soloToDoApp.service.TodoService;
import soloToDoApp.soloToDoApp.utils.UriCreator;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping//("/v1/todos")
@Validated
public class TodoController {
    private final static String TODO_DEFAULT_URL = "";
    private final TodoService todoService;
    private final TodoMapper mapper;

    public TodoController(TodoService todoService, TodoMapper mapper){
        this.todoService = todoService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String welcome(){
        return "To-do Application!";
    }

    @PostMapping
    public ResponseEntity postTodo(@Valid @RequestBody TodoPostDto todoDto){
        Todo todo = mapper.todoPostDtoToTodo(todoDto);
        Todo resultTodo = todoService.createTodo(todo);
        //todo list

        URI location = UriCreator.createUri(TODO_DEFAULT_URL,resultTodo.getId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive int id,
                                      @Valid @RequestBody TodoPatchDto todoPatchDto){

        todoPatchDto.setId(id);
        Todo response = todoService.updateTodo(mapper.todoPatchDtoToTodo(todoPatchDto));
        return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive int id){
        Todo response = todoService.findTodo(id);
        return new ResponseEntity<>(mapper.todoToTodoResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(){

        List<Todo> Todos = todoService.findTodos();
        List<TodoResponseDto> response =
                Todos.stream()
                        .map(Todo -> mapper.todoToTodoResponseDto(Todo))
                        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive int id){

        todoService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
