package exceptions;

/**
 * Signals that an employee identifier is not registered.
 */
public class EmployeeNotFoundException extends Exception {
  /**
   * Creates an exception with a descriptive message.
   *
   * @param message the exception message
   */
  public EmployeeNotFoundException(String message) {
    super(message);
  }
}
