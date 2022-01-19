package com.app.BankSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String birthDate;
    @NotBlank
    private String country;
    @Column(unique = true)
    @NotNull
    private Long passportNumber;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    @NotBlank
    @Email
    private String email;
    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    @Length(min = 5)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Card> cards = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Operation> operations = new ArrayList<>();
}
