package com.lopez;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class ToDoListTest {

    private ToDoList toDoList;

    @BeforeEach
    public void setUp(){
        toDoList = ToDoList.getToDoInstance();
    }


    @Test
    public void testAddChore(){
        LinkedList<String> updateList = toDoList.addChore("Work on Computer");
        assertTrue(updateList.contains("Work on Computer"), "Chore should be added to the toDoList");
    }

    @Test
    public void testRemoveChore(){
        LinkedList<String> updatedList = toDoList.removeChore(0);
        assertFalse(updatedList.contains("Work on Computer"), "Chore at index 0 should be removed");
    }

    @Test
    public void testGetToDoList(){
        toDoList.getToDoList();
        assertNotNull(toDoList, "The list should not be null");
    }





}
