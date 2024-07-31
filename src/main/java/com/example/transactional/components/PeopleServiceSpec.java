package com.example.transactional.components;

import com.example.transactional.exceptions.MyCheckedException;
import com.example.transactional.persistence.Person;

public interface PeopleServiceSpec {

    public Person getDefault();

    public Person getPersonButThrowsRuntimeExceptionThatWillTriggerRollback(String name);

    public Person getPersonButThrowsCheckedExceptionSoNoRollback(String name) throws MyCheckedException;

}
