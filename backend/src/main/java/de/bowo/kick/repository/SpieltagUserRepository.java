package de.bowo.kick.repository;

import de.bowo.kick.model.SpieltagUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpieltagUserRepository extends JpaRepository<SpieltagUser, SpieltagUser.SpieltagUserId> {

}
