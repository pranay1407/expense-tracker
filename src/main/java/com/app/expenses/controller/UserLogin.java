package com.app.expenses.controller;

import com.app.expenses.model.User;
import com.app.expenses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserLogin
{
    @Autowired
    UserService service;

    @GetMapping("/register")
    public String registerForm(User user, Model model)
    {
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result)
    {
        if (result.hasErrors())
        {
            System.out.println(result);
            return "register";
        }
        service.registerUser(user);
        return "register";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

}

