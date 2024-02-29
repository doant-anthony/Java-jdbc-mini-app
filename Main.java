import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        EmployeeDao employeeDao = EmployeeDaoFactory.getEmployeeDao();
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            System.out.println("******************************");
            System.out.println("Select from the options below");
            System.out.println("******************************");
            System.out.println("PRESS 1: Add Employee");
            System.out.println("PRESS 2: Update Employee");
            System.out.println("PRESS 3: Delete Employee");
            System.out.println("PRESS 4: Get All Employee");
            System.out.println("PRESS 5: Get Employee by Id");
            System.out.println("PRESS 6: Exit");

            int input = scanner.nextInt();
            switch (input) {
                case 1: {
                    //add employee
                    System.out.println("Enter Name: ");
                    String name = scanner.next();
                    System.out.println("Enter Email: ");
                    String email = scanner.next();
                    Employee employee = new Employee();
                    employee.setName(name);
                    employee.setEmail(email);
                    employeeDao.addEmployee(employee);
                    break;
                }
                case 2: {
                    //Update Employee
                    System.out.println("Enter Employee ID to update: ");
                    int id = scanner.nextInt();
                    System.out.println("Enter new Name: ");
                    String newName = scanner.next();
                    System.out.println("Enter new Email: ");
                    String newEmail = scanner.next();
                    Employee updatedEmployee = new Employee();
                    updatedEmployee.setId(id);
                    updatedEmployee.setName(newName);
                    updatedEmployee.setEmail(newEmail);
                    employeeDao.addEmployee(updatedEmployee);
                    System.out.println("Employee added successfully!");
                    break;
                }
                case 3: {
                    //Delete Employees
                    System.out.println("Enter Employee ID to delete: ");
                    int id = scanner.nextInt();
                    employeeDao.deleteEmployee(id);
                    System.out.println("Employee deleted successfully!");
                    break;
                }
                case 4: {
                    // Get All Employees
                    List<Employee> employees = employeeDao.getEmployees();
                    System.out.println("List of Employees:" + employees);
                    for (Employee emp : employees) {
                        System.out.println(emp);
                    }
                    break;
                }
                case 5: {
                    //Get Employee by ID
                    System.out.println("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    Employee employee = employeeDao.getEmployeeById(id);
                    if (employee != null) {
                        System.out.println("Employee details: " + employee);
                    } else {
                        System.out.println("Employee not found with ID: " + id);
                    }
                    break;
                }
                case 6: {
                    //Exit
                    System.out.println("Thank you");
                    System.out.println("Exiting...");
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("Please Choose between 1 - 6");
                }

            }
        }
    }
}