package com.scaler.Splitwise.Services;

import com.scaler.Splitwise.Exceptions.GroupNotFoundException;
import com.scaler.Splitwise.Exceptions.UserNotFoundException;
import com.scaler.Splitwise.Models.Expense;
import com.scaler.Splitwise.Models.ExpenseUser;
import com.scaler.Splitwise.Models.Group;
import com.scaler.Splitwise.Models.User;
import com.scaler.Splitwise.Repositories.ExpenseRepository;
import com.scaler.Splitwise.Repositories.ExpenseUserRepository;
import com.scaler.Splitwise.Repositories.GroupRepository;
import com.scaler.Splitwise.Repositories.UserRepository;
import com.scaler.Splitwise.Strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;

    @Autowired
    public SettleUpService(UserRepository userRepository,
                           ExpenseUserRepository expenseUserRepository,
                           SettleUpStrategy settleUpStrategy,
                           GroupRepository groupRepository
            , ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<Transaction> settleUpUser(Long userId) throws UserNotFoundException{

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User is not present");
        }
        User user = optionalUser.get();

        List<ExpenseUser>expenseUsers = expenseUserRepository.findAllByUser(user);

        List<Expense>expenses = new ArrayList<>();
        for(ExpenseUser expenseUser: expenseUsers){
            expenses.add(expenseUser.getExpense());
        }

        List<Transaction> transactions = settleUpStrategy.settleUp(expenses);

        List<Transaction> filteredTransactions = new ArrayList<>();

        for(Transaction transaction: transactions){
            if(transaction.getFrom().equals(user) || transaction.getTo().equals(user)){
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public List<Transaction> settleUpGroup(Long groupId) throws GroupNotFoundException{

        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if(optionalGroup.isEmpty()){
            throw new GroupNotFoundException("Group is not present!");
        }
        Group group = optionalGroup.get();
        List<Expense>expenses = expenseRepository.findAllByGroup(group);

        return settleUpStrategy.settleUp(expenses);
    }
}
