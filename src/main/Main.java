package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the window properly
        window.setResizable(false);
        window.setTitle("The 2DG");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // Causes this window to be sized to fit the preferred size and layout of its subcomponents

        window.setLocationRelativeTo(null); //window will be at the center of the screen as a location is not specified
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
