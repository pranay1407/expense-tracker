package com.app.expenses.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Expense
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String expenseName;
    private Double amount;
    private LocalDate date;
    private String description;

    @ManyToOne(targetEntity = User.class, fetch= FetchType.EAGER)
    private User user;
}
