package com.voting.controller;

import com.voting.model.User;
import com.voting.service.VotingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired private VotingService votingService;

    @GetMapping("/")
    public String showLogin() { return "login"; }

    @GetMapping("/register")
    public String showRegister(Model model) { 
        model.addAttribute("user", new User()); 
        return "register"; 
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (votingService.findUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists!");
            return "register";
        }
        votingService.registerUser(user);
        return "redirect:/?success=Registered successfully. Please login.";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = votingService.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid Email or Password!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String editProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/";
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute User updatedUser, HttpSession session) {
        User sessionUser = (User) session.getAttribute("loggedInUser");
        if (sessionUser == null) return "redirect:/";
        
        sessionUser.setName(updatedUser.getName());
        sessionUser.setPassword(updatedUser.getPassword());
        votingService.updateUser(sessionUser);
        
        return "redirect:/dashboard?success=Profile updated successfully";
    }
}