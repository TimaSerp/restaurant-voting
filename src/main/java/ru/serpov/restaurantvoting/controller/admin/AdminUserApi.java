package ru.serpov.restaurantvoting.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.serpov.restaurantvoting.model.dto.impl.UserDto;

import java.util.List;

@Tag(name = "Admin users API")
public interface AdminUserApi {

    @Operation(summary = "Get user info")
    @ApiResponse(responseCode = "200", description = "Received user info")
    ResponseEntity<UserDto> get(@PathVariable @Parameter(description = "user id") int id);

    @Operation(summary = "Delete user")
    void delete(@PathVariable @Parameter(description = "user id") int id);

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Received users")
    ResponseEntity<List<UserDto>> getAll();

    @Operation(summary = "Create user")
    @ApiResponse(responseCode = "204", description = "Created new user")
    ResponseEntity<UserDto> createWithLocation(@Valid @RequestBody UserDto user);

    @Operation(summary = "Update existed user")
    @ApiResponse(responseCode = "200", description = "Updated existed user")
    void update(@Valid @RequestBody UserDto user, @PathVariable @Parameter(description = "user id") int id);

    @Operation(summary = "Get user by email")
    @ApiResponse(responseCode = "200")
    ResponseEntity<UserDto> getByEmail(@RequestParam String email);

    @Operation(summary = "Set user enabled")
    void enable(@PathVariable @Parameter(description = "user id") int id, @RequestParam boolean enabled);
}