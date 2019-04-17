package sigaplication.nicadeveloper.com.sig_aplication.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tumblr.remember.Remember;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.api.Api;
import sigaplication.nicadeveloper.com.sig_aplication.constants.Constants;
import sigaplication.nicadeveloper.com.sig_aplication.databaseDao.AppDatabase;
import sigaplication.nicadeveloper.com.sig_aplication.model.User;
import sigaplication.nicadeveloper.com.sig_aplication.model.AccessToken;

public class LoginActivity extends AppCompatActivity {


    //Elements of the login
    private EditText editTextUser;
    private EditText editTextPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private Button restorePassword;
    private AccessToken tokenResponse;

    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Remember.init(getApplicationContext(),"sigaplication.nicadeveloper.com.sig_aplication.ui.activities");
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Call all the necessary methods
        bindUI();
        initActions();
    }

    private void bindUI() {
        editTextUser = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.signIn);
        btnSignUp = findViewById(R.id.signUp);
        restorePassword = findViewById(R.id.restorePassword);
    }

    private void initActions() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editTextUser.getText().toString(), editTextPassword.getText().toString());
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login(final String email, final String password) {
        // Reset errors.
        editTextUser.setError(null);
        editTextPassword.setError(null);

        if (!isValidUser(email)) {
            editTextUser.setError(getString(R.string.invalid_user_txt));
        } else if (!isValidPassword(password)) {
            editTextPassword.setError(getString(R.string.password_invalid));
        } else {


            final User userRequest = new User();
            userRequest.setEmail(email);
            userRequest.setPassword(password);

            Call<AccessToken> call = Api.instance().login(userRequest);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    tokenResponse = response.body();

                    if(response.body()!=null){
                        Remember.putString(Constants.ACCESS_TOKEN, tokenResponse.getId());
                        Toast.makeText(getApplicationContext(), "Sesión iniciada", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error al iniciar sesión", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


    private boolean isValidUser(String email) {
        return email.contains("@");
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4;
    }
}
