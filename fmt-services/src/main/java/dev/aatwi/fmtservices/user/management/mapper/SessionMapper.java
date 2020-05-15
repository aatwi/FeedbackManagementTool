package dev.aatwi.fmtservices.user.management.mapper;

import dev.aatwi.fmtservices.dto.SessionDTO;
import dev.aatwi.fmtservices.model.Session;

import static dev.aatwi.fmtservices.dto.SessionDTOBuilder.newSessionDTOBuilder;
import static dev.aatwi.fmtservices.model.SessionBuilder.newSessionBuilder;

public final class SessionMapper {
    private SessionMapper() {
    }

    public static Session convertSessionDTOToSession(SessionDTO sessionDTO) {
        return newSessionBuilder()
                .withId(sessionDTO.getId())
                .withName(sessionDTO.getName())
                .withStartDate(sessionDTO.getStartDate())
                .withEndDate(sessionDTO.getEndDate())
                .build();
    }

    public static SessionDTO convertSessionToSessionDTO(Session session) {
        return newSessionDTOBuilder()
                .withId(session.getId())
                .withName(session.getName())
                .withStartDate(session.getStartDate())
                .withEndDate(session.getEndDate())
                .build();
    }
}
