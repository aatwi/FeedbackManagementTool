package dev.aatwi.fmt.services.dto;

import java.sql.Date;
import java.util.Objects;

public final class SessionDTO {
    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;

    public SessionDTO(Long id, String name, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
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

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "SessionDTO{" +
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
        SessionDTO that = (SessionDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDate.toString(), that.startDate.toString()) &&
                Objects.equals(endDate.toString(), that.endDate.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate);
    }
}
