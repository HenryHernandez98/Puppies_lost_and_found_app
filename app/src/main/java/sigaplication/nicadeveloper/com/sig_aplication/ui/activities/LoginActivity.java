package sigaplication.nicadeveloper.com.sig_aplication.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sigaplication.nicadeveloper.com.sig_aplication.R;

public class LoginActivity extends AppCompatActivity {


    //Elements of the login
    private EditText username;
    private EditText password;
    private Button signIn;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Call all the necessary methods
        initViews();
        setActions();
    }

    private void initViews() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);

    }


    private void setActions() {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
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

    private void signIn() {

        String nickname = String.valueOf(username.getText().toString());
        String pass = String.valueOf(password.getText().toString());
        if (nickname.equals("") || pass.equals("")) {
            Toast.makeText(getApplicationContext(), "Can't leave empty fields", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getApplicationContext(), "Success to login", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();


        }


    }
}
