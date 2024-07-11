package Quiz2.Persistance;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    Boolean save(T t) throws SQLException;

    Boolean update(T t) throws SQLException;

    Boolean delete(Integer id) throws SQLException;

     List<T> findAll() throws SQLException;

     T findById(Integer id);


}
