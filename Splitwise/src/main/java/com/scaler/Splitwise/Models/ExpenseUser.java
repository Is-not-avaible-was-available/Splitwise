package com.scaler.Splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ExpenseUser extends BaseModel{

    @ManyToOne
    private Expense expense;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;
    private int amount;
}
