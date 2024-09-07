package ru.serpov.restaurantvoting.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Admin vote API")
public interface AdminVoteApi {

    @Operation(summary = "Get vote count for restaurant")
    @ApiResponse(responseCode = "200")
    int getVotesCount(@PathVariable @Parameter(description = "restaurant id") int id);
}
