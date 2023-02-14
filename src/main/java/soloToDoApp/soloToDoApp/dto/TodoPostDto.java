package soloToDoApp.soloToDoApp.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class TodoPostDto {
    private String title;
    private int todo_order;
    private boolean completed;
/*
    public String getTitle() {
        return title;
    }

    public int getTodo_order() {
        return todo_order;
    }

    public boolean isCompleted() {
        return completed;
    }*/

}
