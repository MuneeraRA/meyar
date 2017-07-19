package app.firstcode.meyar.data.service.model.restPassword;

import com.google.gson.annotations.SerializedName;

/**
 * Created by macbookpro on 7/15/2017 AD.
 */

public class RestPasswordResponse {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
