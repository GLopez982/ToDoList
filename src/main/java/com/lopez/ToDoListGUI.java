package com.lopez;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogManager;
import java.util.logging.Handler;

public class ToDoListGUI extends JFrame {
    private JComboBox<String> actionComboBox;
    public static ToDoList toDoList;
    private static final Logger logger = Logger.getLogger(ToDoListGUI.class.getName());

    
    
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
        try {
            // Load logging configuration
            LogManager.getLogManager().readConfiguration(
                ToDoListGUI.class.getClassLoader().getResourceAsStream("LoggingConfiguration.properties")
            );
        } catch (Exception e) {
            System.err.println("Could not load logging configuration file");
        }

        // Set up file handler and console handler for logging
        try {
            FileHandler fileHandler = new FileHandler("GUI_Application.log", true);
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.setLevel(Level.ALL);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
        } catch (Exception e) {
            logger.severe("Error setting up file handler: " + e.getMessage());
        }
        // Initialize the ToDoList instance

        toDoList = ToDoList.getToDoInstance();
        // Get the selected action from the combo box


        String selectedAction = (String) actionComboBox.getSelectedItem();
        switch (selectedAction) {
            case "Add Task":
                // Prompt the user for a new task
                logger.info("User selected to add a task");
                // Use JOptionPane to get user input

                String newTask = JOptionPane.showInputDialog(this, "Enter new task:");
                if (newTask != null && !newTask.trim().isEmpty()) {
                    toDoList.addChore(newTask);
                    JOptionPane.showMessageDialog(this, "Task added: " + newTask);
                }
                break;
            case "View Tasks":
                // Display the current tasks in a message dialog
                logger.info("User selected to view tasks");
                
                String tasks = toDoList.toString();
                JOptionPane.showMessageDialog(this, tasks);
                break;
            case "Remove Task":
                
                // Prompt the user for the task number to remove
                logger.info("User selected to remove a task");
                // Use JOptionPane to get user input
                String currentTasks = toDoList.toString();
                String taskToRemove = JOptionPane.showInputDialog(this, 
                    "Current tasks:\n" + currentTasks + "\nEnter the task number to remove:");
                if (taskToRemove != null && !taskToRemove.trim().isEmpty()) {
                    try {
                        int taskNumber = Integer.parseInt(taskToRemove);
                        toDoList.removeChore(taskNumber);
                        JOptionPane.showMessageDialog(this, "Task removed");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid task number");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please select an action");
                break;
        }
    }
    
    public static void main(String[] args) {
        logger.info("Starting ToDo List GUI application");
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ToDoListGUI gui = new ToDoListGUI();
            gui.setVisible(true);
        });
    }
}