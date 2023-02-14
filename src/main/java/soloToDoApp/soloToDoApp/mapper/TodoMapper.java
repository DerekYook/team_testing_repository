package soloToDoApp.soloToDoApp.mapper;

import org.mapstruct.Mapper;
import soloToDoApp.soloToDoApp.dto.TodoPatchDto;
import soloToDoApp.soloToDoApp.dto.TodoPostDto;
import soloToDoApp.soloToDoApp.dto.TodoResponseDto;
import soloToDoApp.soloToDoApp.entity.Todo;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDtoToTodo(TodoPostDto todoPostDto);
    Todo todoPatchDtoToTodo(TodoPatchDto todoPatchDto);
    TodoResponseDto todoToTodoResponseDto(Todo todo);
}
