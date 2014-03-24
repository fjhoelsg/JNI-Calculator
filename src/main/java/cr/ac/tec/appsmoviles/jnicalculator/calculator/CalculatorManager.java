package cr.ac.tec.appsmoviles.jnicalculator.calculator;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.operation.BinaryArithmeticOperation;
import cr.ac.tec.appsmoviles.jnicalculator.calculator.operation.NoOperation;
import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;

/**
 * Manages the low level functions for a calculator
 */
public class CalculatorManager {

  /**
   * The operation that is set when nothing should be executed
   */
  private static final BinaryArithmeticOperation NO_OPERATION = new NoOperation();

  /**
   * The processor that executes the arithmetic operations
   */
  private ArithmeticProcessor arithmeticProcessor;

  /**
   * The first operand of an arithmetic operation
   */
  private double firstOperand;

  /**
   * The operation that is scheduled to be executed
   */
  private BinaryArithmeticOperation operation;

  /**
   * The cached result of the last arithmetic operation
   */
  private double result;

  /**
   * Whether the cached result is up to date or not
   */
  private boolean resultIsCached;

  /**
   * The second operand for an arithmetic operation
   */
  private double secondOperand;

  /**
   * Creates a CalculatorManager
   *
   * @param arithmeticProcessor the processor that should execute the arithmetic operations
   */
  public CalculatorManager(ArithmeticProcessor arithmeticProcessor) {
    this.arithmeticProcessor = arithmeticProcessor;
    operation = NO_OPERATION;
    resultIsCached = false;
  }

  /**
   * Calculates teh square root of a number
   *
   * @param radicand the operand for the square root
   * @return the square root of the radicand
   */
  public double calculateSquareRoot(double radicand) {
    return arithmeticProcessor.nthRoot(radicand, 2);
  }

  /**
   * Clears the state of the calculator
   */
  public void clearAll() {
    setOperation(NO_OPERATION);
  }

  /**
   * Executes the scheduled arithmetic operation with the operands that have been provided
   *
   * @return the result of the arithmetic operation
   *
   * @throws ArithmeticException if the arithmetic operation fails
   */
  public double getResult() throws ArithmeticException {
    if (resultIsCached) {
      return result;
    }
    // Throws ArithmeticError
    result = operation.operate(arithmeticProcessor, firstOperand, secondOperand);
    firstOperand = result;
    resultIsCached = true;
    setOperation(NO_OPERATION);
    return result;
  }

  /**
   * Checks whether there is a scheduled operation
   *
   * @return true if there is a scheduled operation; false otherwise
   */
  public boolean operationIsPending() {
    return operationIsSet() && !resultIsCached;
  }

  /**
   * Sets an operand for the an arithmetic operation, the position of the operand depends on
   * whether
   * there is scheduled arithmetic operation or not
   *
   * @param operand the value for the operand
   */
  public void setOperand(double operand) {
    if (operationIsSet()) {
      secondOperand = operand;
    } else {
      firstOperand = operand;
    }
    resultIsCached = false;
  }

  /**
   * Schedules an arithmetic operation to be executed when the next result is calculated
   *
   * @param operation the arithmetic operation to be scheduled
   */
  public void setOperation(BinaryArithmeticOperation operation) {
    this.operation = operation == null ? NO_OPERATION : operation;
    resultIsCached = false;
  }

  /**
   * Sets an operand in the form of a percentage for the next arithmetic operation, the position of
   * the operand depends on whether there is scheduled arithmetic operation or not
   *
   * @param operand the operand that will be used as a percentage
   * @return the calculated value of the operand as a percentage
   */
  public double setPercentageOperand(double operand) {
    if (!operationIsSet()) {
      firstOperand = 1;
    }
    resultIsCached = false;
    secondOperand = calculatePercentage(firstOperand, operand);
    return secondOperand;
  }

  /**
   * Calculates the percentage of a number
   *
   * @param base the base from which the percentage is taken
   * @param numericValue the actual numeric value that will be used as a percentage
   * @return the amount of the base defined by numeric value as a percentage
   */
  private double calculatePercentage(double base, double numericValue) {
    return arithmeticProcessor.getPercentage(base, numericValue);
  }

  /**
   * Checks whether an arithmetic operation is scheduled to be executed
   *
   * @return true is there is an arithmetic operation is scheduled to be executed; false otherwise
   */
  private boolean operationIsSet() {
    return operation != NO_OPERATION;
  }
}
