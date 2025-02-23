package com.lopez;
import javax.swing.*;

public class App 
{
      public static void main(String[] args) {

            ToDoList myDoList = ToDoList.getToDoInstance();

            boolean isRunning = true;



            System.out.println("Welcome to the ToDo Menu >> ");
            System.out.println("Please make a selection:  ");
            System.out.println("A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit ");

            while(isRunning) {

                String userChoice = JOptionPane.showInputDialog(null, "A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit");
                while(userChoice.isEmpty()){
                    userChoice = JOptionPane.showInputDialog(null, "A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit");

                }
                char userchar = userChoice.toUpperCase().charAt(0);

                switch (userchar) {


                    case 'A':
                        String chore = JOptionPane.showInputDialog(null, "Please enter a chore to add to the list >> ");
                        myDoList.addChore(chore);
                        System.out.println("Chore list updated successfully ");
                        break;

                    case 'R':

                        System.out.println("This is your current ToDo list >>> ");
                        System.out.println(myDoList.toString());
                        String userIndex = JOptionPane.showInputDialog(null, "Enter the index number of the completed chore to be removed >>> ");
                        int indexNum = Integer.parseInt(userIndex);
                        myDoList.removeChore(indexNum);
                        System.out.println("Chore removed successfully ");
                        break;


                    case 'D':
                        myDoList.getToDoList();

                        break;


                    case 'Q':
                        System.out.println("Exiting the program...\n.... Have a great day.");
                        isRunning = false;
                        break;

                    default:
                        userChoice = JOptionPane.showInputDialog(null, "Please enter a valid menu choice >> ");
                        userchar = userChoice.toLowerCase().charAt(0);
                        break;


                }


            }

        }
    }
