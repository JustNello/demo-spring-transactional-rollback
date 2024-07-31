package com.example.transactional.components;

import com.example.transactional.exceptions.MyCheckedException;
import com.example.transactional.persistence.PeopleRepository;
import com.example.transactional.persistence.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.transactional.TransactionalApplication.USERNAME;

@Component
@RequiredArgsConstructor
public class UserDao implements PeopleServiceSpec {

    private final PeopleRepository peopleRepository;

    private void doFail() {
        Person result = getDefault();
        result.setFailed(true);
        peopleRepository.save(result);
    }

    private Person getPerson(String name) {
        return peopleRepository.findByName(name);
    }

    @Override
    public Person getDefault() {
        return getPerson(USERNAME);
    }

    @Override
    public Person getPersonButThrowsRuntimeExceptionThatWillTriggerRollback(String name) {
        try {
            throw new RuntimeException("Simulate a RuntimeException");
        } catch (Exception e) {
            doFail();
            // We are throwing a RuntimeException, so @Transactional PeopleService
            // will roll back everything we've done withing the doFail() method
            throw e;
        }
    }

    @Override
    public Person getPersonButThrowsCheckedExceptionSoNoRollback(String name) throws MyCheckedException {
        try {
            throw new RuntimeException("Simulate a RuntimeException");
        } catch (Exception e) {
            doFail();
            throw new MyCheckedException(e.getMessage());
        }
    }
}
