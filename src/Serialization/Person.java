package Serialization;

import java.io.Serializable;

public class Person implements Serializable {
    private String name, lastname, user, password;
    private int age;

    public Person(String name, String lastname, int age, String user, String password) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.user = user;
        this.password = password;
    }

    // Métodos getter para acceder a los atributos
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
