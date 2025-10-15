package swingWorkers.ui;

import swingWorkers.device.RFDeviceMech;
import swingWorkers.device.RFDeviceProx;
import swingWorkers.worker.CountingWorker;
import swingWorkers.worker.RFDeviceWorker;
import swingWorkers.worker.WorkerManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final WorkerManager workerManager;

    private JTextArea outputArea;
    private JButton counterButton;
    private JButton pauseResumeButton;

    public MainFrame(WorkerManager workerManager) {
        this.workerManager = workerManager;
        initComponents();
        setupLayout();
        setupListeners();
        initWorkers();
    }

    private void initComponents() {
        setTitle("Swing Workers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        counterButton = new JButton("Start counter");

        pauseResumeButton = new JButton("Pause");
        pauseResumeButton.setEnabled(false);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(outputArea);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(counterButton);
        buttonPanel.add(pauseResumeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        counterButton.addActionListener(e -> startCounter());
        pauseResumeButton.addActionListener(e -> togglePauseResumeWorkers());
    }

    private void initWorkers() {
        outputArea.setText("");
        pauseResumeButton.setEnabled(true);
        pauseResumeButton.setText("Pause");

        var proxWorker = new RFDeviceWorker(new RFDeviceMech(), workerManager, this::onRFData);
        var mechWorker = new RFDeviceWorker(new RFDeviceProx(), workerManager, this::onRFData);

        workerManager.submit(proxWorker);
        workerManager.submit(mechWorker);
    }

    private void startCounter() {
        counterButton.setEnabled(false);

        var worker = new CountingWorker(workerManager, this::onCounterData, this::onCounterDone);
        workerManager.submit(worker);
    }

    private void togglePauseResumeWorkers() {
        if (pauseResumeButton.getText().equals("Pause")) {
            workerManager.pause();
            pauseResumeButton.setText("Resume");
            outputArea.append("--- Paused ---\n");
        } else {
            workerManager.resume();
            pauseResumeButton.setText("Pause");
            outputArea.append("--- Resumed ---\n");
        }
    }

    private void onRFData(String data) {
        outputArea.append(data + "\n");
        workerManager.resume();
        pauseResumeButton.setText("Pause");
        outputArea.append("--- Resumed ---\n");
    }

    private void onCounterData(String data) {
        outputArea.append(data + "\n");
    }

    private void onCounterDone(boolean success) {
        if (success) outputArea.append("Successful count to 10!\n");
        counterButton.setEnabled(true);
        workerManager.resume();
        outputArea.append("--- Resumed ---\n");
    }
}
