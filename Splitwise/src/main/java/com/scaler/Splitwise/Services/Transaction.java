package com.scaler.Splitwise.Services;

import com.scaler.Splitwise.Models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private int amount;
    private User from;
    private User to;
}
