package breakout;

import javax.swing.*;

public class MainWindow {

    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(MainWindow::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Breakout");
        BreakoutPanel breakoutPanel = new BreakoutPanel();
        Thread thread = new Thread(breakoutPanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.add(breakoutPanel);
        frame.setVisible(true);

        thread.start();
    }
}
