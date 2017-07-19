package app.firstcode.meyar.data.service.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diaa on 7/15/2017.
 */

public class RateResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
