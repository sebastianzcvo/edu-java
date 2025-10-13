package swing;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class MainFrame extends JFrame {

    private JTextField postIdField;
    private JButton fetchButton;
    private JLabel resultLabel;
    private JSlider slider;
    private JLabel sliderLabel;

    public MainFrame() {
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        setTitle("Main Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLocationRelativeTo(null); // Center on screen

        postIdField = new JTextField(10);
        fetchButton = new JButton("Fetch Post");
        resultLabel = new JLabel("Enter post ID and click the button");

        slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        sliderLabel = new JLabel("Slider Value: 50");
    }

    private void setupLayout() {
        JPanel fetchPanel = new JPanel();
        fetchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        fetchPanel.add(new JLabel("Post ID:"));
        fetchPanel.add(postIdField);
        fetchPanel.add(fetchButton);

        // Slider panel to demonstrate UI remains responsive
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BorderLayout(10, 10));
        sliderPanel.setBorder(BorderFactory.createTitledBorder("Slider (remains responsive during API call)"));
        sliderPanel.add(slider, BorderLayout.CENTER);
        sliderPanel.add(sliderLabel, BorderLayout.SOUTH);

        setLayout(new BorderLayout(10, 10));
        add(resultLabel, BorderLayout.NORTH);
        add(fetchPanel, BorderLayout.CENTER);
        add(sliderPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        fetchButton.addActionListener(e -> handleButtonClick());

        // Slider listener to demonstrate UI remains responsive during API call
        slider.addChangeListener(e -> sliderLabel.setText("Slider Value: " + slider.getValue()));
    }

    private void handleButtonClick() {
        Optional<Integer> postId = validatePostId();
        if (postId.isEmpty()) {
            return;
        }

        // Disable button and show loading state
        fetchButton.setEnabled(false);
        resultLabel.setText("Loading...");

        // Execute API call in background thread using SwingWorker
        new PostWorker(
                postId.get(),
                result -> resultLabel.setText(result),
                ex -> resultLabel.setText("Error: " + ex.getMessage()),
                () -> fetchButton.setEnabled(true)
        ).execute();
    }

    private Optional<Integer> validatePostId() {
        String postIdText = postIdField.getText().trim();

        if (postIdText.isEmpty()) {
            resultLabel.setText("Please enter a post ID");
            return Optional.empty();
        }

        try {
            return Optional.of(Integer.parseInt(postIdText));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid post ID. Please enter a number.");
            return Optional.empty();
        }
    }

}
