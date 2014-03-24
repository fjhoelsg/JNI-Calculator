package cr.ac.tec.appsmoviles.jnicalculator.gui;

import cr.ac.tec.appsmoviles.jnicalculator.calculator.CalculatorManager;
import cr.ac.tec.appsmoviles.jnicalculator.calculator.operation.*;
import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.ArithmeticProcessor;
import cr.ac.tec.appsmoviles.jnicalculator.calculator.processing.NativeArithmeticProcessor;

import javax.swing.*;

/**
 * The controller for the main view of the application
 */
public class MainViewController {

  /**
   * The addition operation
   */
  private static final BinaryArithmeticOperation ADDITION = new Addition();

  /**
   * The representation of a decimal point
   */
  private static final String DECIMAL_POINT = ".";

  /**
   * The division operation
   */
  private static final BinaryArithmeticOperation DIVISION = new Division();

  /**
   * The error string that should be displayed is an exception is thrown
   */
  private static final String ERROR = "ERROR";

  /**
   * The multiplication operation
   */
  private static final BinaryArithmeticOperation MULTIPLICATION = new Multiplication();

  /**
   * The representation of a negative sign
   */
  private static final String NEGATIVE_SIGN = "-";

  /**
   * The subtraction operation
   */
  private static final BinaryArithmeticOperation SUBTRACTION = new Subtraction();

  /**
   * The default 'zero' string
   */
  private static final String ZERO = "0";

  /**
   * The maximum number of digits that should be sent to the display
   */
  private final int maxDigits;

  /**
   * The underlying manager for the calculator
   */
  private CalculatorManager calculatorManager;

  /**
   * The button that sends the clear command
   */
  private JButton clearButton;

  /**
   * Whether a decimal point has been set or not
   */
  private boolean decimalPointSet;

  /**
   * The display for the output
   */
  private JLabel display;

  /**
   * Whether the user is editing what is being displayed or not
   */
  private boolean editing;

  /**
   * Creates a controller for the main view
   *
   * @param clearButton the button that sends the clear command
   * @param display the display for the output
   * @param maxDigits the maximum number of digits that should be displayed
   */
  public MainViewController(JButton clearButton, JLabel display, int maxDigits) {
    this.display = display;
    this.clearButton = clearButton;
    this.maxDigits = maxDigits;
    ArithmeticProcessor processor = new NativeArithmeticProcessor();
    calculatorManager = new CalculatorManager(processor);
    clearDisplay();
  }

  /**
   * Handles the clear command
   */
  public void handleClear() {
    final String action = clearButton.getText();
    final String allClear = "AC";
    if (action.equals(allClear)) {
      calculatorManager.clearAll();
    } else {
      clearButton.setText(allClear);
    }
    clearDisplay();
  }

  /**
   * Handles the input of digits
   *
   * @param digit the digit that is send (can be the decimal point)
   */
  public void handleDigit(String digit) {
    String displayText = getDisplayText();
    if (getDisplayValue() == 0 && digit.equals(ZERO)) {
      // Ignore 0 when display is 0
      return;
    }
    if (!isEditing()) {
      clearDisplay();
      setDisplayText("");
      displayText = getDisplayText();
      setEditing(true);
    } else if (displayText.length() >= maxDigits) {
      return;
    }
    if (digit.equals(DECIMAL_POINT)) {
      if (decimalPointSet) {
        return;
      }
      if (getDisplayText().isEmpty()) {
        setDisplayValue(0);
      }
      decimalPointSet = true;
    } else if (getDisplayValue() == 0) {
      boolean isNegative = displayText.startsWith(NEGATIVE_SIGN);
      // Prevents leading zeros without a decimal point
      setDisplayText(isNegative ? NEGATIVE_SIGN : "");
    }
    appendToDisplay(digit);
  }

  /**
   * Handles the division command
   */
  public void handleDivision() {
    setOperation(DIVISION);
  }

  /**
   * Handles the equals command
   */
  public void handleEquals() {
    getResult();
  }

  /**
   * Handles the minus command
   */
  public void handleMinus() {
    setOperation(SUBTRACTION);
  }

  /**
   * Handles the percentage command
   */
  public void handlePercentage() {
    double percentage = calculatorManager.setPercentageOperand(getDisplayValue());
    setDisplayValue(percentage);
    if (!calculatorManager.operationIsPending()) {
      setEditing(false);
    }
  }

