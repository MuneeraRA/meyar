package app.firstcode.meyar.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.firstcode.meyar.OnDialogClickListener;
import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.restPassword.SetPasswordRequest;
import app.firstcode.meyar.data.service.model.restPassword.SetPasswordResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubmitNewPasswordActivity extends BaseActivity implements View.OnClickListener  {

    Button save_button;
    EditText edt_password , edt_con_password;

    String password , conPassword,mobileId , pinCode;

    ApiInterface service ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_new_password);
        initToolbar(R.string.restore_password_title);

        save_button = (Button) findViewById(R.id.save_button);
        edt_password=(EditText)findViewById(R.id.enter_new_password);
        edt_con_password=(EditText)findViewById(R.id.re_enter_new_password);
        mobileId=null;
        pinCode=getIntent().getStringExtra("pinCode");

        save_button.setOnClickListener(this);
    }

@Override
public void onClick(View v) {
        switch (v.getId()) {
        case R.id.save_button:
            password=edt_password.getText().toString().trim();
            conPassword=edt_con_password.getText().toString().trim();

           if (validate(v,password,conPassword)){

               service= ApiClient.getClient().create(ApiInterface.class);

               Call<SetPasswordResponse> call = service.setPassword(new SetPasswordRequest(pinCode,mobileId,password));

               call.enqueue(new Callback<SetPasswordResponse>() {
                   @Override
                   public void onResponse(Call<SetPasswordResponse> call, Response<SetPasswordResponse> response) {
                       if (response.code() == 200 && response.body().getStatus().equals("success")) {
                           BaseActivity.showMessage(SubmitNewPasswordActivity.this, R.drawable.ic_check,R.string.new_password_successfully_change , R.string.back_to_main, new OnDialogClickListener() {
                               @Override
                               public void onClick() {

                                   Intent intent = new Intent(SubmitNewPasswordActivity.this,HomePageActivity.class);
                                   startActivity(intent);
                                   finish();
                               }
                           });

                       }

                       else {

                           BaseActivity.showMessage(SubmitNewPasswordActivity.this, R.drawable.ic_report,R.string.msg_invalied_pin_request , R.string.done, new OnDialogClickListener() {
                               @Override
                               public void onClick() {

                                   Intent intent = new Intent(SubmitNewPasswordActivity.this,ForgetPasswordActivity.class);
                                   startActivity(intent);
                                   finish();
                               }
                           });


                       }


                       }

                   @Override
                   public void onFailure(Call<SetPasswordResponse> call, Throwable t) {

                       BaseActivity.showMessage(SubmitNewPasswordActivity.this, R.drawable.ic_report,R.string.msg_checking_network , R.string.done, new OnDialogClickListener() {
                           @Override
                           public void onClick() {

                           }
                       });

                   }
               });

           }


            break;


        }

}


private boolean validate (View v , String pass , String conPass){

    if (pass.equals("")){
        showMessage(SubmitNewPasswordActivity.this, R.drawable.ic_lock, R.string.msg_empty_password, R.string.done, new OnDialogClickListener() {
            @Override
            public void onClick() {
            }
        });
        return false ;

    }


    if (conPass.equals("")){

        showMessage(SubmitNewPasswordActivity.this, R.drawable.ic_lock, R.string.msg_empty_passwordConf, R.string.done, new OnDialogClickListener() {
            @Override
            public void onClick() {
            }
        });

        return false ;

    }

    if (!pass.equals(conPass)){

        showMessage(SubmitNewPasswordActivity.this, R.drawable.ic_report, R.string.msg_empty_password_not_matching, R.string.done, new OnDialogClickListener() {
            @Override
            public void onClick() {
            }
        });

        return false ;
    }

    return true;

}

}
