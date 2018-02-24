package net.burakuyar.relatify.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by burak on 2/25/2018.
 */
public class User implements Serializable {
    private Person user;
    private List<Relative> relatives;

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    @Override
    public int hashCode() {
        return this.user.hashCode();
    }
}
