package de.bowo.kick.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@NoRepositoryBean
public interface NonParticipantRepository extends UserBaseRepository {

}
