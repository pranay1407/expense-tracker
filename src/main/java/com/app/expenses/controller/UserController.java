package com.app.expenses.controller;

import com.app.expenses.model.Expense;
import com.app.expenses.model.User;
import com.app.expenses.service.ExpenseService;
import com.app.expenses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private ExpenseService service;
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePage()
    {
        return "home";
    }

    @GetMapping("/addExpense")
    public String expenseForm()
    {
        return "addExpenses";
    }

    @PostMapping("/addExpense")
    public String addExpense(@ModelAttribute("expense") Expense expense, Principal principal)
    {
        User user = userService.findCurrentUser(principal.getName());
        expense.setUser(user);
        service.addExpense(expense);
        return "redirect:/user/home";
    }
    
    @GetMapping("/listExpense")
    public String getAllExpenses(Model model, Principal principal)
    {
        List<Expense> expenses = service.getAllExpenses(principal.getName());
        model.addAttribute("expenses", expenses);
        return "listExpenses";
    }

    @PostMapping("/updateData")
    public String getUpdate(@RequestParam("updateId") Integer id, Model model)
    {
        Expense expense = service.fetchOnIdForUpdate(id);
        model.addAttribute("expense", expense);
        return "updateForm";
    }

    @PostMapping("/updateDataSave")
    public String updateSave(@ModelAttribute Expense expense, Principal principal)
    {
        User user = userService.findCurrentUser(principal.getName());
        expense.setUser(user);
        service.updateDataSave(expense);
        return "redirect:/user/home";
    }
    @PostMapping("/deleteData")
    public String updateSave(@RequestParam("deleteId") Integer id)
    {
        service.deleteExpense(id);

        return "redirect:/user/home";
    }



}
