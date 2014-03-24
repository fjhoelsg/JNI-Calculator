package cr.ac.tec.appsmoviles.jnicalculator.calculator.operation;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;

/**
 * Defines the multiplication operation
 */
public class Multiplication extends BinaryArithmeticOperation {

  /**
   * Multiplies two numbers
   *
   * @param arithmeticProcessor the processor that will execute the operation
   * @param firstOperand the multiplicand
   * @param secondOperand the multiplier
   * @return product of the first operand times the second operand
   */
  @Override
  public double operate(ArithmeticProcessor arithmeticProcessor, double firstOperand, double secondOperand) {
    return arithmeticProcessor.multiply(firstOperand, secondOperand);
  }
}
