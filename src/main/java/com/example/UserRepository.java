package com.example;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    String searchUsersByAttributes(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) String wiek) {
        List<User> foundUsers = new ArrayList<> ();

        if (imie.isEmpty () && nazwisko.isEmpty () && wiek.isEmpty ())
            return "Musisz podać dane użytkownika, którego chcesz znaleźć";
        else if (!imie.isEmpty ()) foundUsers = searchByFirstName (imie);
        else if (!nazwisko.isEmpty ()) foundUsers = searchByLastName (nazwisko);
        else if (!wiek.isEmpty ()) foundUsers = searchByAge (wiek);

        if (foundUsers.isEmpty ()) return "Nie znaleziono tego użytkownika..";
        else return "Użytkownik został znaleziony!<br/> " + foundUsers;
    }

    String removeUsersByAttributes(@RequestParam(required = false) String imie, @RequestParam(required = false) String nazwisko, @RequestParam(required = false) String wiek) {
        List<User> removedUsers = new ArrayList<> ();

        if (imie.isEmpty () && nazwisko.isEmpty () && wiek.isEmpty ())
            return "Musisz podać dane użytkownika, którego chcesz usunąć";
        else if (!imie.isEmpty ()) {
            removedUsers = searchByFirstName (imie);
            removeUserFromList (removedUsers);
        } else if (!nazwisko.isEmpty ()) {
            removedUsers = searchByLastName (nazwisko);
            removeUserFromList (removedUsers);
        } else if (!wiek.isEmpty ()) {
            removedUsers = searchByAge (wiek);
            removeUserFromList (removedUsers);
        }

        if (removedUsers.isEmpty ()) return "Nie znaleziono tego użytkownika..";
        else return "Użytkownik został usunięty! " + removedUsers;
    }

    private List<User> searchByAge(String wiek) {
        List<User> foundUsers;
        int wiekInt = Integer.parseInt (wiek);
        foundUsers = users.stream ().
                filter (user -> user.getWiek () == wiekInt)
                .collect (Collectors.toList ());
        return foundUsers;
    }

    private List<User> searchByLastName(String nazwisko) {
        List<User> foundUsers = users.stream ()
                .filter (user -> user.getNazwisko ()
                .equalsIgnoreCase (nazwisko))
                .collect (Collectors.toList ());
        return foundUsers;
    }

    private List<User> searchByFirstName(String imie) {
        List<User> foundUsers = users.stream ()
                .filter (user -> user.getImie ()
                .equalsIgnoreCase (imie))
                .collect (Collectors.toList ());
        return foundUsers;

    }

    private void removeUserFromList(List<User> removedUsers) {
        removedUsers.forEach (user -> users.remove (user));
    }
}
