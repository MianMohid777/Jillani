package Assignment.Persistance.DAO.Interface;

import Assignment.Application.Model.*;

import java.sql.SQLException;
import java.util.List;

public interface Task_IDAO {

    void save(Task t) throws SQLException;
    void delete(Long id) throws SQLException;

    void update(Long id, Task t) throws SQLException;

    List<Task> getAll() throws SQLException;

    Task findById(Long id) throws SQLException;
}
