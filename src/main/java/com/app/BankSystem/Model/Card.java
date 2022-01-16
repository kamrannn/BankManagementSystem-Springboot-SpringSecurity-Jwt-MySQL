package com.app.BankSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    private String number;
    @NotNull
    private Long secretCode;
    @NotNull
    private Long cryptogram;
    private Boolean isBlocked;
    @NotNull
    private Double monthlyThreshold;
    private Boolean isVirtual;
    private Boolean isContactless;
    @NotNull
    private double totalAmount;
}
