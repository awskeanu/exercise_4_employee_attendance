/**
 * Represents an employee in the attendance system.
 */
public class Employee {
  private final String employeeId;
  private final String name;
  private final String department;

  /**
   * Creates an employee with the specified details.
   *
   * @param employeeId the employee's unique identifier
   * @param name the employee's name
   * @param department the employee's department
   */
  public Employee(String employeeId, String name, String department) {
    this.employeeId = employeeId;
    this.name = name;
    this.department = department;
  }

  /**
   * Returns the employee's identifier.
   *
   * @return the employee identifier
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Returns the employee's name.
   *
   * @return the employee name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the employee's department.
   *
   * @return the employee department
   */
  public String getDepartment() {
    return department;
  }
}
