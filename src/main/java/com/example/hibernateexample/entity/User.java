package com.example.hibernateexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String login;

    @NotNull
    @Size(min = 8, max = 100)
    @Column(length = 100, unique = false, nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @NotNull
    @Column(nullable = false)
    private Boolean activated = false;

    private String langKey;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
                        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                        inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();
}
