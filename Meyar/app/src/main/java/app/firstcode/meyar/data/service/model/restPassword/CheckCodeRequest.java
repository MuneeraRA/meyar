package app.firstcode.meyar.data.service.model.restPassword;

/**
 * Created by macbookpro on 7/16/2017 AD.
 */

public class CheckCodeRequest {

    private String Code ;
    private String MobileID;

    public CheckCodeRequest() {
    }

    public CheckCodeRequest(String code, String mobileID) {
        Code = code;
        MobileID = mobileID;
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
}
