package com.example.transactional.exceptions;

// Implement your own checked exception by extending Exception
// Note that RuntimeException is a subclass of Exception too,
// but the Java Virtual Machine (JDK) handles RuntimeException
// so that the user is not required to catch them (i.e. they are "un-checked")
public class MyCheckedException extends Exception {

    public MyCheckedException(String message) {
        super(message);
    }
}
