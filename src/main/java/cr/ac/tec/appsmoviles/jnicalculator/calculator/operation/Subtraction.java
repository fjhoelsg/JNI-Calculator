package cr.ac.tec.appsmoviles.jnicalculator.calculator.operation;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;

/**
 * Defines the subtraction operation
 */
public class Subtraction extends BinaryArithmeticOperation {

  /**
   * Subtracts the second operand from the first operand
   *
   * @param arithmeticProcessor the processor that will execute the operation
   * @param firstOperand the minuend
   * @param secondOperand the subtrahend
   * @return the difference between the first operand and the second operand
   */
  @Override
  public double operate(ArithmeticProcessor arithmeticProcessor, double firstOperand, double secondOperand) {
    return arithmeticProcessor.subtract(firstOperand, secondOperand);
  }
}
