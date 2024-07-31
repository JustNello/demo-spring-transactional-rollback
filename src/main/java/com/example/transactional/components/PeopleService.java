package com.example.transactional.components;

import com.example.transactional.exceptions.MyCheckedException;
import com.example.transactional.persistence.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PeopleService implements PeopleServiceSpec {

    // Delegate to userDao to see how Exceptions
    // are handled by a class that is not @Transactional annotated
    private final UserDao userDao;

    @Override
    public Person getDefault() {
        return userDao.getDefault();
    }

    @Override
    public Person getPersonButThrowsRuntimeExceptionThatWillTriggerRollback(String name) {
        return userDao.getPersonButThrowsRuntimeExceptionThatWillTriggerRollback(name);
    }

    @Override
    public Person getPersonButThrowsCheckedExceptionSoNoRollback(String name) throws MyCheckedException {
        return userDao.getPersonButThrowsCheckedExceptionSoNoRollback(name);
    }
}
