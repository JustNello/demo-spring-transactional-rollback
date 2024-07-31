package com.example.transactional.components;

import com.example.transactional.exceptions.MyCheckedException;
import com.example.transactional.persistence.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
@Log
public class UserController implements PeopleServiceSpec {

    private final PeopleService peopleService;

    @Override
    @GetMapping
    public Person getDefault() {
        return peopleService.getDefault();
    }

    @Override
    @GetMapping("/rollback/{name}")
    public Person getPersonButThrowsRuntimeExceptionThatWillTriggerRollback(@PathVariable String name) {
        return peopleService.getPersonButThrowsRuntimeExceptionThatWillTriggerRollback(name);
    }

    @Override
    @GetMapping("/checked/{name}")
    public Person getPersonButThrowsCheckedExceptionSoNoRollback(@PathVariable String name) throws MyCheckedException {
        return peopleService.getPersonButThrowsCheckedExceptionSoNoRollback(name);
    }
}
