package ru.serpov.restaurant_voting.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.serpov.restaurant_voting.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity implements HasId {
    @NotBlank
    @Column(name = "description", nullable = false)
    @Size(min = 2, max = 120)
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    private Integer price;

    @Column(name = "registered", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    private LocalDate registered;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(Meal meal) {
        this(meal.id(), meal.getDescription(), meal.getPrice(), meal.getRegistered(), meal.getRestaurant());
    }

    public Meal(String description, int price, LocalDate registered, Restaurant restaurant) {
        this(null, description, price, registered, restaurant);
    }

    public Meal(Integer id, String description, int price, LocalDate registered, Restaurant restaurant) {
        super(id);
        this.description = description;
        this.price = price;
        this.registered = registered;
        this.restaurant = restaurant;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDate getRegistered() {
        return registered;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
