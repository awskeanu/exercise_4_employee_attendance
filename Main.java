import exceptions.EmployeeAlreadyExistsException;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidAttendanceException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Demonstrates employee registration and attendance management.
 */
public class Main {
  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  /**
   * Runs the attendance management demonstration.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    AttendanceManager manager = new AttendanceManager();

    System.out.println("1. Register Employees:");
    registerAndDisplay(manager, new Employee("E001", "Riri Williams", "IT"));
    registerAndDisplay(manager, new Employee("E002", "Nick Fury", "HR"));

    System.out.println("\n2. Register another employee:");
    registerAndDisplay(manager, new Employee("E001", "Thor Son of Odin", "Electrical"));

    System.out.println("\n3. Search for employee ID E001:");
    displayEmployee(manager, "E001");

    System.out.println("\n4. Search for employee ID E999:");
    displayEmployee(manager, "E999");

    System.out.println("\n5. Record a time-in for employee E001:");
    recordTimeIn(manager, "E001");

    System.out.println("\n6. Record another time-in for employee E001:");
    recordTimeIn(manager, "E001");

    System.out.println("\n7. Record a time-out for employee E002:");
    recordTimeOut(manager, "E002");

    System.out.println("\n8. Record a time-out for employee E001:");
    recordTimeOut(manager, "E001");

    System.out.println("\n9. Record another time-in for employee E001:");
    recordTimeIn(manager, "E001");

    System.out.println("\n10. Completed attendance records for employee E001:");
    displayCompletedAttendanceRecords(manager, "E001");
  }

  /**
   * Registers an employee and displays the result.
   *
   * @param manager the attendance manager
   * @param employee the employee to register
   */
  private static void registerAndDisplay(AttendanceManager manager, Employee employee) {
    System.out.println(employee.getEmployeeId() + " " + employee.getName()
        + " " + employee.getDepartment());
    try {
      manager.registerEmployee(employee);
      System.out.println("The employee is successfully registered.");
    } catch (EmployeeAlreadyExistsException exception) {
      System.out.println(exception.getMessage());
    }
  }

  /**
   * Finds and displays an employee, handling an unknown identifier.
   *
   * @param manager the attendance manager
   * @param employeeId the employee identifier to find
   */
  private static void displayEmployee(AttendanceManager manager, String employeeId) {
    try {
      Employee employee = manager.findEmployeeById(employeeId);
      System.out.println("ID: " + employee.getEmployeeId());
      System.out.println("Name: " + employee.getName());
      System.out.println("Department: " + employee.getDepartment());
    } catch (EmployeeNotFoundException exception) {
      System.out.println(exception.getMessage());
    }
  }

  /**
   * Records an employee's time-in and handles attendance errors.
   *
   * @param manager the attendance manager
   * @param employeeId the employee identifier
   */
  private static void recordTimeIn(AttendanceManager manager, String employeeId) {
    try {
      manager.timeIn(employeeId);
      System.out.println("Time-in recorded.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException exception) {
      System.out.println(exception.getMessage());
    }
  }

  /**
   * Records an employee's time-out and handles attendance errors.
   *
   * @param manager the attendance manager
   * @param employeeId the employee identifier
   */
  private static void recordTimeOut(AttendanceManager manager, String employeeId) {
    try {
      manager.timeOut(employeeId);
      System.out.println("Time-out recorded.");
    } catch (EmployeeNotFoundException | InvalidAttendanceException exception) {
      System.out.println(exception.getMessage());
    }
  }

  /**
   * Retrieves and displays an employee's completed attendance records.
   *
   * @param manager the attendance manager
   * @param employeeId the employee identifier
   */
  private static void displayCompletedAttendanceRecords(
      AttendanceManager manager, String employeeId) {
    try {
      List<AttendanceRecord> records = manager.getCompletedAttendanceRecords(employeeId);
      for (AttendanceRecord record : records) {
        System.out.println("Employee ID: " + record.getEmployeeId());
        System.out.println("Time-in: " + record.getTimeIn().format(DATE_TIME_FORMATTER));
        System.out.println("Time-out: " + record.getTimeOut().format(DATE_TIME_FORMATTER));
      }
    } catch (EmployeeNotFoundException exception) {
      System.out.println(exception.getMessage());
    }
  }
}
