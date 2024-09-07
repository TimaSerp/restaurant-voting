package ru.serpov.restaurantvoting.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import ru.serpov.restaurantvoting.model.AuthUser;
import ru.serpov.restaurantvoting.model.dto.impl.VoteDto;

@Tag(name = "User vote API")
public interface UserVoteApi {

    @Operation(summary = "Get vote of current user today")
    @ApiResponse(responseCode = "200")
    ResponseEntity<VoteDto> getVote(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser);

    @Operation(summary = "Vote for restaurant", description = "can vote only before 11 am each day and only 1 user - 1 restaurant")
    void vote(@PathVariable @Parameter(description = "restaurant id") int id, @AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser);

    @Operation(summary = "Delete vote of current user")
    void unvote(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser authUser);
}
