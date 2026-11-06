import exceptions.EmployeeAlreadyExistsException;
import exceptions.EmployeeNotFoundException;
import exceptions.InvalidAttendanceException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages registered employees and their attendance records.
 */
public class AttendanceManager {
  private final List<Employee> employees;
  private final List<AttendanceRecord> attendanceRecords;

  /**
   * Creates an attendance manager with empty employee and attendance lists.
   */
  public AttendanceManager() {
    employees = new ArrayList<>();
    attendanceRecords = new ArrayList<>();
  }

  /**
   * Registers an employee if their identifier is not already in use.
   *
   * @param employee the employee to register
   * @throws EmployeeAlreadyExistsException if the employee identifier is registered
   */
  public void registerEmployee(Employee employee) throws EmployeeAlreadyExistsException {
    for (Employee registeredEmployee : employees) {
      if (registeredEmployee.getEmployeeId().equals(employee.getEmployeeId())) {
        throw new EmployeeAlreadyExistsException(
            "Employee ID " + employee.getEmployeeId() + " is already registered.");
      }
    }
    employees.add(employee);
  }

  /**
   * Finds a registered employee by identifier.
   *
   * @param employeeId the employee identifier to find
   * @return the matching employee
   * @throws EmployeeNotFoundException if the identifier is not registered
   */
  public Employee findEmployeeById(String employeeId) throws EmployeeNotFoundException {
    for (Employee employee : employees) {
      if (employee.getEmployeeId().equals(employeeId)) {
        return employee;
      }
    }
    throw new EmployeeNotFoundException("Employee ID " + employeeId + " was not found.");
  }

  /**
   * Records the employee's time-in if they have no active attendance record.
   *
   * @param employeeId the employee identifier
   * @throws EmployeeNotFoundException if the identifier is not registered
   * @throws InvalidAttendanceException if the employee already has an active record
   */
  public void timeIn(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);
    for (AttendanceRecord record : attendanceRecords) {
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        throw new InvalidAttendanceException(
            "Employee ID " + employeeId + " already has an active attendance record.");
      }
    }
    attendanceRecords.add(new AttendanceRecord(employeeId, LocalDateTime.now()));
  }

  /**
   * Records the employee's time-out if they have an active attendance record.
   *
   * @param employeeId the employee identifier
   * @throws EmployeeNotFoundException if the identifier is not registered
   * @throws InvalidAttendanceException if the employee has no active record
   */
  public void timeOut(String employeeId)
      throws EmployeeNotFoundException, InvalidAttendanceException {
    findEmployeeById(employeeId);
    for (int index = attendanceRecords.size() - 1; index >= 0; index--) {
      AttendanceRecord record = attendanceRecords.get(index);
      if (record.getEmployeeId().equals(employeeId) && record.getTimeOut() == null) {
        record.setTimeOut(LocalDateTime.now());
        return;
      }
    }
    throw new InvalidAttendanceException(
        "Employee ID " + employeeId + " does not have an active attendance record.");
  }

  /**
   * Returns completed attendance records for a registered employee.
   *
   * @param employeeId the employee identifier
   * @return the employee's completed attendance records
   * @throws EmployeeNotFoundException if the identifier is not registered
   */
  public List<AttendanceRecord> getCompletedAttendanceRecords(String employeeId)
      throws EmployeeNotFoundException {
    findEmployeeById(employeeId);
    return attendanceRecords.stream()
        .filter(record -> record.getEmployeeId().equals(employeeId)
            && record.getTimeOut() != null)
        .collect(Collectors.toList());
  }
}
