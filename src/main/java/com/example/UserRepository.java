package com.example;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<User> users;

    public UserRepository(){
        users = new ArrayList<>();
        users.add(new User("Diana", "Lewando", 28));
        users.add(new User("Patryk", "Pomidor", 3));
        users.add(new User("Czesiek", "Czosnek", 2));
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(User user){
        users.remove(user);
    }
}
