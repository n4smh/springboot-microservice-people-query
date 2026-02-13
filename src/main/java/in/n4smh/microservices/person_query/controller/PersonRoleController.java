package in.n4smh.microservices.person_query.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.n4smh.microservices.person_query.config.TraceIdFilter;
import in.n4smh.microservices.person_query.service.IPersonRoleService;
import in.n4smh.microservices.person_query.shared.ApiResponseBody;
import in.n4smh.microservices.person_shared.dto.model.PersonRole;
import in.n4smh.microservices.person_shared.dto.model.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/person")
@Tag(name = "Person Role", description = "Person Role operations")
public class PersonRoleController {

	private IPersonRoleService personRoleService;
	private TraceIdFilter traceIdFilter;

	public PersonRoleController(final IPersonRoleService personRoleService, final TraceIdFilter traceIdFilter) {
		this.personRoleService = personRoleService;
		this.traceIdFilter = traceIdFilter;
	}

	@GetMapping("/roles")
	public ResponseEntity<ApiResponseBody<List<Role>>> listAllRoles() {

		List<Role> roles = personRoleService.listAllRoles();

		return ResponseEntity
				.ok(ApiResponseBody.<List<Role>>builder().traceId(traceIdFilter.getTraceId()).data(roles).build());
	}

	@GetMapping("/{id}/roles")
	public ResponseEntity<ApiResponseBody<PersonRole>> getPersonRoles(@PathVariable String id) throws Exception {

		PersonRole roles = personRoleService.getPersonRoles(id);

		return ResponseEntity
				.ok(ApiResponseBody.<PersonRole>builder().traceId(traceIdFilter.getTraceId()).data(roles).build());
	}

}
