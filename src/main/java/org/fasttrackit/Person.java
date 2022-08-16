package org.fasttrackit;

public class Person {
    private final String name;
    private final int age;
    private final int id;

    public Person(String name, int age, int id) {
        if (name == null || age > 120 || age < 0) {
            throw new RuntimeException();
        }
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
