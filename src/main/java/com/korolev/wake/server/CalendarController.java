package com.korolev.wake.server;

import com.korolev.wake.model.User;
import com.korolev.wake.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CalendarController {

    @Autowired()
    @Qualifier("userDAO")
    private UserRepository userRepository;

    @GetMapping("/home")
    public String stayHome(){
        return "u a at the home page ";
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
