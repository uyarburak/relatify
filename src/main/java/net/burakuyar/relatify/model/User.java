package net.burakuyar.relatify.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

/**
 * Created by burak on 2/25/2018.
 */
public class User implements Serializable {
    private Person user;
    private List<Relative> relatives;
    private String email; // TODO: use EmailValidator.getInstance().isValid(email);
    private String pdfJSON;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPdfJSON() {
        return new String(Base64.getDecoder().decode(pdfJSON));
    }

    public void setPdfJSON(String pdfJSON) {
        this.pdfJSON = Base64.getEncoder().encodeToString(pdfJSON.getBytes());
    }

    @Override
    public int hashCode() {
        return this.user.hashCode();
    }
}
