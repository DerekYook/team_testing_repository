package soloToDoApp.soloToDoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
//@AllArgsConstructor
public class TodoResponseDto {
    private int id;
    private String title;
    private int todo_order;
    private boolean completed;
}
