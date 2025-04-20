package com.lopez;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;


public class ToDoList{

    private static ToDoList onlyList = null;

    String[] toDoString = {"Clean Car", "Clean House", "Toss out trash", "Do Homework", "Feed the Cat", "Wash the windows",
            "Dump the Wagon", "Water Plants"};
    LinkedList<String> toDoList = new LinkedList<>(Arrays.asList(toDoString));

    private ToDoList() {}


    //This method is used to create a new instance of the ToDoList if one does not exist and returns the ToDoList shuffled.
    public static ToDoList getToDoInstance() {

        synchronized (ToDoList.class) {
            if (onlyList == null) {
                onlyList = new ToDoList();
                Collections.shuffle(onlyList.toDoList);

            }

        }
        return onlyList;
    }
    // This method returns a String LinkedList with the passed chore added to the ToDoList.
    public LinkedList<String> addChore(String chore){
        LinkedList<String> updatedToDoList = onlyList.toDoList;
        if(chore.isEmpty()){
            chore = JOptionPane.showInputDialog(null, "Please enter a chore to add to the list >> ");
            if(chore.isEmpty()){
                return updatedToDoList;
            }



        }
        updatedToDoList.add(chore);
        return updatedToDoList;
    }

    // This method returns a String LinkedList with the passed index number removed from the ToDoList.
    public LinkedList<String> removeChore(int choreIndex){
        LinkedList<String> removedLinkedList = onlyList.toDoList;

        if(choreIndex < 0 || choreIndex >= removedLinkedList.size()){
            String choreIndexString = JOptionPane.showInputDialog(null, "Please enter the chore index for removal >>> ");
            choreIndex = Integer.parseInt(choreIndexString);
            while(choreIndex < 0 || choreIndex >= removedLinkedList.size()){
                choreIndexString = JOptionPane.showInputDialog(null, "Please enter the chore index for removal >>> ");
                choreIndex = Integer.parseInt(choreIndexString);

            }
            removedLinkedList.remove(choreIndex);

        }else{
            removedLinkedList.remove(choreIndex);

        }
        return removedLinkedList;
    }

    public void getToDoList(){
        System.out.println(onlyList.toDoList);

    }

    //This is the overridden toString method for the ToDoList that outputs the chore index number and chore name.
    @Override
    public String toString(){
        StringBuilder toDoListIndex = new StringBuilder();
        for(int i = 0; i < onlyList.toDoList.size(); i++){
            toDoListIndex.append("Index# " + i + ": " + onlyList.toDoList.get(i)).append("\n");
        }
        return toDoListIndex.toString();

    }

}