package com.mcnz.jdbc.tutorial.JdbcTutorial;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    // Create a DataSource using HikariCP
    // HikariCP is a high-performance JDBC connection pool
    private static DataSource getDataSource(){
        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:./todo;AUTO_SERVER=TRUE");
        return dataSource;
    }

    // Create a table if it does not exist
    public static void createTable() throws SQLException {
        try (Connection connection = getDataSource().getConnection()) {
            String createTableSql = "create table if not exists TASK (id identity primary key, name varchar, status varchar)";
            var statement = connection.createStatement();
            statement.execute(createTableSql);
        }
    }
    // Create, Update, Delete, and Find methods for Task
    // These methods will use the DataSource to get a connection and perform operations on the TASK
    public static void create(Task task) throws Exception{
        try(Connection connection = getDataSource().getConnection()) {
            String insertStatement = "insert into TASK (name) values (?)";
            var preparedStatement = connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, task.name);
            preparedStatement.execute();
        }
    }

    // Update a task by its ID
    public static void update(Task task) throws Exception {
        try (Connection connection = getDataSource().getConnection()) {
            String updateCommand = "update TASK set name = ? where id = ?";
            var preparedStatement = connection.prepareStatement(updateCommand);
            preparedStatement.setString(1, task.name);
            preparedStatement.setString(2, String.valueOf(task.id));
            preparedStatement.execute();

        }
    }
    // Delete all tasks
    public static void deleteAll(Task task) throws Exception {
        try (Connection connection = getDataSource().getConnection()) {
            var statement = connection.createStatement();
            statement.executeUpdate("Delete From TASK");

        }
    }
    // Find all tasks
    public static List<Task> findAll() throws Exception {
        List<Task> tasks = new ArrayList();
        try (Connection connection = getDataSource().getConnection()) {
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery("select * from TASK");
            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt(1), resultSet.getString(2));
                tasks.add(task);
            }
            return tasks;
        }
    }
}
