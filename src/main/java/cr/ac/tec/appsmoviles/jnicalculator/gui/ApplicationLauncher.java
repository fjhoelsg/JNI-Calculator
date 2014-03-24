package cr.ac.tec.appsmoviles.jnicalculator.gui;

/**
 * Launches the main view for the application
 */
public class ApplicationLauncher {

  /**
   * Starts the application
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        new MainView().setVisible(true);
      }
    });
  }
}
