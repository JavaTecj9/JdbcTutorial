package com.mcnz.jdbc.tutorial.JdbcTutorial;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Notes {
    public static void main(String[] args) throws SQLException {


        var connection = DriverManager.getConnection("jdbc:h2:./todo;AUTO_SERVER=TRUE");
        var statement = connection.createStatement();

        String createTableSql = "create table if not exists TASK (id identity primary key, name varchar, status varchar)";
        statement.execute(createTableSql);

        String insertQuery = "insert into TASK (name) values ('Learn Java!')";
        statement.execute(insertQuery);

        //Prepared Statement
        String insertStatement = "insert into TASK (name) values (?)";
        var preparedStatement = connection.prepareStatement(insertStatement);
        preparedStatement.setString(1, "Learn Spring!");
        preparedStatement.execute();

        String updateCommand = "update TASK set name = ? where name = 'Learn Java!'";
        preparedStatement.setString(1, "Learn Jakarta");
        preparedStatement.execute();

        //String updateCommand2 = "update TASK set status = 'In Progress' where name = 'Lean Java!'";
        //statement.execute(updateCommand2);

        //Delet query
        String deleteQuery = "delete from TASK where name = 'Lean Java!' or name = 'Learn Jakarta'";
        statement.execute(deleteQuery);

        //Select Query
        String selectAll = "select * from TASK";
        var resultSet = statement.executeQuery(selectAll);
        while (resultSet.next()) {
            System.out.println("To Do Item: " + resultSet.getString("name"));
        }
    }
}
