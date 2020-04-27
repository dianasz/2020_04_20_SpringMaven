package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @ResponseBody
    String users() {
        List<User> users = userRepository.getAll ();
        String result = "";
        for (User user : users) {
            result += user.toString () + "</br>";
        }
        return result;
    }

    @RequestMapping("/add")
    public String add(@RequestParam(defaultValue = "Anonim", required = false) String imie, @RequestParam String nazwisko, @RequestParam int wiek) {
        if ("".equals (imie)) {
            return "redirect:/err.html";
        } else {
            User user = new User (imie, nazwisko, wiek);
            userRepository.add (user);
            return "redirect:/success.html";
        }
    }

    @ResponseBody
    @RequestMapping("/search")
    public String search(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) String wiek) {
        return userRepository.searchUsersByAttributes (imie, nazwisko, wiek);
    }

    @ResponseBody
    @RequestMapping("/remove")
    public String remove(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) String wiek) {
        return userRepository.removeUsersByAttributes (imie, nazwisko, wiek);
    }



}