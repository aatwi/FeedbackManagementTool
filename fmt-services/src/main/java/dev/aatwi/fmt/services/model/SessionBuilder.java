package dev.aatwi.fmt.services.model;

import java.sql.Date;

public final class SessionBuilder
{
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;


    public static SessionBuilder newSessionBuilder()
    {
        return new SessionBuilder();
    }


    public SessionBuilder withId(Long id)
    {
        this.id = id;
        return this;
    }


    public SessionBuilder withName(String name)
    {
        this.name = name;
        return this;
    }


    public SessionBuilder withStartDate(Date startDate)
    {
        this.startDate = startDate;
        return this;
    }


    public SessionBuilder withEndDate(Date endDate)
    {
        this.endDate = endDate;
        return this;
    }


    public Session build()
    {
        if (id == 0)
        {
            return new Session(name, startDate, endDate);
        }
        return new Session(id, name, startDate, endDate);

    }
}
