package ru.serpov.restaurantvoting.model;

public interface HasIdAndEmail extends HasId {
    String getEmail();

    void setEmail(String email);
}
