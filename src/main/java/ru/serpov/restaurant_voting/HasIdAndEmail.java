package ru.serpov.restaurant_voting;

public interface HasIdAndEmail extends HasId {
    String getEmail();
    void setEmail(String email);
}
