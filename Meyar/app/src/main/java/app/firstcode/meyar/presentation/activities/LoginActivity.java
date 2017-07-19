package app.firstcode.meyar.presentation.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.Category;
import app.firstcode.meyar.data.service.model.Contractor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    Button login_button;
    EditText user_email_edit, user_password_edit;
    LinearLayout forget_password;
    ApiInterface apiService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar(R.string.nav_login);

        user_email_edit = (EditText) findViewById(R.id.user_email_edit);
        user_password_edit = (EditText) findViewById(R.id.user_password_edit);
        login_button = (Button) findViewById(R.id.login_button);
        forget_password = (LinearLayout) findViewById(R.id.forget_password);

        apiService = ApiClient.getClient().create(ApiInterface.class);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_email_edit.getText().toString();
                String password = user_password_edit.getText().toString();
                loginConnection(email, password);

//                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
////                intent.putExtra("isLogin", false);
////                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
    }

    private void loginConnection(String email, String password) {
        Call<Contractor> call = apiService.login(email, password);
        call.enqueue(new Callback<Contractor>() {
            @Override
            public void onResponse(Call<Contractor> call, Response<Contractor> response) {
                Hawk.put("user", response.body());
                Log.e("USER: ", response.body() + "");
                if (response.isSuccessful()) {
                    Hawk.put("user", response.body());
                    Log.e("User Name", response.body().getUser().getName());
                    Log.e("Object: ", new Gson().toJson(response.body()));
                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Log.e("Error", "Fail");
                }
            }

            @Override
            public void onFailure(Call<Contractor> call, Throwable t) {
                Log.e("OnFail: ", call.toString());
            }
        });
    }
}
