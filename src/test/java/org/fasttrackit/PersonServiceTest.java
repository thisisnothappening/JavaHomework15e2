package org.fasttrackit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService();
        personService.addPerson("Mircea", 21);
        personService.addPerson("Marcel", 15);
        personService.addPerson("Marius", 29);
    }

    @AfterEach
    void tearDown() {
        personService = null;
    }

    @Test
    void testAddPersonToTheList() {
        boolean containsPersons = false;
        int counter = 0;
        for (Person person : personService.getAllPersons()) {
            if (person.getName().equals("Mircea") && person.getAge() == 21 && person.getId() == 0) {
                counter++;
            }
            if (person.getName().equals("Marcel") && person.getAge() == 15 && person.getId() == 1) {
                counter++;
            }
            if (person.getName().equals("Marius") && person.getAge() == 29 && person.getId() == 2) {
                counter++;
            }
        }
        if (counter == 3) {
            containsPersons = true;
        }
        Assertions.assertTrue(containsPersons);
    }

    @Test
    void testAddPersonException() {
        boolean thrown = false;
        try {
            personService.addPerson("Mircea", 38);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testRemovePersonFromList() {
        personService.removePerson(1);
        boolean containsPersons = false;
        boolean personIsRemoved = false;
        int counter = 0;
        for (Person person : personService.getAllPersons()) {
            if (person.getName().equals("Mircea") && person.getAge() == 21 && person.getId() == 0) {
                counter++;
            }
            if (!person.getName().equals("Marcel") && !(person.getAge() == 15) && !(person.getId() == 1)) {
                personIsRemoved = true;
            }
            if (person.getName().equals("Marius") && person.getAge() == 29 && person.getId() == 2) {
                counter++;
            }
        }
        if (counter == 2) {
            containsPersons = true;
        }
        Assertions.assertTrue(containsPersons);
        Assertions.assertTrue(personIsRemoved);
    }

    @Test
    void testRemovePersonInvalidId() {
        boolean thrown = false;
        try {
            personService.removePerson(-1);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testRemovePersonNoSuchElementException() {
        boolean thrown = false;
        try {
            personService.removePerson(3);
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testGetPersonsOlderThanToList() {
        boolean containsPersons = false;
        boolean containsRemovedPersons = true;
        int counter = 0;
        for (Person person : personService.getPersonsOlderThan(20)) {
            if (person.getName().equals("Mircea") && person.getAge() == 21 && person.getId() == 0) {
                counter++;
            }
            if (!person.getName().equals("Marcel") && !(person.getAge() == 15) && !(person.getId() == 1)) {
                containsRemovedPersons = false;
            }
            if (person.getName().equals("Marius") && person.getAge() == 29 && person.getId() == 2) {
                counter++;
            }
        }
        if (counter == 2) {
            containsPersons = true;
        }
        Assertions.assertTrue(containsPersons);
        Assertions.assertFalse(containsRemovedPersons);
    }

    @Test
    void testGetPersonsOlderThanAgeTooHigh() {
        boolean thrown = false;
        try {
            personService.getPersonsOlderThan(121);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testGetPersonsOlderThanAgeTooLow() {
        boolean thrown = false;
        try {
            personService.getPersonsOlderThan(-1);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testGetAllPersonNames() {
        boolean containsPersons = false;
        int counter = 0;
        for (Person person : personService.getAllPersons()) {
            for (String name : personService.getAllPersonNames()) {
                if (person.getName().equals(name)) {
                    counter++;
                }
            }
        }
        if (counter == 3) {
            containsPersons = true;
        }
        Assertions.assertTrue(containsPersons);
    }

    @Test
    void testGetPerson() {
        boolean works = false;
        for (Person person : personService.getAllPersons()) {
            if (person.equals(personService.getPerson("Marius"))) {
                works = true;
            }
        }
        Assertions.assertTrue(works);
    }

    @Test
    void testGetPersonValidName() {
        boolean thrown = false;
        try {
            personService.getPerson(null);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testGetPersonNotFound() {
        boolean thrown = false;
        try {
            personService.getPerson("Marcelus");
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testGetPersonById() {
        boolean works = false;
        for (Person person : personService.getAllPersons()) {
            if (person.equals(personService.getPersonById(1))) {
                works = true;
            }
        }
        Assertions.assertTrue(works);
    }

    @Test
    void testGetPersonByIdInvalidId() {
        boolean thrown = false;
        try {
            personService.getPersonById(-1);
        } catch (InvalidParameterException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }

    @Test
    void testGetPersonByIdPersonNotFound() {
        boolean thrown = false;
        try {
            personService.getPersonById(3);
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        Assertions.assertTrue(thrown);
    }
}
