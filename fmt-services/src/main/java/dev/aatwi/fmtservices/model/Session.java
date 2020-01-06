package dev.aatwi.fmtservices.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "SESSION")
@Table(
    name = "SESSION"
)
@SequenceGenerator(name = "session", allocationSize = 100)
public class Session
{

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session")
    @Id
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


    public Session()
    {

    }


    public Session(String name, Date startDate, Date endDate)
    {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Session(long id, String name, Date startDate, Date endDate)
    {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public Date getStartDate()
    {
        return startDate;
    }


    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }


    public Date getEndDate()
    {
        return endDate;
    }


    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }


    @Override public String toString()
    {
        return "Session{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            '}';
    }


    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) &&
            Objects.equals(name, session.name) &&
            Objects.equals(startDate, session.startDate) &&
            Objects.equals(endDate, session.endDate);
    }


    @Override public int hashCode()
    {
        return Objects.hash(id, name, startDate, endDate);
    }
}
