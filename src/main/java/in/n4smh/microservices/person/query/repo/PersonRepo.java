package in.n4smh.microservices.person.query.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.n4smh.microservices.person.shared.entity.PersonEntity;

@Repository
public interface PersonRepo extends CrudRepository<PersonEntity, String> {

	Iterable<PersonEntity> findByIsActive(int isActive);
}
