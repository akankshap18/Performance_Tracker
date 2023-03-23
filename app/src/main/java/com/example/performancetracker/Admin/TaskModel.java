package com.example.performancetracker.Admin;

public class TaskModel {
    String taskname, taskdesc;

    public TaskModel(){}

    public TaskModel(String taskname, String taskdesc) {
        this.taskname = taskname;
        this.taskdesc = taskdesc;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }
}