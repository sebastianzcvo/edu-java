package swingWorkers;

import swingWorkers.ui.MainFrame;
import swingWorkers.worker.WorkerManager;

import javax.swing.*;

public class SwingWorkersApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame(new WorkerManager()).setVisible(true);
        });
    }
}
