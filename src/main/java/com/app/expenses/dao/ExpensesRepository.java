package com.app.expenses.dao;

import com.app.expenses.model.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpensesRepository extends CrudRepository<Expense,Integer>
{

}
