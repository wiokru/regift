package com.regift.regift.utils;

public class UserBuilder {

    private String email;
    private String name;
    private String surname;
    private String password;
    private String city;

    public UserBuilder() {
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public User build() {
        User user = new User();
        user.setEmail(this.email);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setPassword(this.password);
        user.setCity(this.city);

        return user;
    }
}
