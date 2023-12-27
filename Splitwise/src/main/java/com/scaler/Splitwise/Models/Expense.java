package com.scaler.Splitwise.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Expense extends BaseModel{
    private String description;
    private int amount;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private Group group;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser>expenseUsers;
}
