package com.mcnz.jdbc.tutorial.JdbcTutorial;

import org.hibernate.annotations.processing.SQL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JdbcToDoList {

    public static void main(String[] args) {
        try {
            Task task1 = new Task("Learn JDBC");
            TaskRepository.create(task1);

            Task taskupdate = new Task( 41,"Updated Task name Test");
            TaskRepository.update(taskupdate);

            List<Task> tasks = TaskRepository.findAll();
            for (Task task : tasks) {
                System.out.println(task.name);
            }

            //Task taskDelete = new Task();
            //TaskRepository.deleteAll(taskDelete);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

