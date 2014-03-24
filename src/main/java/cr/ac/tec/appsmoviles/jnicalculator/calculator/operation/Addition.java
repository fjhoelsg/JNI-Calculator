package cr.ac.tec.appsmoviles.jnicalculator.calculator.operation;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;

/**
 * Defines the addition operation
 */
public class Addition extends BinaryArithmeticOperation {

  /**
   * Adds the two operands
   *
   * @param arithmeticProcessor the processor that will execute the operation
   * @param firstOperand the augend
   * @param secondOperand the addend
   * @return the sum of operands
   */
  @Override
  public double operate(ArithmeticProcessor arithmeticProcessor, double firstOperand, double secondOperand) {
    return arithmeticProcessor.add(firstOperand, secondOperand);
  }
}
