package de.bowo.kick.dto;

import java.util.ArrayList;
import java.util.List;

public class SpieltagDto {

    private List<UserDto> teilnehmer = new ArrayList<>();

    public void setTeilnehmer(final List<UserDto> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    public List<UserDto> getTeilnehmer() {
        return teilnehmer;
    }
}
