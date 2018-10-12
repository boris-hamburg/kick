package de.bowo.kick.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Spieltag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "spieltag_user",
            joinColumns = @JoinColumn(referencedColumnName = "id", name = "spieltagId"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id", name = "userId"))
    public List<User> teilnehmer;

    public Spieltag() {
    }

    public Long getId() {
        return id;
    }
}
