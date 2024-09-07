package ru.serpov.restaurantvoting.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.serpov.restaurantvoting.model.dto.impl.RestaurantDto;

@Tag(name = "Admin restaurant API")
public interface AdminRestaurantApi {

    @Operation(summary = "Create new restaurant")
    @ApiResponse(responseCode = "201", description = "Created restaurant info")
    ResponseEntity<RestaurantDto> create(@RequestBody RestaurantDto restaurant);

    @Operation(summary = "Delete restaurant")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable @Parameter(description = "restaurant id") int id);

    @Operation(summary = "Update existed restaurant")
    @ApiResponse(responseCode = "200", description = "Updated restaurant info")
    ResponseEntity<RestaurantDto> update(@RequestBody RestaurantDto restaurant, @PathVariable @Parameter(description = "restaurant id") int id);
}
