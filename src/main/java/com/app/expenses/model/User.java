package com.app.expenses.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Columns;

import java.util.Set;

@Data
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Username can not be empty")
    @Size(min = 4, max = 12, message = "Username between 4-12 characters")
    private String username;
    private String password;
    @Pattern(regexp = "(?i)^[a-z0-9+_.-]+@[a-z0-9.-]+\\.[a-z]{2,7}$" , message = "Invalid email")
    private String email;
    private String name;

    @OneToMany(targetEntity = Expense.class, cascade=CascadeType.ALL, mappedBy="user")
    private Set<Expense> postDetails;

}
