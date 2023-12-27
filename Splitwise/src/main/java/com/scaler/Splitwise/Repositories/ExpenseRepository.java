package com.scaler.Splitwise.Repositories;

import com.scaler.Splitwise.Models.Expense;
import com.scaler.Splitwise.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByGroup(Group group);
}
