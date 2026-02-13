package in.n4smh.microservices.person_query.repo;

import java.util.List;

import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.n4smh.microservices.person_shared.entity.PersonRoleEntity;

@Repository
public interface PersonRoleRepo extends CrudRepository<PersonRoleEntity, String> {

	@NativeQuery("SELECT person_id FROM people_role WHERE role_id = :role_id")
	List<String> fetchPersonsIdForRole(@Param("role_id") String roleId);
}
