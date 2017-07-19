package app.firstcode.meyar.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.firstcode.meyar.data.service.model.restPassword.RestPasswordRequest;
import app.firstcode.meyar.data.service.model.restPassword.RestPasswordResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import app.firstcode.meyar.OnDialogClickListener;
import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.base.BaseFragment;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    Button logIn;
    EditText edt_mail ;
    String mail , mobileId ;

    ApiInterface service ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initToolbar(R.string.restore_password_title);

        logIn = (Button) findViewById(R.id.login_button);
       edt_mail=(EditText)findViewById(R.id.user_email_edit);
        mobileId=getAdvertisingId();


        logIn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:

                mail=edt_mail.getText().toString().trim();

                if (validate(v,mail,mobileId)){
                    service= ApiClient.getClient().create(ApiInterface.class);
                    Call<RestPasswordResponse> call =service.requestPassword(new RestPasswordRequest(mail,mobileId));
                    call.enqueue(new Callback<RestPasswordResponse>() {
                        @Override
                        public void onResponse(Call<RestPasswordResponse> call, Response<RestPasswordResponse> response) {

                            if (response.code() == 200 && response.body().getStatus().equals("success")) {
                                showMessage(ForgetPasswordActivity.this, R.drawable.ic_check, R.string.we_send_active_code, R.string.done, new OnDialogClickListener() {
                                    @Override
                                    public void onClick() {
                                        Intent intent = new Intent(ForgetPasswordActivity.this,EnterPinCodeActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                });

                            }

                            else {

                                showMessage(ForgetPasswordActivity.this, R.drawable.ic_report, R.string.msg_wrong_mail, R.string.done, new OnDialogClickListener() {
                                    @Override
                                    public void onClick() {
                                    }
                                });

                            }

                            }

                        @Override
                        public void onFailure(Call<RestPasswordResponse> call, Throwable t) {

                            BaseActivity.showMessage(ForgetPasswordActivity.this, R.drawable.ic_report,R.string.msg_checking_network , R.string.done, new OnDialogClickListener() {
                                @Override
                                public void onClick() {

                                }
                            });

                        }
                    });

                }

                break;}

    }


    private boolean validate (View v , String mail , String mobileId){

        if (mail.equals("")){
            showMessage(ForgetPasswordActivity.this, R.drawable.ic_lock, R.string.msg_empty_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {
                }
            });
            return false ;

        }


        if (mobileId.equals("")){

            showMessage(ForgetPasswordActivity.this, R.drawable.ic_lock, R.string.msg_invalied_pin, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {
                }
            });

            return false ;

        }

        if (!BaseFragment.validateMail(mail)){

            showMessage(ForgetPasswordActivity.this, R.drawable.ic_report, R.string.msg_wrong_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {
                }
            });

            return false ;
        }

        return true;

    }
}
