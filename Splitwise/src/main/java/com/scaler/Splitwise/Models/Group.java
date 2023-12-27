package com.scaler.Splitwise.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Group extends BaseModel{
    private String  groupName;
    @ManyToMany
    private List<User>members;
    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
    @ManyToOne
    private User createdBy;
}
