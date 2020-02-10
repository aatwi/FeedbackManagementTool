package dev.aatwi.fmtservices.mapper;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;

import static dev.aatwi.fmtservices.dto.SessionDTOBuilder.newSessionDTOBuilder;
import static dev.aatwi.fmtservices.mapper.SessionMapper.convertSessionDTOToSession;
import static dev.aatwi.fmtservices.mapper.SessionMapper.convertSessionToSessionDTO;
import static dev.aatwi.fmtservices.model.SessionBuilder.newSessionBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionMapperTest {
    @Test
    public void
    it_should_convert_a_SessionDTO_class_to_a_Session_object() {
        assertEquals(
                newSessionBuilder()
                        .withId(123L)
                        .withName("SessionOne")
                        .withStartDate(getDate(2020, 1, 6))
                        .withEndDate(getDate(2020, 1, 26))
                        .build(),

                convertSessionDTOToSession(
                        newSessionDTOBuilder()
                                .withId(123L)
                                .withName("SessionOne")
                                .withStartDate(getDate(2020, 1, 6))
                                .withEndDate(getDate(2020, 1, 26))
                                .build()));
    }

    @Test
    public void
    it_should_convert_a_Session_instance_to_a_SessionDTO_instance() {
        assertEquals(
                newSessionDTOBuilder()
                        .withId(123L)
                        .withName("SessionOne")
                        .withStartDate(getDate(2020, 1, 6))
                        .withEndDate(getDate(2020, 1, 26))
                        .build(),

                convertSessionToSessionDTO(newSessionBuilder()
                        .withId(123L)
                        .withName("SessionOne")
                        .withStartDate(getDate(2020, 1, 6))
                        .withEndDate(getDate(2020, 1, 26))
                        .build()));
    }


    private Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);

        return new Date(cal.getTime().getTime());
    }
}
