package com.tpe.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Course {

    @Id
    @GeneratedValue(generator = "generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generator",sequenceName = "course_seq",initialValue = 100,allocationSize = 5)
    private Long id;

    @NotBlank(message = "Name can not be blank!")
    @Column(nullable = false)
    private String name;

    private String description;

    @NotNull(message = "Please provide duration")
    @Column(nullable = false)
    private int duration;//saat

    //getter-setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}