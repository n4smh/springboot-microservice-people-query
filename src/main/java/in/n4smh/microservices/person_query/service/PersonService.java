package in.n4smh.microservices.person_query.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import in.n4smh.microservices.person_query.repo.PersonRepo;
import in.n4smh.microservices.person_shared.dto.model.Person;
import in.n4smh.microservices.person_shared.dto.model.PersonRole;
import in.n4smh.microservices.person_shared.entity.PersonEntity;
import in.n4smh.microservices.person_shared.mapper.IPersonMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService implements IPersonService {

	private PersonRepo personRepo;
	private IPersonRoleService personRoleService;
	private IPersonMapper personMapper;

	public PersonService(final PersonRepo personRepo, final IPersonRoleService personRoleService,
			final IPersonMapper personMapper) {
		this.personRepo = personRepo;
		this.personRoleService = personRoleService;
		this.personMapper = personMapper;
	}

	@Override
	public List<Person> listPersons(boolean onlyActive, boolean onlyInactive) throws Exception {

		List<Person> personResponses = new ArrayList<>();

		Iterable<PersonEntity> persons;

		if (onlyActive && !onlyInactive) {
			persons = personRepo.findByIsActive(1);

		} else if (!onlyActive && onlyInactive) {
			persons = personRepo.findByIsActive(0);

		} else {
			persons = personRepo.findAll();

		}

		persons.forEach((p) -> {
			PersonRole personRole = null;

			try {
				personRole = personRoleService.getPersonRoles(p.getId());

			} catch (Exception e) {
				log.error(e.getMessage());
			}

			Person person = personMapper.toPersonResponse(p);
			person.setRoles(personRole.getRoles());

			personResponses.add(person);

		});

		return personResponses;
	}

	@Override
	public Person getPerson(String id) throws Exception {

		if (StringUtils.isBlank(id)) {
			throw new Exception(String.format("Invalid Person ID: %s", id));
		}

		Optional<PersonEntity> personEntity = personRepo.findById(id);

		PersonRole personRole = personRoleService.getPersonRoles(id);

		Person person = personMapper.toPersonResponse(
				personEntity.orElseThrow(() -> new Exception(String.format("Invalid Person ID: %s", id))));

		person.setRoles(personRole.getRoles());

		return person;
	}

	@Override
	public List<Person> getPersonsWithRole(String roleId) throws Exception {

		List<String> personsId = personRoleService.fetchPersonsIdForRole(roleId);
		List<Person> personResponses = new ArrayList<>();

		// Iterable<PersonEntity> persons = personRepo.findAllById(personsId);
		// persons.forEach(p -> personResponses.add(personMapper.toPersonResponse(p)));

		personResponses = personsId.stream().map((id) -> {
			try {
				return getPerson(id);

			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}

		}).filter(person -> Objects.nonNull(person)).toList();

		return personResponses;
	}

}
