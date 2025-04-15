package javanexuspots.inventoryManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author OEG Group
 */
import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        // Initialize the backend components


        // Launch the UI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}

