package de.bowo.kick.repository;

import de.bowo.kick.model.Participant;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ParticipantRepository extends UserBaseRepository<Participant> {

}
