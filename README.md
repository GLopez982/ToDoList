import javax.swing.*;
import java.awt.*;

public class ToDoListGUI extends JFrame {
    private JComboBox<String> actionComboBox;
    
    public ToDoListGUI() {
        // Set up the frame
        setTitle("ToDo List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the frame
        
        // Create main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create the combo box with actions
        String[] actions = {"Select Action", "Add Task", "View Tasks", "Mark Task as Complete", "Remove Task"};
        actionComboBox = new JComboBox<>(actions);
        
        // Create a button to execute the selected action
        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(e -> handleAction());
        
        // Add components to panels
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Select Operation: "));
        topPanel.add(actionComboBox);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(executeButton);
        
        // Add panels to main panel
        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    private void handleAction() {
        String selectedAction = (String) actionComboBox.getSelectedItem();
        switch (selectedAction) {
            case "Add Task":
                String newTask = JOptionPane.showInputDialog(this, "Enter new task:");
                if (newTask != null && !newTask.trim().isEmpty()) {
                    // Add your task adding logic here
                    JOptionPane.showMessageDialog(this, "Task added: " + newTask);
                }
                break;
            case "View Tasks":
                // Add your task viewing logic here
                JOptionPane.showMessageDialog(this, "View Tasks selected");
                break;
            case "Mark Task as Complete":
                // Add your task completion logic here
                JOptionPane.showMessageDialog(this, "Mark Task as Complete selected");
                break;
            case "Remove Task":
                // Add your task removal logic here
                JOptionPane.showMessageDialog(this, "Remove Task selected");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please select an action");
                break;
        }
    }
    
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ToDoListGUI gui = new ToDoListGUI();
            gui.setVisible(true);
        });
    }
}