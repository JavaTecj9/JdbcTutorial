package com.mcnz.jdbc.tutorial.JdbcTutorial;

public class Task {

    int id;
    String name;
    String status;

    public Task() {
    }

    public Task(String name) {
        super();
        this.name = name;
    }

    public Task(int id, String name) {
        super();
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

