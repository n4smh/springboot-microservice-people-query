package in.n4smh.microservices.person.query.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import in.n4smh.microservices.person.query.repo.PersonRoleRepo;
import in.n4smh.microservices.person.query.repo.RoleRepo;
import in.n4smh.microservices.person.shared.dto.model.PersonRole;
import in.n4smh.microservices.person.shared.dto.model.Role;
import in.n4smh.microservices.person.shared.entity.RoleEntity;
import in.n4smh.microservices.person.shared.mapper.IRoleMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonRoleService implements IPersonRoleService {

	private RoleRepo roleRepo;
	private PersonRoleRepo personRoleRepo;
	private IRoleMapper roleMapper;

	public PersonRoleService(final RoleRepo roleRepo, final PersonRoleRepo personRoleRepo,
			final IRoleMapper roleMapper) {
		this.roleRepo = roleRepo;
		this.personRoleRepo = personRoleRepo;
		this.roleMapper = roleMapper;
	}

	@Override
	public List<Role> listAllRoles() {
		List<RoleEntity> rolesEntity = roleRepo.findAll();

		List<Role> roles = rolesEntity.stream().map(roleEntity -> roleMapper.toPersonRole(roleEntity)).toList();

		return roles;
	}

	@Override
	public PersonRole getPersonRoles(String id) throws Exception {

		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		List<RoleEntity> rolesEntity = roleRepo.findByPersonId(id);

		List<Role> roles = rolesEntity.stream().map(roleEntity -> roleMapper.toPersonRole(roleEntity)).toList();

		return PersonRole.builder().personId(id).roles(roles).build();
	}

	@Override
	public List<String> fetchPersonsIdForRole(String roleId) throws Exception {

		if (StringUtils.isBlank(roleId)) {
			throw new Exception(String.format("Invalid Role ID: %s", roleId));
		}

		List<String> personsId = personRoleRepo.fetchPersonsIdForRole(roleId);

		return personsId;

	}

}
