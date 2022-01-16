package com.app.BankSystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    private String id = UUID.randomUUID().toString();
    private LocalDateTime dateTime;
    private String label;
    @NotNull
    private Double amount;
    @NotNull
    private Double exchangeRateApplied;
    private Long creditorAccountId;
    @NotBlank
    private String creditorAccountName;
    @NotBlank
    private String country;
}
