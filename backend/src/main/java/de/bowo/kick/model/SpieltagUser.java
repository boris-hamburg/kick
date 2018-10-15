package de.bowo.kick.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity(name = "SpieltagUser")
public class SpieltagUser implements Serializable {

    @EmbeddedId
    public SpieltagUserId spieltagUserId;

    public Instant joinedDate;

    public SpieltagUser() {
    }

    public SpieltagUser(Spieltag spieltag, User user) {
        spieltagUserId = new SpieltagUser.SpieltagUserId();
        spieltagUserId.spieltagId = spieltag.getId();
        spieltagUserId.userId = user.getId();
        joinedDate = Instant.now();
    }

    @Override public String toString() {
        return "SpieltagUser{" + "spieltagUserId=" + spieltagUserId + ", joinedDate=" + joinedDate + '}';
    }

    @Embeddable
    public static class SpieltagUserId implements Serializable {

        public Long spieltagId;
        public Long userId;

        @Override public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            SpieltagUserId spieltagUserId = (SpieltagUserId) o;
            return Objects.equals(spieltagId, spieltagUserId.spieltagId) &&
                    Objects.equals(userId, spieltagUserId.userId);
        }

        @Override public int hashCode() {
            return Objects.hash(spieltagId, userId);
        }

        @Override public String toString() {
            return "SpieltagUserId{" + "spieltagId=" + spieltagId + ", userId=" + userId + '}';
        }
    }

}
