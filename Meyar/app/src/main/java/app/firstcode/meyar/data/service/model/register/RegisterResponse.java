
package app.firstcode.meyar.data.service.model.register;

public class RegisterResponse {


    private String status;
    private String message;


    public RegisterResponse() {
    }

    public RegisterResponse(String status, String message, app.firstcode.meyar.data.service.model.register.data data) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
