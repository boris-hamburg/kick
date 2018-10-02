package de.bowo.kick.repository;

import de.bowo.kick.model.NonParticipant;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NonParticipantRepository extends UserBaseRepository<NonParticipant> {

}
