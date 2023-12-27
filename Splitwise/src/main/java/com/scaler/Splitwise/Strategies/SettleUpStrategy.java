package com.scaler.Splitwise.Strategies;

import com.scaler.Splitwise.Models.Expense;
import com.scaler.Splitwise.Services.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SettleUpStrategy {
    public List<Transaction> settleUp(List<Expense>expenses);
}
