package org.fasttrackit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    void itShouldCreatePerson() {
        Person person = new Person("Paul", 21, 0);
        Assertions.assertEquals("Paul", person.getName());
        Assertions.assertEquals(21, person.getAge());
        Assertions.assertEquals(0, person.getId());
    }

    @Test
    void testValidName() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            new Person(null, 10, 0);
        });
    }

    @Test
    void testAgeTooLow() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            new Person("Paul", -1, 0);
        });
    }

    @Test
    void testAgeTooHigh() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            new Person("Paul", 121, 0);
        });
    }
}
