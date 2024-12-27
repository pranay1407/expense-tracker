package com.app.expenses.dao;


import com.app.expenses.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>
{
    User findByUsername(String username);
}
