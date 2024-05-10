package com.example.hibernateexample.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 30, unique = false, nullable = false)
    private String name;
    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "surname", length = 30)
    private String surname;
    private int age;

    @Email
    @Size(min = 5, max = 150)
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @ManyToOne
    private Department department;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id", unique = true, nullable = false)
    private Account account;

    @OneToMany
    private Set<Item> items;

    @ManyToMany
    @JoinTable(
            name = "student_projects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Projects> projects;
}
