package Assignment.Persistance.DAO.Interface;

import Assignment.Application.Model.Skill;

import java.sql.SQLException;
import java.util.List;

public interface Skill_DAO {

    void saveSkill(Long id,Long skId, int exp) throws SQLException;

    void delete(Long id,Long skId) throws SQLException;

    void update(Long Id,Long SkId,int exp) throws SQLException;

    List<Skill> getSkills(Long id) throws SQLException;

    String findBYId(Long id) throws SQLException;

}
