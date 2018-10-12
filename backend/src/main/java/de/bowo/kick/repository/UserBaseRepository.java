package de.bowo.kick.repository;

import de.bowo.kick.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserBaseRepository extends JpaRepository<User, Long> {

    User findByFirstNameAndLastName(final String firstName, final String lastName);
}
