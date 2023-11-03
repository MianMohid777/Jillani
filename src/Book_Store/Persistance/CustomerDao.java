package Book_Store.Persistance;

import Book_Store.Model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDao {

    public Customer getCustomer(Long id)
    {
        try{
            Connection conn = DB_Connection.getConnection();

            String query = "SELECT * FROM Customer WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet res = statement.executeQuery();

            if(res.next())
            {
                String c_id = res.getString("id");
                String c_name = res.getString("name");

                Customer c = new Customer();

                c.setId(Long.valueOf(c_id));
                c.setName(c_name);

                return c;
            }

           return new Customer();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
