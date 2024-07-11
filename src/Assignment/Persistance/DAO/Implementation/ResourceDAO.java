package Assignment.Persistance.DAO.Implementation;

import Assignment.Application.Model.*;
import Assignment.Persistance.Connection.DB_Connection;
import Assignment.Persistance.DAO.Interface.Resource_IDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ResourceDAO implements Resource_IDAO {

    private ResultSet rs;

    private void fillSet() throws SQLException {
        Connection conn = DB_Connection.getConnection();
        String query = "SELECT * FROM Applicant";
        PreparedStatement statement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        rs = statement.executeQuery();

    }
   public ResourceDAO() throws SQLException {
       fillSet();
   }
    @Override
    public void save(Applicant a) throws SQLException {

        Connection conn = DB_Connection.getConnection();

        String query = "INSERT INTO Applicant(name) VALUES(?)";
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, a.getName());
        statement.executeUpdate();

        ResultSet keys = statement.getGeneratedKeys();

        if(keys.next())
        {
            a.setId(keys.getInt(1));
        }

    }



    @Override
    public void delete(Long id) throws SQLException {

        /*Connection conn = DB_Connection.getConnection();

        String query = "DELETE FROM Applicant WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        statement.executeUpdate();*/

       if( rs.absolute(Math.toIntExact(id))) {
           rs.deleteRow();
           rs.moveToCurrentRow();
       }
    }

    @Override
    public void update(Long id, Applicant a) throws SQLException {

        if(rs.absolute(Math.toIntExact(id))) {

            rs.updateString(2, a.getName());

            rs.updateRow();

            rs.moveToCurrentRow();
        }

    }

    @Override
    public List<Applicant> getAll() throws SQLException {

        Connection conn = DB_Connection.getConnection();

        String query = "SELECT * FROM Applicant";
        PreparedStatement statement = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = statement.executeQuery();

        List<Applicant> applicantList = new LinkedList<>();


        while(rs.next())
        {
            Applicant a = new Applicant();

            a.setId(rs.getInt(1));
            a.setName(rs.getString(2));

            applicantList.add(a);
        }



        return applicantList;
    }
    @Override
     public Applicant findById(Long id) throws SQLException {
        /*Connection conn = DB_Connection.getConnection();

        String query = "SELECT * FROM Applicant WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, String.valueOf(id));
        ResultSet rs = statement.executeQuery();*/



        Applicant a = new Applicant();

        rs.absolute(Math.toIntExact(id));

        a.setId(rs.getInt(1));
        a.setName(rs.getString(2));

        return a;
    }

    public static void main(String[]args) throws SQLException {
        ResourceDAO dao = new ResourceDAO();


        for(Applicant a: dao.getAll())
        {
            System.out.println(a.getName());
        }

        Applicant x  =new Applicant();
        x.setName("Mian");

        dao.update(1L,x);

        System.out.println("\"Found\" " + dao.findById(1L).getName());


    }
}
