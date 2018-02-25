package net.burakuyar.relatify.model;

import java.io.Serializable;

/**
 * Created by burak on 2/25/2018.
 */
public class Relative implements Serializable{
    private Person person;
    private String relation;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
