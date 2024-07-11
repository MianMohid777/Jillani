package Assignment.Persistance.DAO.Implementation;

import Assignment.Application.Model.Skill;
import Assignment.Persistance.Connection.DB_Connection;
import Assignment.Persistance.DAO.Interface.Skill_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TaskSkillDao implements Skill_DAO {

    private ResultSet rs;

    private void getAll() throws SQLException {
        Connection conn = DB_Connection.getConnection();
        String query = "SELECT * FROM Task_Skill";
        PreparedStatement statement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        rs = statement.executeQuery();

    }

    public TaskSkillDao() throws SQLException {
        getAll();
    }
    @Override
    public void saveSkill(Long id, Long skId, int exp) throws SQLException {

        rs.moveToInsertRow();
        rs.updateString(2, String.valueOf(id));
        rs.updateString(3,String.valueOf(skId));
        rs.updateString(4,String.valueOf(exp));

        rs.insertRow();

        rs.moveToCurrentRow();
    }

    @Override
    public void delete(Long id,Long skId) throws SQLException {

        if(rs != null) {
            rs.absolute(0);
            while(rs.next() && (rs.getInt(2) != id || rs.getInt(3) != skId));

            rs.deleteRow();

            rs.moveToCurrentRow();
        }

    }

    @Override
    public void update(Long Id,Long skId,int exp) throws SQLException {

        rs.absolute(0);
        while(rs.next() && (rs.getInt(2) != Id || rs.getInt(3) != skId));


        rs.updateString(4,String.valueOf(exp));
        rs.updateRow();
        rs.moveToCurrentRow();
    }

    @Override
    public List<Skill> getSkills(Long id) throws SQLException {
        Connection conn = DB_Connection.getConnection();
        String query = "SELECT * FROM Task_Skill WHERE taskID = ?";
        PreparedStatement statement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        statement.setString(1,String.valueOf(id));
        ResultSet set = statement.executeQuery();


        List<Skill> skills = new LinkedList<>();

        String fetch = "SELECT * FROM Skill";
        PreparedStatement statement2 = conn.prepareStatement(fetch,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet skillSet = statement2.executeQuery();

        if(skillSet.next()) {
            while (set.next()) {

                int skId = set.getInt(3);
                Skill sk = new Skill();

                while (skillSet.getInt(1) != skId && skillSet.next()) ;

                sk.setSkillName(skillSet.getString(2));
                sk.setExp(set.getInt(4));
                skills.add(sk);
                skillSet.absolute(1);


            }

            return skills;
        }
        return new LinkedList<>();
    }

    @Override
    public String findBYId(Long id) throws SQLException {
        Connection conn = DB_Connection.getConnection();
        String fetch = "SELECT * FROM Skill";
        PreparedStatement statement2 = conn.prepareStatement(fetch,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet skillSet = statement2.executeQuery();

        while(skillSet.next() && skillSet.getInt(1) != id);

        return skillSet.getString(2);
    }
}
