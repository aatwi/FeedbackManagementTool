package dev.aatwi.fmtservices.dto;

import java.sql.Date;

public final class SessionDTOBuilder {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;


    private SessionDTOBuilder() {

    }


    public static SessionDTOBuilder newSessionDTOBuilder() {
        return new SessionDTOBuilder();
    }


    public SessionDTOBuilder withId(long id) {
        this.id = id;
        return this;
    }


    public SessionDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public SessionDTOBuilder withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }


    public SessionDTOBuilder withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }


    public SessionDTO build() {
        return new SessionDTO(id, name, startDate, endDate);
    }

}
