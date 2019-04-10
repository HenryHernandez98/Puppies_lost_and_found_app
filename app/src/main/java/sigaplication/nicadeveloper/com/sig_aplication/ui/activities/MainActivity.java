package sigaplication.nicadeveloper.com.sig_aplication.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import sigaplication.nicadeveloper.com.sig_aplication.R;
import sigaplication.nicadeveloper.com.sig_aplication.ui.fragments.ComplaintFragment;
import sigaplication.nicadeveloper.com.sig_aplication.ui.fragments.HomeFragment;
import sigaplication.nicadeveloper.com.sig_aplication.ui.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActions();

        BottomNavigationView navigationView = findViewById(R.id.navigation);
        final HomeFragment homeFragment = new HomeFragment();
        final ComplaintFragment complaintFragment = new ComplaintFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
           int id= menuItem.getItemId();
           if(id == R.id.navigation_home){
               setFragment(homeFragment);
               return true;
           }else if (id == R.id.navigation_complaint){
               setFragment(complaintFragment);
               return true;
           }else if ((id == R.id.navigation_profile)){
               setFragment(profileFragment);
               return true;
           }
            return false;
        });

    }

    private void initViews(){
        addComplaint = findViewById(R.id.new_complaint);
    }

    private void initActions(){
        addComplaint.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ApplicationFormActivity.class);
            startActivity(intent);
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.commit();
    }
}
