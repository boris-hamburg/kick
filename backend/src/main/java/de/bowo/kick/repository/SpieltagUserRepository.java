package de.bowo.kick.repository;

import de.bowo.kick.dto.UserDto;
import de.bowo.kick.model.SpieltagUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpieltagUserRepository extends JpaRepository<SpieltagUser, SpieltagUser.SpieltagUserId> {

    @Query(nativeQuery = true, value = "select a.joined_date as joinedDate, b.first_name as firstName, b.last_name as lastName from spieltag_user a inner join user b on a.user_id = b.id")
    List<UserDto> findeTeilnehmer();

}
