package ru.serpov.restaurantvoting.model.dto.impl;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.serpov.restaurantvoting.model.dto.CustomDto;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class VoteDto extends CustomDto {

    private int userId;

    private int restaurantId;

    @NotNull
    private Date voteDate;

    @Builder
    public VoteDto(Integer id, int userId, int restaurantId, Date voteDate) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.voteDate = voteDate;
    }

}
