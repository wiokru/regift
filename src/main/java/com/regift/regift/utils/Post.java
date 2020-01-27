package com.regift.regift.utils;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "posts")
public class Post implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    @Type(type = "text")
    private String description;
    @NotNull
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Post() {
    }

    public Post(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.creationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String displayCreationDate() {
        return this.creationDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
