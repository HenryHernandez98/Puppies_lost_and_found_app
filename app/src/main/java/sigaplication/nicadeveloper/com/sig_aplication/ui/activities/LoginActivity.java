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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.api.Api;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;
import sigaplication.nicadeveloper.com.sig_aplication.models.accessToken;

public class LoginActivity extends AppCompatActivity {


    //Elements of the login
    private EditText username;
    private EditText email;
    private EditText password;
    private Button login;
    private Button signUp;
    private Button restorePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Remember.init(getApplicationContext(),"sigaplication.nicadeveloper.com.sig_aplication.ui.activities");
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Call all the necessary methods
        initViews();
        initActions();
    }

    private void initViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);
        restorePassword = findViewById(R.id.restorePassword);

    }


    private void initActions() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SingUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login() {
        String email2 = String.valueOf(email.getText().toString());
        String pass2 = String.valueOf(password.getText().toString());
        if(email2.equals("") || pass2.equals("")) {
            Toast.makeText(getApplicationContext(),"Can't leave empty fields",Toast.LENGTH_SHORT).show();
        }else{
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            saveUserData(email2,pass2);
            Call<accessToken> accesTokenCall =Api.instance().loginUser(user);
            accesTokenCall.enqueue(new Callback<accessToken>() {
                @Override
                public void onResponse(@NonNull Call<accessToken> call,@NonNull Response<accessToken> response) {
                    if(response.isSuccessful()) {
                        Remember.putString("access_token", response.body().getId(), new Remember.Callback() {
                            @Override
                            public void apply(Boolean success) {
                                Toast.makeText(getApplicationContext(), "Success to login", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(),"An error occur while login was doing",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<accessToken> call, @NonNull Throwable t) {
                    Log.e("Err","An error occur while login was doing", t);
                }
            });


        }
    }
    public void saveUserData(String u, String p){

        SharedPreferences sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("email",u);
        editor.putString("password",p);

        editor.commit();
    }

    /*public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                login();
                break;
            case R.id.signUp:
                Intent intent = new Intent(LoginActivity.this, SingUpActivity.class);
                startActivity(intent);
                break;
        }
    }*/
}
