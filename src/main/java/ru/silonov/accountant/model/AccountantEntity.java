package ru.silonov.accountant.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Random;

@Entity
@Table(name = "accountant", schema = "public", catalog = "accountant")
public class AccountantEntity {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id = System.currentTimeMillis() + new Random().nextInt(10000);
    @Basic
    @Column(name = "date_")
    private Date date = new Date(System.currentTimeMillis());
    @Basic
    @Column(name = "time_")
    private String time = java.time.LocalDateTime.now().toLocalTime().toString();
    @Basic
    @Column(name = "task")
    private String task;
    @Basic
    @Column(name = "user_id")
    private Long userId;

    public AccountantEntity(long id) {
    }

    public AccountantEntity(String task, Long userId) {
        this.task = task;
        this.userId = userId;
    }

//    public AccountantEntity(Date date, String time, String task, Long userId) {
//        this.date = date;
//        this.time = time;
//        this.task = task;
//        this.userId = userId;
//    }

    public AccountantEntity(long id, Date date, String time, String task, Long userId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.task = task;
        this.userId = userId;
    }

    public AccountantEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountantEntity that = (AccountantEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.toString().equals(that.date.toString()) : that.date.toString() != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (task != null ? !task.equals(that.task) : that.task != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
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
