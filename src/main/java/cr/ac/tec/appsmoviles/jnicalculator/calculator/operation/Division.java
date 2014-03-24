package cr.ac.tec.appsmoviles.jnicalculator.calculator.operation;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;

/**
 * Defines the division operation
 */
public class Division extends BinaryArithmeticOperation {

  /**
   * Divides the first operand by the second operand
   *
   * @param arithmeticProcessor the processor that will execute the operation
   * @param firstOperand the dividend
   * @param secondOperand the divisor
   * @return the quotient of the first operand when divided by the second operand
   */
  @Override
  public double operate(ArithmeticProcessor arithmeticProcessor, double firstOperand, double secondOperand) {
    return arithmeticProcessor.divide(firstOperand, secondOperand);
  }
}
