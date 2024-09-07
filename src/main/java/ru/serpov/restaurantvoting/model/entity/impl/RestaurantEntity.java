package ru.serpov.restaurantvoting.model.entity.impl;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.serpov.restaurantvoting.model.HasId;
import ru.serpov.restaurantvoting.model.entity.NamedEntity;

import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
@Setter
@Getter
@NoArgsConstructor
public class RestaurantEntity extends NamedEntity implements HasId {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MealEntity> meals;

    @Builder
    public RestaurantEntity(Integer id, String name, String description, String address, List<MealEntity> meals) {
        super(id, name);
        this.description = description;
        this.address = address;
        this.meals = meals;
    }
}
