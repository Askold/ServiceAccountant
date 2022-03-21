package ru.silonov.accountant.model;

public class ReportDTO {

    private String task;
    private int time;
    private int userId;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AccountantEntityDTO{" +
                "report='" + task + '\'' +
                ", time=" + time +
                ", student=" + userId +
                '}';
    }
}
