package in.n4smh.microservices.person.query.service;

import java.util.List;

import in.n4smh.microservices.person.shared.dto.model.PersonRole;
import in.n4smh.microservices.person.shared.dto.model.Role;

public interface IPersonRoleService {

	List<Role> listAllRoles();

	PersonRole getPersonRoles(String id) throws Exception;

	List<String> fetchPersonsIdForRole(String roleId) throws Exception;

}
