package ru.serpov.restaurantvoting.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;

import java.util.List;

@Tag(name = "User restaurant API")
public interface UserRestaurantApi {

    @Operation(summary = "Get restaurant info")
    @ApiResponse(responseCode = "200", description = "Received restaurant info")
    ResponseEntity<RestaurantDto> get(@PathVariable @Parameter(description = "restaurant id") int id);

    @Operation(summary = "Get all restaurants info")
    @ApiResponse(responseCode = "200", description = "Received restaurants info")
    ResponseEntity<List<RestaurantDto>> getAll();
}
