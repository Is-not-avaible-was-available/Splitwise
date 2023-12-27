package com.scaler.Splitwise.Repositories;

import com.scaler.Splitwise.Models.ExpenseUser;
import com.scaler.Splitwise.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {


    List<ExpenseUser> findAllByUser(User user);
}
