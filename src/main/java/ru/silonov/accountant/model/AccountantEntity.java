package ru.silonov.accountant.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Random;

@Entity
@Table(name = "accountant", schema = "public", catalog = "accountant")
public class AccountantEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id = (int) (System.currentTimeMillis());
    @Basic
    @Column(name = "report_date")
    private Date date = new Date(System.currentTimeMillis());
    @Basic
    @Column(name = "time_")
    private int time;
    @Basic
    @Column(name = "report_text")
    private String task;
    @Basic
    @Column(name = "student_id")
    private int userId;

    public AccountantEntity(int id) {
    }

    public AccountantEntity(String task, int time, int userId) {
        this.time = time;
        this.task = task;
        this.userId = userId;
    }

//    public AccountantEntity(Date date, String time, String task, int userId) {
//        this.date = date;
//        this.time = time;
//        this.task = task;
//        this.userId = userId;
//    }

    public AccountantEntity(int id, Date date, int time, String task, int userId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.task = task;
        this.userId = userId;
    }

    public AccountantEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountantEntity entity = (AccountantEntity) o;
        return id == entity.id && userId == entity.userId && Objects.equals(date, entity.date) && Objects.equals(time, entity.time) && Objects.equals(task, entity.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, task, userId);
    }

    @Override
    public String toString() {
        return "AccountantEntity{" +
                "id=" + id +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", task='" + task + '\'' +
                ", userId=" + userId +
                '}';
    }
}
