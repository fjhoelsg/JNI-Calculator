package cr.ac.tec.appsmoviles.jnicalculator.calculator.operation;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;

/**
 * Defines a operation that does nothing
 */
public class NoOperation extends BinaryArithmeticOperation {

  /**
   * Does nothing
   *
   * @param arithmeticProcessor the processor that will execute the operation
   * @param firstOperand the first operand for the operation
   * @param secondOperand the second operand for the operation
   * @return the first operand
   */
  @Override
  public double operate(ArithmeticProcessor arithmeticProcessor, double firstOperand, double secondOperand) {
    return firstOperand;
  }
}
