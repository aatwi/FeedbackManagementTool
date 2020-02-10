package dev.aatwi.fmtservices.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "SESSION")
@Table(name = "SESSION")
public class Session {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "User password can not be null!")
    private String name;

    @Column(name = "START_DATE", nullable = false)
    @NotNull(message = "User password can not be null!")
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    @NotNull(message = "User password can not be null!")
    private Date endDate;


    public Session() {

    }


    public Session(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Session(long id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getStartDate() {
        return startDate;
    }


    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public Date getEndDate() {
        return endDate;
    }


    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) &&
                Objects.equals(name, session.name) &&
                Objects.equals(startDate.toString(), session.startDate.toString()) &&
                Objects.equals(endDate.toString(), session.endDate.toString());
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate);
    }
}
