package ru.serpov.restaurantvoting.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import ru.serpov.restaurantvoting.model.AuthUser;
import ru.serpov.restaurantvoting.model.dto.impl.UserDto;

@Tag(name = "Profile API")
public interface ProfileApi {

    @Operation(summary = "Get current user info")
    @ApiResponse(responseCode = "200")
    ResponseEntity<UserDto> get(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser);

    @Operation(summary = "Delete current user")
    @ApiResponse(responseCode = "200")
    void delete(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser);

    @Operation(summary = "Register new user")
    @ApiResponse(responseCode = "200")
    ResponseEntity<UserDto> register(@RequestBody UserDto user);

    @Operation(summary = "Update current user info")
    @ApiResponse(responseCode = "200")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto user, @AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser);
}
