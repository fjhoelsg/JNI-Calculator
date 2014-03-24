package cr.ac.tec.appsmoviles.jnicalculator.calculator.processing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A processor that connects to a native library to perform basic arithmetic operations
 */
public class NativeArithmeticProcessor extends ArithmeticProcessor {

  /**
   * Loads the native library required by the processing
   */
  static {
    final String libraryJarPath = "/libcalc.so";
    final int bufferSize = 1024;
    try {
      // Create temporary directory
      Path libraryDirectoryPath = Files.createTempDirectory("cr.ac.tec.appsmoviles.jnicalculator");
      // Create file
      File library = new File(libraryDirectoryPath.toAbsolutePath().toString(), libraryJarPath);
      if (library.createNewFile()) {
        library.deleteOnExit();
      }
      // Create streams
      try (InputStream input = NativeArithmeticProcessor.class.getResourceAsStream(libraryJarPath);
           OutputStream output = new FileOutputStream(library)) {
        // Write file
        byte[] buffer = new byte[bufferSize];
        int readBytes;
        while ((readBytes = input.read(buffer)) != -1) {
          output.write(buffer, 0, readBytes);
        }
        // Load library
        System.load(library.getAbsolutePath());
      }
    } catch (Exception exception) {
      throw new ExceptionInInitializerError("Could not load native library");
    }
  }

  /**
   * Adds two numbers
   *
   * @param augend the left term of the addition
   * @param addend the right term of the addition
   * @return the sum of the two numbers
   */
  @Override
  public double add(double augend, double addend) {
    return nativeAdd(augend, addend);
  }

  /**
   * Divides one number by another
   *
   * @param dividend the right term of the division
   * @param divisor the left term of the division
   * @return the quotient of the two numbers
   *
   * @throws ArithmeticException if the divisor is 0
   */
  @Override
  public double divide(double dividend, double divisor) throws ArithmeticException {
    if (divisor == 0) {
      throw new ArithmeticException("Division by 0");
    }
    return nativeDivide(dividend, divisor);
  }

  /**
   * Raises a number to a exponent
   *
   * @param base the number that will be raised to the exponent
   * @param exponent the degree to which the base will be raised
   */
  @Override
  public double exponentiate(double base, double exponent) throws ArithmeticException {
    if (base < 0 && exponent != (int) exponent) {
      throw new ArithmeticException("Possible even root of negative number");
    }
    return nativeExponentiate(base, exponent);
  }

  /**
   * Multiplies two numbers
   *
   * @param multiplicand the left term of the multiplication
   * @param multiplier the right term of the multiplication
   * @return the product of the two numbers
   */
  @Override
  public double multiply(double multiplicand, double multiplier) {
    return nativeMultiply(multiplicand, multiplier);
  }

  /**
   * Subtracts one number from another
   *
   * @param minuend the left term of the subtraction
   * @param subtrahend the right term of the subtraction
   * @return the difference of the two numbers
   */
  @Override
  public double subtract(double minuend, double subtrahend) {
    return nativeSubtract(minuend, subtrahend);
  }

  /**
   * Calls a native library to perform a sum
   *
   * @param augend the left term of the addition
   * @param addend the right term of the addition
   * @return the sum of the two numbers
   */
  private native double nativeAdd(double augend, double addend);

  /**
   * Calls a native library to perform a division
   *
   * @param dividend the right term of the division
   * @param divisor the left term of the division
   * @return the quotient of the two numbers
   *
   * @throws ArithmeticException if the divisor is 0
   */
  private native double nativeDivide(double dividend, double divisor);

  /**
   * Calls a native library to perform an exponentiation
   *
   * @param base the number that will be raised to the exponent
   * @param exponent the degree to which the base will be raised
   * @return the power of the base when raised to the exponent
   */
  private native double nativeExponentiate(double base, double exponent);

  /**
   * Calls a native library to perform a multiplication
   *
   * @param multiplicand the left term of the multiplication
   * @param multiplier the right term of the multiplication
   * @return the product of the two numbers
   */
  private native double nativeMultiply(double multiplicand, double multiplier);

  /**
   * Calls a native library to perform a subtraction
   *
   * @param minuend the left term of the subtraction
   * @param subtrahend the right term of the subtraction
   * @return the difference of the two numbers
   */
  private native double nativeSubtract(double minuend, double subtrahend);
}
