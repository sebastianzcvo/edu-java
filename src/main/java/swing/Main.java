package swing;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Always create and show GUI on EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
