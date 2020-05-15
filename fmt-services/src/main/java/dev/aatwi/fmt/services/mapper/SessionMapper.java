package dev.aatwi.fmt.services.mapper;

import dev.aatwi.fmt.services.dto.SessionDTO;
import dev.aatwi.fmt.services.dto.SessionDTOBuilder;
import dev.aatwi.fmt.services.model.Session;
import dev.aatwi.fmt.services.model.SessionBuilder;

public final class SessionMapper {
    private SessionMapper() {
    }

    public static Session convertSessionDTOToSession(SessionDTO sessionDTO) {
        return SessionBuilder.newSessionBuilder()
                .withId(sessionDTO.getId())
                .withName(sessionDTO.getName())
                .withStartDate(sessionDTO.getStartDate())
                .withEndDate(sessionDTO.getEndDate())
                .build();
    }

    public static SessionDTO convertSessionToSessionDTO(Session session) {
        return SessionDTOBuilder.newSessionDTOBuilder()
                .withId(session.getId())
                .withName(session.getName())
                .withStartDate(session.getStartDate())
                .withEndDate(session.getEndDate())
                .build();
    }
}
