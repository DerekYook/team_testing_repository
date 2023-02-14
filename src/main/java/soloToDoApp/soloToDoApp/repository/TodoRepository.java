package soloToDoApp.soloToDoApp.repository;

import org.springframework.data.repository.CrudRepository;
import soloToDoApp.soloToDoApp.entity.Todo;

import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
    Optional<Todo> findById(int id);
}
