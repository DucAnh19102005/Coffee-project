package com.ducanh.coffee.controller;

import com.ducanh.coffee.entity.Account;
import com.ducanh.coffee.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = {"/login", "/"})
    public String showLogin(RedirectAttributes redirectAttributes) {
        return "/login";
    }

    @PostMapping("/auth")
    public String doLogin(@RequestParam("email") String email, @RequestParam("pass") String password, RedirectAttributes redirectAttributes, HttpSession httpSession){

        Account account = accountService.authenticate(email, password);

        if(account == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials!!!");
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("pass", password);
            return "redirect:/login";
        }

        httpSession.setAttribute("loggedInUser", account);

        return "redirect:/products";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
