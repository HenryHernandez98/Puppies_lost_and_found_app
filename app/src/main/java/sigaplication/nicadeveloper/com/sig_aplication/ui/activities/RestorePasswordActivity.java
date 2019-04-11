package sigaplication.nicadeveloper.com.sig_aplication.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

import sigaplication.nicadeveloper.com.sig_aplication.R;

public class RestorePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_password);

        Fresco.initialize(this);
    }
}
