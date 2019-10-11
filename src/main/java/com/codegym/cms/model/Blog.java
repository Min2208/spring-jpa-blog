package com.codegym.cms.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Blog")
public class Blog implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    private String description;

    public Blog(String name, String description, String body) {
        this.name = name;
        this.description = description;
        this.body = body;
    }

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Blog(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
