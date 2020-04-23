package com.example.demo.exercises.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FirstExerciseController {
    @GetMapping("/test/test1")
    @ResponseBody
    String hello(HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        return "Cześć tutaj Diana. Wszystko działa"; //http://localhost:8082/test/test1
    }
}
