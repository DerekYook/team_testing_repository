package soloToDoApp.soloToDoApp.service;

import soloToDoApp.soloToDoApp.entity.Todo;
import org.springframework.stereotype.Service;
import soloToDoApp.soloToDoApp.exception.BusinessLogicException;
import soloToDoApp.soloToDoApp.exception.ExceptionCode;
import soloToDoApp.soloToDoApp.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo){
        verifyExistsId(todo.getId());

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Todo todo){
        Todo findTodo = findVerifiedTodo(todo.getId());

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodo_order())
                .ifPresent(todo_order -> findTodo.setTodo_order(todo_order));
        Optional.ofNullable(todo.isCompleted())
                .ifPresent(completed -> findTodo.setCompleted(completed));

        return todoRepository.save(findTodo);
    }

    public Todo findTodo(int id){
        return findVerifiedTodo(id);
    }

    public List<Todo> findTodos(){
        return (List<Todo>) todoRepository.findAll();
    }

    public void deleteTodo(int id){
        Todo findTodo = findVerifiedTodo(id);

        todoRepository.delete(findTodo);
    }
    public Todo findVerifiedTodo(int id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo findTodo =
                optionalTodo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findTodo;
    }
    private void verifyExistsId(int id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent())
            throw new BusinessLogicException(ExceptionCode.TODO_EXISTS);
    }
}
