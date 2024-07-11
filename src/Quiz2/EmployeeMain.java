package Quiz2;

import Quiz2.Presentation.EmployeeUI;

import java.sql.SQLException;

public class EmployeeMain {

   public static void main(String[] args) throws SQLException {
       EmployeeUI ui = new EmployeeUI();
       ui.setVisible(true);
   }
}
