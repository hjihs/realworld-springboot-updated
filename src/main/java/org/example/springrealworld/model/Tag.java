package org.example.springrealworld.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles;

    // Constructors, Getters, and Setters
}

