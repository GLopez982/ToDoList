package com.lopez;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;

public class LoggerTest {
    private static final Logger LOGGER = Logger.getLogger("TestLogger");

    public static void main(String[] args) {
        try {
            // Setup file handler
            FileHandler fh = new FileHandler("testlog.txt");
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // Start testing
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\nLogger Test Menu:");
                System.out.println("1. Test Info Log");
                System.out.println("2. Test Warning Log");
                System.out.println("3. Test Severe Log");
                System.out.println("4. Exit");
                System.out.print("Choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        LOGGER.info("This is a test INFO message");
                        System.out.println("Info message logged");
                        break;
                    case "2":
                        LOGGER.warning("This is a test WARNING message");
                        System.out.println("Warning message logged");
                        break;
                    case "3":
                        LOGGER.severe("This is a test SEVERE message");
                        System.out.println("Severe message logged");
                        break;
                    case "4":
                        LOGGER.info("Ending test");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
            
            scanner.close();
            fh.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}