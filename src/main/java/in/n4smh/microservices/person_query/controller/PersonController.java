package in.n4smh.microservices.person_query.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.n4smh.microservices.person_query.config.TraceIdFilter;
import in.n4smh.microservices.person_query.service.IPersonService;
import in.n4smh.microservices.person_query.shared.ApiResponseBody;
import in.n4smh.microservices.person_shared.dto.model.Person;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/person")
@Tag(name = "Person", description = "Person operations")
public class PersonController {

	private IPersonService personService;
	private TraceIdFilter traceIdFilter;

	public PersonController(final IPersonService personService, final TraceIdFilter traceIdFilter) {
		this.personService = personService;
		this.traceIdFilter = traceIdFilter;
	}

	@GetMapping
	public ResponseEntity<ApiResponseBody<List<Person>>> listPersons(
			@RequestParam(defaultValue = "false") boolean onlyActive,
			@RequestParam(defaultValue = "false") boolean onlyInactive) throws Exception {

		log.info("listPersons");
		log.debug("listPersons");

		List<Person> persons = personService.listPersons(onlyActive, onlyInactive);

		ApiResponseBody<List<Person>> p = ApiResponseBody.<List<Person>>builder().traceId(traceIdFilter.getTraceId())
				.data(persons).build();

		return ResponseEntity.ok(p);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseBody<Person>> getPerson(@PathVariable String id) throws Exception {

		Person person = personService.getPerson(id);

		return ResponseEntity
				.ok(ApiResponseBody.<Person>builder().traceId(traceIdFilter.getTraceId()).data(person).build());

	}

	@GetMapping("/roles/{roleId}")
	public ResponseEntity<ApiResponseBody<List<Person>>> getPersonsWithRole(@PathVariable String roleId)
			throws Exception {

		List<Person> roles = personService.getPersonsWithRole(roleId);

		return ResponseEntity
				.ok(ApiResponseBody.<List<Person>>builder().traceId(traceIdFilter.getTraceId()).data(roles).build());
	}

}
