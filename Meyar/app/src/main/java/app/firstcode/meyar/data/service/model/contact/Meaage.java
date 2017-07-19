package app.firstcode.meyar.data.service.model.contact;

/**
 * Created by macbookpro on 7/14/2017 AD.
 */

public class Meaage {

    private String Email ;
    private String Details;

    public Meaage() {
    }

    public Meaage(String email, String details) {
        Email = email;
        Details = details;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
