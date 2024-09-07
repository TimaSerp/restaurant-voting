package ru.serpov.restaurantvoting.controller.admin.impl;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.serpov.restaurantvoting.controller.AbstractController;
import ru.serpov.restaurantvoting.controller.admin.AdminUserApi;
import ru.serpov.restaurantvoting.model.dto.impl.UserDto;
import ru.serpov.restaurantvoting.service.impl.UserService;

import java.net.URI;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController extends AbstractController<UserDto> implements AdminUserApi {

    private final UserService userService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable @Parameter(name = "id", description = "user id") int id) {
        return getById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        deleteById(id);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return getAllDtos(Sort.by(ASC, "name", "email"));
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createWithLocation(@Valid @RequestBody UserDto user) {
        UserDto created = createNew(user).getBody();
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/admin/users/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserDto user, @PathVariable int id) {
        updateExisted(user, id);
    }

    @Override
    @GetMapping("/by-email")
    public ResponseEntity<UserDto> getByEmail(@RequestParam String email) {
        log.info("getByEmail {}", email);
        UserDto user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @Override
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        userService.setEnabled(id, enabled);
    }
}