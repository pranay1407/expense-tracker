package com.app.expenses.service;

import com.app.expenses.dao.ExpensesRepository;
import com.app.expenses.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService
{
    @Autowired
    private ExpensesRepository repo;
    public void addExpense(Expense expense)
    {
        repo.save(expense);
    }

    public List<Expense> getAllExpenses(String user)
    {
        Iterable<Expense> expensesList = repo.findAll();
        Iterator<Expense> expenses = expensesList.iterator();
        List<Expense> newExpenseList = new ArrayList<>();
        while (expenses.hasNext())
        {
            Expense expenseObj = expenses.next();
            if(expenseObj.getUser().getUsername().equals(user))
            {
                newExpenseList.add(expenseObj);
            }
        }
        return newExpenseList;


      //  return (List<Expense>) repo.findAll();
    }

    public Expense fetchOnIdForUpdate(Integer id)
    {
        Optional<Expense> update = repo.findById(id);
        return update.get();

    }

    public void updateDataSave(Expense expense)
    {
        repo.save(expense);
    }

    public void deleteExpense(Integer id)
    {
        repo.deleteById(id);
    }

}
