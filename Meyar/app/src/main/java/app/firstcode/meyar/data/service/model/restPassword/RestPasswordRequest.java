package app.firstcode.meyar.data.service.model.restPassword;

/**
 * Created by macbookpro on 7/15/2017 AD.
 */

public class RestPasswordRequest {

    private String Email;
    private String MobileID;

    public RestPasswordRequest(String email, String mobileID) {
        Email = email;
        MobileID = mobileID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileID() {
        return MobileID;
    }

    public void setMobileID(String mobileID) {
        MobileID = mobileID;
    }
}
