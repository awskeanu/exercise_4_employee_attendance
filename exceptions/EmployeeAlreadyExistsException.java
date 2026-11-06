package exceptions;

/**
 * Signals that an employee identifier is already registered.
 */
public class EmployeeAlreadyExistsException extends Exception {
  /**
   * Creates an exception with a descriptive message.
   *
   * @param message the exception message
   */
  public EmployeeAlreadyExistsException(String message) {
    super(message);
  }
}