  /**
   * Handles the plus command
   */
  public void handlePlus() {
    setOperation(ADDITION);
  }

  /**
   * Handles the change of sign command
   */
  public void handleSignChange() {
    final String displayText = getDisplayText();
    if (displayText.equals(ERROR)) {
      return;
    }
    String newDisplayText;
    if (calculatorManager.operationIsPending()) {
      newDisplayText = NEGATIVE_SIGN + ZERO;
    } else {
      newDisplayText = displayText.startsWith(NEGATIVE_SIGN) ? displayText.substring(1) : NEGATIVE_SIGN + displayText;
    }
    setDisplayText(newDisplayText);
    setEditing(true);
  }

  /**
   * Handles the square root command
   */
  public void handleSquareRoot() {
    try {
      double root = calculatorManager.calculateSquareRoot(getDisplayValue());
      setDisplayValue(root);
      if (!calculatorManager.operationIsPending()) {
        setEditing(false);
      }
    } catch (ArithmeticException exception) {
      raiseError();
    }
  }

  /**
   * Handle the times command
   */
  public void handleTimes() {
    setOperation(MULTIPLICATION);
  }

  /**
   * Display the error string to the user
   */
  public void raiseError() {
    setDisplayText(ERROR);
  }

  /**
   * Appends a digit to the display
   *
   * @param digit the digit that will be appended
   */
  private void appendToDisplay(String digit) {
    String displayText = getDisplayText() + digit;
    setDisplayText(displayText);
  }

  /**
   * Clears the display
   */
  private void clearDisplay() {
    setDisplayValue(0);
    setEditing(false);
    decimalPointSet = false;
  }

  /**
   * Gets the text that is currently being displayed
   *
   * @return the text of the display
   */
  private String getDisplayText() {
    return display.getText();
  }

  /**
   * Changes the text that is currently being displayed
   *
   * @param value the new text
   */
  private void setDisplayText(String value) {
    final int maxDigits = value.startsWith(NEGATIVE_SIGN) ? this.maxDigits + 1 : this.maxDigits;
    if (value.length() > maxDigits) {
      display.setText(value.substring(0, maxDigits - 1));
    } else {
      display.setText(value);
    }
  }

  /**
   * Gets the number that is currently being displayed
   *
   * @return the text of the display as a number
   */
  private double getDisplayValue() {
    String value = getDisplayText();
    if (value.endsWith(DECIMAL_POINT)) {
      value = value + "0";
    }
    // ERROR should behave as a 0
    return value.isEmpty() || value.equals(ERROR) || value.equals(NEGATIVE_SIGN) ? 0 : Double.parseDouble(value);
  }

  /**
   * Changes the value that is currently being displayed
   *
   * @param value the new number
   */
  private void setDisplayValue(double value) {
    String displayValue;
    if (value == (int) value) {
      displayValue = Integer.toString((int) value);
    } else {
      displayValue = Double.toString(value);
    }
    setDisplayText(displayValue);
  }

  /**
   * Obtains and display the result of the operation that is set in the CalculatorManager
   */
  private void getResult() {
    setEditing(false);
    calculatorManager.setOperand(getDisplayValue());
    try {
      setDisplayValue(calculatorManager.getResult());
    } catch (ArithmeticException exception) {
      calculatorManager.clearAll();
      raiseError();
    }
  }

  /**
   * Checks if the user is currently editing what is being displayed
   *
   * @return true if the user is editing the display; false otherwise
   */
  private boolean isEditing() {
    return editing;
  }

  /**
   * Sets the state of display as being edited or not
   *
   * @param isEditing whether the user is editing the display or not
   */
  private void setEditing(boolean isEditing) {
    editing = isEditing;
    if (editing) {
      clearButton.setText("C");
    }
  }

  /**
   * Sets the operation that the CalculatorManager should execute
   *
   * @param operation the operation that should be executed
   */
  private void setOperation(BinaryArithmeticOperation operation) {
    if (calculatorManager.operationIsPending()) {
      if (!editing) {
        calculatorManager.setOperation(operation);
        return;
      }
      getResult();
    }
    setEditing(false);
    calculatorManager.setOperand(getDisplayValue());
    calculatorManager.setOperation(operation);
  }
}
