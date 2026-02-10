package in.n4smh.microservices.people_query.service;

import java.util.List;

import in.n4smh.microservices.people_shared.dto.model.PersonRole;
import in.n4smh.microservices.people_shared.dto.model.Role;

public interface IPersonRoleService {

	List<Role> listAllRoles();

	PersonRole getPersonRoles(String id) throws Exception;

	List<String> fetchPersonsIdForRole(String roleId) throws Exception;

}
