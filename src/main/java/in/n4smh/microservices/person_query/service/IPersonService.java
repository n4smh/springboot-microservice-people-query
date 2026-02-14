package in.n4smh.microservices.person_query.service;

import java.util.List;

import in.n4smh.microservices.person_shared.dto.model.Person;

public interface IPersonService {

	List<Person> listPersons(boolean onlyActive, boolean onlyInactive) throws Exception;

	Person getPerson(String id) throws Exception;

	List<Person> getPersonsWithRole(String roleId) throws Exception;

}
