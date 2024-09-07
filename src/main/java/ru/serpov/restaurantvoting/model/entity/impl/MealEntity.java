package ru.serpov.restaurantvoting.model.entity.impl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.serpov.restaurantvoting.model.HasId;
import ru.serpov.restaurantvoting.model.entity.NamedEntity;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor
public class MealEntity extends NamedEntity implements HasId {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Builder
    public MealEntity(Integer id, String name, String description, int price, RestaurantEntity restaurant) {
        super(id, name);
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }
}
