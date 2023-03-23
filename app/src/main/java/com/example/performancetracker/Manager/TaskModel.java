package com.example.performancetracker.Manager;

public class TaskModel {

    String taskname, taskdesc;

    public TaskModel(){}

    public TaskModel(String taskname, String taskdesc) {
        this.taskname = taskname;
        this.taskdesc = taskdesc;
    }

    public String getTaskName() {
        return taskname;
    }

    public void setTaskName(String name) {
        this.taskdesc = name;
    }

    public String getTaskDesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }

}