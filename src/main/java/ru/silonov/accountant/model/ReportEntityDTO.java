package ru.silonov.accountant.model;

public class ReportEntityDTO {
    private String report;
    private int time;
    private int student;

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "AccountantEntityDTO{" +
                "report='" + report + '\'' +
                ", time=" + time +
                ", student=" + student +
                '}';
    }
}
