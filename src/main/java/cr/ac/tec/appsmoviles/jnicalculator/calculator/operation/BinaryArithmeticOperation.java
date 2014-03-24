package cr.ac.tec.appsmoviles.jnicalculator.calculator.operation;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;

/**
 * Defines an arithmetic operation with two operands
 */
public abstract class BinaryArithmeticOperation {

  /**
   * Performs the operation on the given operands
   *
   * @param arithmeticProcessor the processor that will execute the operation
   * @param firstOperand the first operand for the operation
   * @param secondOperand the second operand for the operation
   * @return the result of the operation with the given operands
   */
  public abstract double operate(ArithmeticProcessor arithmeticProcessor, double firstOperand, double secondOperand);
}
