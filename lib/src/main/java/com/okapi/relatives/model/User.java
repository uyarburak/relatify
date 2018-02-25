package com.okapi.relatives.model;

import java.util.List;

/**
 * Created by burak on 2/24/2018.
 */
public class User {
    Person user;
    List<Person> relatives;

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public List<Person> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Person> relatives) {
        this.relatives = relatives;
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
