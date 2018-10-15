package de.bowo.kick.dto;

import java.time.Instant;

public interface UserDto {
    String getFirstName();
    String getLastName();
    Instant getJoinedDate();
}
