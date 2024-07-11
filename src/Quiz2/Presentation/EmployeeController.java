package Quiz2.Presentation;

import Quiz2.Application.Employee;
import Quiz2.Persistance.EmployeeDAO;
import Quiz2.Persistance.IDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class EmployeeController {

    private HashMap<Integer, Employee> employeeMap;

   static private EmployeeDAO employeeDAO;

    public EmployeeController() throws SQLException {
        employeeMap = new HashMap<>();
        employeeDAO = new EmployeeDAO();

        List<Employee> employeeList = employeeDAO.findAll();

        for(Employee e: employeeList)
        {
            employeeMap.put(e.getId(),e);
        }
    }

    public void save(String name, String pos, Double salary) throws SQLException {
        Employee e = new Employee();
        e.setName(name);
        e.setPosition(pos);
        e.setSalary(salary);

        if(employeeDAO.save(e))
        {
            employeeMap.put(e.getId(),e);

        }
    }

    public void delete(Integer id) throws SQLException {
        if(employeeDAO.delete(id))
        {
            employeeMap.remove(id);
        }
    }
    public void update(String name, String pos, Double salary) throws SQLException {
        Employee e = new Employee();
        e.setName(name);
        e.setPosition(pos);
        e.setSalary(salary);

        if(employeeDAO.save(e))
        {
            employeeMap.put(e.getId(),e);

        }
    }

    public HashMap<Integer, Employee> getEmployeeMap() {
        return employeeMap;
    }
}
