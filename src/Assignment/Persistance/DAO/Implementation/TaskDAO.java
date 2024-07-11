package Assignment.Persistance.DAO.Implementation;

import Assignment.Application.Model.Applicant;
import Assignment.Application.Model.Task;
import Assignment.Persistance.Connection.DB_Connection;
import Assignment.Persistance.DAO.Interface.Task_IDAO;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TaskDAO implements Task_IDAO {

    private ResultSet rs;

    private void fillSet() throws SQLException {
        Connection conn = DB_Connection.getConnection();
        String query = "SELECT * FROM Task";
        PreparedStatement statement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        rs = statement.executeQuery();

    }
    public TaskDAO() throws SQLException {
        fillSet();
    }
    @Override
    public void save(Task t) throws SQLException {

        Connection conn = DB_Connection.getConnection();

        String query = "INSERT INTO Task(name) VALUES(?)";
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, t.getTaskName());
        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();

        if (keys.next()) {
            t.setId(keys.getInt(1));
        }

    }

    @Override
    public void delete(Long id) throws SQLException {

        if( rs.absolute(Math.toIntExact(id))) {
            rs.deleteRow();
            rs.moveToCurrentRow();
        }
    }

    @Override
    public void update(Long id, Task t) throws SQLException {


        if(rs.absolute(Math.toIntExact(id))) {

            rs.updateString(2, t.getTaskName());

            rs.updateRow();

            rs.moveToCurrentRow();
        }
    }

    @Override
    public List<Task> getAll() throws SQLException {

        Connection conn = DB_Connection.getConnection();

        String query = "SELECT * FROM Task";
        PreparedStatement statement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = statement.executeQuery();

        List<Task> taskList = new LinkedList<>();


        while(rs.next())
        {
           Task t = new Task();

            t.setId(rs.getInt(1));
            t.setTaskName(rs.getString(2));

            taskList.add(t);
        }

        return taskList;
    }

    @Override
    public Task findById(Long id) throws SQLException {


        Task t = new Task();

        rs.absolute(Math.toIntExact(id));

        t.setId(rs.getInt(1));
        t.setTaskName(rs.getString(2));

        return t;
    }
}
