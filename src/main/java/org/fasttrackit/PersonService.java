package org.fasttrackit;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PersonService {
    private final List<Person> personList = new ArrayList<>();
    private final List<String> nameList = new ArrayList<>();
    private int id = 0;

    public Person addPerson(String name, int age) {
        if (nameList.contains(name)) {
            throw new InvalidParameterException("Person already exists"); // I need this for getPerson(String name) to work as intended
        }

        personList.add(new Person(name, age, id++));
        nameList.add(name);
        return new Person(name, age, id);
    }

    public Person removePerson(int id) {
        if (id < 0) {
            throw new InvalidParameterException("ID cannot be lower than 0");
        }
        for (Person person : personList) {
            if (person.getId() == id) {
                personList.remove(person);
                return person;
            }
        }
        throw new NoSuchElementException("Person not found");
    }

    public List<Person> getAllPersons() {
        return personList;
    }

    public List<Person> getPersonsOlderThan(int age) {
        if (age > 120 || age < 0) {
            throw new InvalidParameterException("Invalid age");
        }
        List<Person> personsOlderThan = new ArrayList<>();
        for (Person person : personList) {
            if (person.getAge() > age) {
                personsOlderThan.add(person);
            }
        }
        return personsOlderThan;
    }

    public List<String> getAllPersonNames() {
        List<String> personNames = new ArrayList<>();
        for (Person person : personList) {
            personNames.add(person.getName());
        }
        return personNames;
    }

    public Person getPerson(String name) {
        if (name == null) {
            throw new InvalidParameterException("Invalid name");
        }
        for (Person person : personList) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        throw new NoSuchElementException("Person not found");
    }

    public Person getPersonById(int id) {
        if (id < 0) {
            throw new InvalidParameterException("ID cannot be lower than 0");
        }
        for (Person person : personList) {
            if (person.getId() == id) {
                return person;
            }
        }
        throw new NoSuchElementException("Person not found");
    }
}
