package sigaplication.nicadeveloper.com.sig_aplication.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import sigaplication.nicadeveloper.com.sig_aplication.models.Profile;
import sigaplication.nicadeveloper.com.sig_aplication.models.User;

public class SingUpActivity extends AppCompatActivity {
    private EditText pass;
    private EditText email;
    private Button login;
    private Button signUp;
    private EditText username;
    private EditText name;
    private EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        initView();
        initActions();

    }
    private void initView() {

        pass = findViewById(R.id.password);
        email= findViewById(R.id.email);
        signUp=findViewById(R.id.create_account);
        login=findViewById(R.id.login);
        username=findViewById(R.id.username);
        name=findViewById(R.id.edit_text_nombre);
        phone=findViewById(R.id.edit_text_telefono);

    }
    private void signUp() {
        String nameUser = String.valueOf(email.getText().toString());
        String password = String.valueOf(pass.getText().toString());
        if(nameUser.equals(" ")|| password.equals("")) {
            Toast.makeText(getApplicationContext(),"Can't leave empty fields",Toast.LENGTH_SHORT).show();
        }else {
            User user = new User();

            user.setEmail(email.getText().toString());
            user.setPassword(pass.getText().toString());
            Call<User> userCall = Api.instance().saveUser(user);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Success to Register", Toast.LENGTH_LONG).show();
                        Profile profile = new Profile();
                        profile.setNombre(name.getText().toString());
                        profile.setIduser(Integer.parseInt(response.body().getId()));
                        profile.setPhone(phone.getText().toString());
                        Call<Profile> profileCall = Api.instance().createProfile(profile);
                        profileCall.enqueue(new Callback<Profile>() {
                            @Override
                            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Perfil creado", Toast.LENGTH_SHORT).show();
                                    assert response.body() != null;
                                    profile.setId(response.body().getId());
                                } else {
                                    Toast.makeText(getApplicationContext(), "An error occur while register was doing", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                                Log.e("Err", "Error to show");
                            }
                        });
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "An error occur while register was doing", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("Err", "Error to show");
                }
            });

        }
    }

   private void initActions() {
       signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               signUp();
           }
       });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
               startActivity(intent);
           }
       });

   }




}
