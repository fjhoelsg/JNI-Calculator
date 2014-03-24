package cr.ac.tec.appsmoviles.jnicalculator.calculator.processing;

/**
 * Defines a processor for basic arithmetic operations
 */
public abstract class ArithmeticProcessor {

  /**
   * Adds two numbers
   *
   * @param augend the left term of the addition
   * @param addend the right term of the addition
   * @return the sum of the two numbers
   */
  public abstract double add(double augend, double addend);

  /**
   * Divides one number by another
   *
   * @param dividend the right term of the division
   * @param divisor the left term of the division
   * @return the quotient of the two numbers
   *
   * @throws java.lang.ArithmeticException if the divisor is 0
   */
  public abstract double divide(double dividend, double divisor) throws ArithmeticException;

  /**
   * Raises a number to a exponent
   *
   * @param base the number that will be raised to the exponent
   * @param exponent the degree to which the base will be raised
   * @return the power of the base when raised to the exponent
   */
  public abstract double exponentiate(double base, double exponent) throws ArithmeticException;

  /**
   * Finds a percentage of a number
   *
   * @param base the number from which the percentage is based
   * @param numericValue the ratio based on 100 that is wanted
   * @return the percentage that represents the specified ratio
   */
  public double getPercentage(double base, double numericValue) {
    return multiply(base, divide(numericValue, 100));
  }

  /**
   * Multiplies two numbers
   *
   * @param multiplicand the left term of the multiplication
   * @param multiplier the right term of the multiplication
   * @return the product of the two numbers
   */
  public abstract double multiply(double multiplicand, double multiplier);

  /**
   * Finds the nth root of a number
   *
   * @param radicand the number whose root is wanted
   * @param degree the degree of the root
   * @return the root of the radicand
   *
   * @throws java.lang.ArithmeticException if the radicand is negative and the
   *                                       degree is even
   */
  public double nthRoot(double radicand, double degree) {
    if (radicand < 0 && degree % 2 == 0) {
      throw new ArithmeticException("Even root of negative number");
    }
    return degree == 0 ? 1 : exponentiate(radicand, divide(1, degree));
  }

  /**
   * Subtracts one number from another
   *
   * @param minuend the left term of the subtraction
   * @param subtrahend the right term of the subtraction
   * @return the difference of the two numbers
   */
  public abstract double subtract(double minuend, double subtrahend);
}
