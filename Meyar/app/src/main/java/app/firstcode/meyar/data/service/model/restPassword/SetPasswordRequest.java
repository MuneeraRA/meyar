package app.firstcode.meyar.data.service.model.restPassword;

/**
 * Created by macbookpro on 7/17/2017 AD.
 */

public class SetPasswordRequest {

    private String Code ;
    private String MobileID;
    private String Password;

    public SetPasswordRequest() {
    }

    public SetPasswordRequest(String code, String mobileID, String password) {
        Code = code;
        MobileID = mobileID;
        Password = password;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMobileID() {
        return MobileID;
    }

    public void setMobileID(String mobileID) {
        MobileID = mobileID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
