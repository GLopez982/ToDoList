package com.lopez;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogManager;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        try {
            // Load logging configuration
            LogManager.getLogManager().readConfiguration(
                App.class.getClassLoader().getResourceAsStream("LoggingConfiguration.properties")
            );
        } catch (Exception e) {
            logger.severe("Could not load logging configuration file");
        }
        try{
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
            Formatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.setLevel(Level.ALL);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            logger.addHandler(consoleHandler);
            logger.info("File handler and console handler set up successfully");
        }catch (Exception e) {
            logger.severe("Error setting up file handler: " + e.getMessage());
        }

        ToDoList myDoList = ToDoList.getToDoInstance();
        boolean isRunning = true;

        logger.info("ToDo Application started");
        System.out.println("Welcome to the ToDo Menu >> ");
        System.out.println("Please make a selection:  ");
        System.out.println("A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit ");
        System.out.printf("Enter your choice: ");

        while(isRunning) {

            Scanner input = new Scanner(System.in);
            System.out.println("A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit");
            String userChoice = input.nextLine();
            
            while(userChoice.isEmpty()){
                logger.warning("User provided empty input");
                System.out.println("A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit");
                userChoice = input.nextLine();
            }
            
            char userchar = userChoice.toUpperCase().charAt(0);
            logger.info("User selected option: " + userchar);

            switch (userchar) {
                case 'A':
                    System.out.println("Please enter a chore to add to the list >> ");
                    String chore = input.nextLine();
                    logger.info("Adding new chore: " + chore);
                    myDoList.addChore(chore);
                    logger.fine("Chore added successfully");
                    System.out.println("Chore list updated successfully ");
                    break;

                case 'R':
                    System.out.println("This is your current ToDo list >>> ");
                    System.out.println(myDoList.toString());
                    System.out.println("Enter the index number of the completed chore to be removed >>> ");
                    int userIndex = input.nextInt();
                    if(!input.hasNextLine()){
                        logger.warning("User did not enter a valid index number");
                        System.out.println("Please enter a valid index number");
                        userIndex = input.nextInt();
                        input.nextLine();

                    }
                    logger.info("User entered index number: " + userIndex);
                    input.nextLine();
                    logger.info("Removing chore at index: " + userIndex);
                    if(userIndex < 0 || userIndex >= myDoList.toDoList.size()){
                        logger.severe("Index number invalid, out of bounds or invalid keystroke pressed ");
                        System.out.println("Please enter a valid index number");
                        userIndex = input.nextInt();
                        input.nextLine();
                    }
                    myDoList.removeChore(userIndex);
                    logger.fine("Chore removed successfully");
                    System.out.println("Chore removed successfully ");
                    break;

                case 'D':
                    logger.info("Displaying ToDo list");
                    myDoList.getToDoList();
                    break;

                case 'Q':
                    logger.info("User requested to quit the application");
                    System.out.println("Exiting the program...\n.... Have a great day.");
                    isRunning = false;
                    break;

                default:
                    logger.warning( "Invalid menu choice entered: " + userchar);
                    System.out.println("Please enter a valid menu choice >> ");
                    userChoice = input.nextLine();
                    userchar = userChoice.toLowerCase().charAt(0);
                    break;
            }

        }
      
        
        logger.info("Application shutting down");
    }
}