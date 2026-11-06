import java.time.LocalDateTime;

/**
 * Represents one employee attendance transaction.
 */
public class AttendanceRecord {
  private final String employeeId;
  private final LocalDateTime timeIn;
  private LocalDateTime timeOut;

  /**
   * Creates an attendance record with no time-out.
   *
   * @param employeeId the employee's identifier
   * @param timeIn the date and time the employee started work
   */
  public AttendanceRecord(String employeeId, LocalDateTime timeIn) {
    this.employeeId = employeeId;
    this.timeIn = timeIn;
    this.timeOut = null;
  }

  /**
   * Returns the employee identifier for this record.
   *
   * @return the employee identifier
   */
  public String getEmployeeId() {
    return employeeId;
  }

  /**
   * Returns the time-in date and time.
   *
   * @return the time-in date and time
   */
  public LocalDateTime getTimeIn() {
    return timeIn;
  }

  /**
   * Returns the time-out date and time, or null if the record is active.
   *
   * @return the time-out date and time, or null
   */
  public LocalDateTime getTimeOut() {
    return timeOut;
  }

  /**
   * Sets the time-out date and time.
   *
   * @param timeOut the time-out date and time
   */
  public void setTimeOut(LocalDateTime timeOut) {
    this.timeOut = timeOut;
  }
}
