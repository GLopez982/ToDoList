package com.lopez;

import java.util.Scanner;

public class App 
{
      public static void main(String[] args) {

            ToDoList myDoList = ToDoList.getToDoInstance();

            boolean isRunning = true;



            System.out.println("Welcome to the ToDo Menu >> ");
            System.out.println("Please make a selection:  ");
            System.out.println("A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit ");

            while(isRunning) {

                Scanner input = new Scanner(System.in);
                System.out.println("A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit");
                String userChoice = input.nextLine();
                while(userChoice.isEmpty()){
                    System.out.println("A to add a chore to the list, R to remove a chore from the list, D to display the list, Q to quit");
                    userChoice = input.nextLine();
                }
                char userchar = userChoice.toUpperCase().charAt(0);

                switch (userchar) {


                    case 'A':
                        System.out.println("Please enter a chore to add to the list >> ");
                        String chore = input.nextLine();
                        myDoList.addChore(chore);
                        System.out.println("Chore list updated successfully ");
                        break;

                    case 'R':

                        System.out.println("This is your current ToDo list >>> ");
                        System.out.println(myDoList.toString());
                        System.out.println("Enter the index number of the completed chore to be removed >>> ");
                        int  userIndex = input.nextInt();
                        input.nextLine();
                        myDoList.removeChore(userIndex);
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
                        System.out.println("Please enter a valid menu choice >> ");
                        userChoice = input.nextLine();
                        userchar = userChoice.toLowerCase().charAt(0);
                        break;


                }


            }

        }
    }
