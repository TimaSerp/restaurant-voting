package ru.serpov.restaurant_voting.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.serpov.restaurant_voting.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity implements HasId {

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(String description, String address) {
        this(null, null, description, address);
    }

    public Restaurant(Integer id, String name, String description, String address) {
        super(id, name);
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
