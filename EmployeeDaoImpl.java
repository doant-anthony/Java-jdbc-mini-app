import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();

    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (name, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            int count = preparedStatement.executeUpdate();
            if (count > 0)
                System.out.println("Employee saved");
            else
                System.out.println("Oops! Something went wrong, please try again");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3, employee.getId());
            int count = preparedStatement.executeUpdate();
            if (count > 0)
                System.out.println("Employee updated");
            else
                System.out.println("Oops! Employee not found or something went wrong");
        }
    }

    @Override
    public void deleteEmployee(int employeeId) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, employeeId);
            int count = preparedStatement.executeUpdate();
            if (count > 0)
                System.out.println("Employee deleted");
            else
                System.out.println("Oops! Employee not found or something went wrong");
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    employees.add(new Employee(id, name, email));
                }
            }
        } return employees;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT id, name, email FROM employee WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int employeeId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    return new Employee(employeeId, name, email);
                }
            }
        }
        return null;
    }
}