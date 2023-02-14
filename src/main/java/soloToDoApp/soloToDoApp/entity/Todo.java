package soloToDoApp.soloToDoApp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("TODOS")
public class Todo {
    @Id
    private int id;
    private String title;
    private int todo_order;
    private boolean completed;
}
