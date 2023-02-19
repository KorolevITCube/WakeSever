package com.korolev.wake.server;

import com.korolev.wake.model.MonthDTO;
import com.korolev.wake.model.MonthTrace;
import com.korolev.wake.model.User;
import com.korolev.wake.repository.IRepositoryMonth;
import com.korolev.wake.repository.IRepositoryUser;
import com.korolev.wake.repository.UserRepository;
import com.korolev.wake.services.IMonthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class CalendarController {

    @Autowired
    @Qualifier("userDAO")
    private IRepositoryUser userRepository;

    @Autowired
    private IMonthService monthService;

    @GetMapping("/month")
    public MonthTrace getMonth(@RequestParam(name = "month") Optional<Integer> month, @RequestParam(name = "year") Optional<Integer> year){
        return monthService.get(month.orElseGet(() -> Calendar.getInstance().get(Calendar.MONTH)),year.orElseGet(() -> Calendar.getInstance().get(Calendar.YEAR)));
    }

    @PostMapping("/month/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody MonthDTO monthTraceDTO){
        monthService.update(monthTraceDTO);
    }

    //todo - delete this endpoint
    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
