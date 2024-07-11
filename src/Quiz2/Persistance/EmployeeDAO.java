package Quiz2.Persistance;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import Quiz2.Application.*;

public class EmployeeDAO implements IDAO<Employee>{

    @Override
    public Boolean save(Employee e) throws SQLException {

        Connection conn = DB_Connection.getConnection();
        String query = "INSERT INTO Employee(name,position,salary) VALUES(?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,e.getName());
        statement.setString(2,e.getPosition());
        statement.setDouble(3,e.getSalary());

        ResultSet set = statement.executeQuery();

        if(set.next())
        {
            e.setId(set.getInt(1));
            return true;
        }


        return false;
    }

    @Override
    public Boolean update(Employee e) throws SQLException {

        Connection conn = DB_Connection.getConnection();
        String query = "UPDATE Employee SET name = ?,position = ?,salary = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,e.getName());
        statement.setString(2,e.getPosition());
        statement.setDouble(3,e.getSalary());
        statement.setDouble(4,e.getId());

         if(statement.executeQuery() != null)
             return true;


        return false;
    }

    @Override
    public Boolean delete(Integer id) throws SQLException {
        Connection conn = DB_Connection.getConnection();
        String query = "DELETE FROM Employee Where id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1,id);

        if(statement.executeUpdate()== 1)
            return true;


        return false;

    }

    @Override
    public List<Employee> findAll() throws SQLException {
        Connection conn = DB_Connection.getConnection();
        String query = "SELECT * FROM Employee";
        PreparedStatement statement = conn.prepareStatement(query);

        List<Employee> employees = new LinkedList<>();

        ResultSet set = statement.executeQuery();

        while(set.next())
        {
            Employee e = new Employee();

            e.setId(set.getInt(1));
            e.setName(set.getString(2));
            e.setPosition(set.getString(3));
            e.setSalary(set.getDouble(4));

            employees.add(e);
        }

        return  employees;
    }

    @Override
    public Employee findById(Integer id) {
        return null;
    }


}
