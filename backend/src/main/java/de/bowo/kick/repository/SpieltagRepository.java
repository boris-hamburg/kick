package de.bowo.kick.repository;

import de.bowo.kick.model.Spieltag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SpieltagRepository extends JpaRepository<Spieltag, Long> {

    Spieltag findFirstByOrderById();

}
