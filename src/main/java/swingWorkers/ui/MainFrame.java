package swingWorkers.ui;

import swingWorkers.device.RFDevice;
import swingWorkers.device.RFDeviceMech;
import swingWorkers.device.RFDeviceProx;
import swingWorkers.worker.CountingWorker;
import swingWorkers.worker.RFDeviceWorker;
import swingWorkers.worker.WorkerManager;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
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
        startWorkers();
    }

    private void initComponents() {
        setTitle("Swing Workers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        DefaultCaret caret = (DefaultCaret) outputArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        counterButton = new JButton("Start counter");
        pauseResumeButton = new JButton("Pause");
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
        counterButton.addActionListener(e -> counterButton());
        pauseResumeButton.addActionListener(e -> pauseResumeButton());
    }

    private void startWorkers() {
        var proxWorker = new RFDeviceWorker(new RFDeviceMech(), workerManager, this::onRFData);
        var mechWorker = new RFDeviceWorker(new RFDeviceProx(), workerManager, this::onRFData);

        workerManager.submit(proxWorker, false);
        workerManager.submit(mechWorker, false);
    }

    private void counterButton() {
        outputArea.append("--- Paused ---\n");
        pauseResumeButton.setText("Pause");
        counterButton.setEnabled(false);
        pauseResumeButton.setEnabled(false);

        var countWorker = new CountingWorker(workerManager, this::onCounterData, this::onCounterDone);
        workerManager.submit(countWorker, true);
    }

    private void pauseResumeButton() {
        if (pauseResumeButton.getText().equals("Pause")) {
            workerManager.halt();
            outputArea.append("--- Paused ---\n");
            pauseResumeButton.setText("Resume");
        } else {
            workerManager.resume();
            outputArea.append("--- Resumed ---\n");
            pauseResumeButton.setText("Pause");
        }
    }

    private void onRFData(String data, RFDevice device) {
        switch (device) {
            case RFDeviceProx prox -> outputArea.append("Prox: " + data + "\n");
            case RFDeviceMech mech -> outputArea.append("Mech: " + data + "\n");
        }
        outputArea.append("--- Resumed ---\n");
        pauseResumeButton.setText("Pause");
    }

    private void onCounterData(Integer data) {
        outputArea.append("Counter: " + data + "\n");
    }

    private void onCounterDone(boolean success) {
        if (success) outputArea.append("Count done!\n");
        outputArea.append("--- Resumed ---\n");
        pauseResumeButton.setText("Pause");
        counterButton.setEnabled(true);
        pauseResumeButton.setEnabled(true);
    }
}
