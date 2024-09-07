package ru.serpov.restaurantvoting.model.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.serpov.restaurantvoting.model.entity.CustomEntity;

import java.util.Date;

@Entity
@Table(name = "restaurant_votes")
@Getter
@Setter
@NoArgsConstructor
public class VoteEntity extends CustomEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RestaurantEntity restaurant;

    @Temporal(TemporalType.DATE)
    @Column(name = "vote_date", nullable = false, columnDefinition = "date default now()")
    private Date voteDate;

    @Builder
    public VoteEntity(Integer id, UserEntity user, RestaurantEntity restaurant, Date voteDate) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDate = voteDate;
    }

}
