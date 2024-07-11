package Assignment.Persistance.DAO.Interface;

import Assignment.Application.Model.Applicant;

import java.sql.SQLException;
import java.util.List;

public interface Resource_IDAO {

    void save(Applicant a) throws SQLException;
    void delete(Long id) throws SQLException;

    void update(Long id, Applicant a) throws SQLException;

    List<Applicant> getAll() throws SQLException;

    Applicant findById(Long id) throws SQLException;
}
