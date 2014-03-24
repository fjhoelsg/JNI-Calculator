package cr.ac.tec.appsmoviles.jnicalculator.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main view of the application
 */
public class MainView extends JFrame {

  /**
   * The vertical weight for the display
   */
  public static final double DISPLAY_VERTICAL_WEIGHT = 0.15;

  /**
   * The vertical weight for the buttons panel
   */
  private static final double BUTTONS_PANEL_VERTICAL_WEIGHT = 0.75;

  /**
   * The background color for the display
   */
  private static final String DISPLAY_BACKGROUND_COLOR = "#FFFFFF";

  /**
   * The color for the border of the display
   */
  private static final String DISPLAY_BORDER_COLOR = "#CCCCCC";

  /**
   * The font used to inside the display
   */
  private static final String DISPLAY_FONT = "Monospaced";

  /**
   * The font size for the display
   */
  private static final int DISPLAY_FONT_SIZE = 36;

  /**
   * The color of the text in the display
   */
  private static final String DISPLAY_FOREGROUND_COLOR = "#000000";

  /**
   * The maximum number of digits that can appear in the display
   */
  private static final int MAX_DIGITS = 11;

  /**
   * The initial height of the window
   */
  private static final int WINDOW_HEIGHT = 400;

  /**
   * Whether the window is resizable or not
   */
  private static final boolean WINDOW_RESIZABLE = false;

  /**
   * The text that will appear in the window's title bar
   */
  private static final String WINDOW_TITLE = "JNI Calculator";

  /**
   * The initial width of the window
   */
  private static final int WINDOW_WIDTH = 300;

  /**
   * The button that sends the clear command
   */
  private JButton clearButton;

  /**
   * The underlying controller for the calculator
   */
  private MainViewController controller;

  /**
   * The display for the output
   */
  private JLabel display = new JLabel();

  /**
   * Creates a window frame and initializes its components
   */
  public MainView() {
    super();
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle(WINDOW_TITLE);
    setLocationByPlatform(true);
    setSize(new java.awt.Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    setResizable(WINDOW_RESIZABLE);
    clearButton = createButton("AC", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handleClear();
      }
    });
    controller = new MainViewController(clearButton, display, MAX_DIGITS);
    initComponents();
  }

  /**
   * Creates a button
   *
   * @param text the text for the button
   * @param listener the ActionListener for the button
   * @return a button with the specified properties
   */
  private JButton createButton(String text, ActionListener listener) {
    JButton button = new JButton(text);
    button.addActionListener(listener);
    return button;
  }

  /**
   * Creates the components needed for the calculator and connects them to the
   * controller
   */
  private void initComponents() {
    // Root
    JPanel root = new JPanel();
    root.setSize(new Dimension(this.getWidth(), this.getHeight()));
    // Display
    display.setFont(new java.awt.Font(DISPLAY_FONT, 0, DISPLAY_FONT_SIZE));
    display.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode(DISPLAY_BORDER_COLOR)));
    display.setForeground(Color.decode(DISPLAY_FOREGROUND_COLOR));
    display.setBackground(Color.decode(DISPLAY_BACKGROUND_COLOR));
    display.setOpaque(true);
    display.setHorizontalAlignment(JLabel.RIGHT);
    // Buttons Panel
    JPanel buttonsPanel = new JPanel(new GridLayout(5, 4, 0, 0));
    // Buttons
    ActionListener digitListener = new DigitActionListener();
    buttonsPanel.add(clearButton);
    buttonsPanel.add(createButton("√", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handleSquareRoot();
      }
    }));
    buttonsPanel.add(createButton("%", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handlePercentage();
      }
    }));
    buttonsPanel.add(createButton("÷", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handleDivision();
      }
    }));
    buttonsPanel.add(createButton("7", digitListener));
    buttonsPanel.add(createButton("8", digitListener));
    buttonsPanel.add(createButton("9", digitListener));
    buttonsPanel.add(createButton("×", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handleTimes();
      }
    }));
    buttonsPanel.add(createButton("4", digitListener));
    buttonsPanel.add(createButton("5", digitListener));
    buttonsPanel.add(createButton("6", digitListener));
    buttonsPanel.add(createButton("-", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handleMinus();
      }
    }));
    buttonsPanel.add(createButton("1", digitListener));
    buttonsPanel.add(createButton("2", digitListener));
    buttonsPanel.add(createButton("3", digitListener));
    buttonsPanel.add(createButton("+", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handlePlus();
      }
    }));
    buttonsPanel.add(createButton("±", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handleSignChange();
      }
    }));
    buttonsPanel.add(createButton("0", digitListener));
    buttonsPanel.add(createButton(".", digitListener));
    buttonsPanel.add(createButton("=", new ActionListener() {

      /**
       * Invoked when an action occurs.
       */
      @Override
      public void actionPerformed(ActionEvent event) {
        controller.handleEquals();
      }
    }));
    // Add Components
    root.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 1;
    constraints.weighty = DISPLAY_VERTICAL_WEIGHT;
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.insets = new Insets(2, 5, 2, 5);
    constraints.fill = GridBagConstraints.HORIZONTAL;
    root.add(display, constraints);
    constraints.insets = new Insets(2, 0, 2, 0);
    constraints.weighty = BUTTONS_PANEL_VERTICAL_WEIGHT;
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.fill = GridBagConstraints.VERTICAL;
    root.add(buttonsPanel, constraints);
    add(root);
  }

  /**
   * An ActionListener that handles digits and sends them to the controller
   */
  private class DigitActionListener implements ActionListener {

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
      String digit = ((JButton) event.getSource()).getText();
      controller.handleDigit(digit);
    }
  }
}
