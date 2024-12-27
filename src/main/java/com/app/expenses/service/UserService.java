package com.app.expenses.service;

import com.app.expenses.dao.UserRepository;
import com.app.expenses.model.User;
import com.app.expenses.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository repo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
        return "User registered successfully";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = repo.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(username);
        return new UserPrincipal(user);
    }

    public User findCurrentUser(String username)
    {
        return repo.findByUsername(username);
    }
}
