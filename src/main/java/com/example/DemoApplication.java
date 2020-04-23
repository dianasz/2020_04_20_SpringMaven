package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //nasza aplikacje jest springbootowa; wykorzystuje skanowanie naszego projektu;
public class DemoApplication {     //ta klasa zawsze musi byc na szczycie wszystkich pakietow!

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args); //klasa glowna jest DemoApplication; nic wiecej nas nie interesuje
    }

}
