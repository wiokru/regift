package com.regift.regift.utils;


import javax.persistence.*;
import java.io.Serializable;

//@Entity(name = "`user`")
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "uniqueEmailConstraint")})
//@NamedQueries({
//        @NamedQuery(name = "ContactList.findAll", query = "SELECT c FROM users c"),
//        @NamedQuery(name = "ContactList.findByFirstname", query = "SELECT c FROM ContactList c WHERE c.firstname = :firstname"),
//        @NamedQuery(name = "ContactList.findByLastname", query = "SELECT c FROM ContactList c WHERE c.lastname = :lastname"),
//        @NamedQuery(name = "ContactList.findByMobile", query = "SELECT c FROM ContactList c WHERE c.mobile = :mobile"),
//        @NamedQuery(name = "ContactList.findByEmail", query = "SELECT c FROM ContactList c WHERE c.email = :email")})

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
        @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "password")
    private String password;
    @Column(name = "city")
    private String city;

    public User() {}

    public User(String email, String name, String surname, String password, String city) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
