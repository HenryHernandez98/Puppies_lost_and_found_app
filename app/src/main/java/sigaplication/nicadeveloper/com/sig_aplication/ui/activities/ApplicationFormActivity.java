package sigaplication.nicadeveloper.com.sig_aplication.ui.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import sigaplication.nicadeveloper.com.sig_aplication.R;

public class ApplicationFormActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 1000;
    private ImageView iv_image;
    private List<Uri> selectedUriList;
    Uri image_Uri;
    private Button addImages;

    //Calendar
    private Button dateButton;
    private EditText dateEditText;
    private DatePickerDialog picker;

    //Maps
    private Button locationButton;
    private EditText locationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_form);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        initViews();

        //Select Images option
        Glide.with(getApplicationContext()).load(R.drawable.ic_image_placeholder).apply(new RequestOptions().fitCenter()).into(iv_image);
        addImages.setOnClickListener(view -> setShowImages());

        //Show the calendar dialog
        dateEditText.setOnClickListener(v -> showCalendar());
        dateButton = findViewById(R.id.dateButton);
        dateButton.setOnClickListener(v -> showCalendar());

        //Show maps
        locationButton.setOnClickListener(v -> showMaps());
        locationEditText.setOnClickListener(v -> getCity());

    }

    private void initViews() {
        //Select multiImages option
        iv_image = findViewById(R.id.iv_image);
        addImages = findViewById(R.id.add_button);
        //Calendar
        dateEditText = findViewById(R.id.dog_date_edit_text);
        //maps
        locationButton = findViewById(R.id.cityButton);
        locationEditText = findViewById(R.id.dog_city_edit_text);
    }

    private void setShowImages() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);

        /*
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                        PackageManager.PERMISSION_DENIED){
            String [] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permission, PERMISSION_CODE);
        } else {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , 1);
        } */
    }
/*
    //Permissions to access the gallery
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length >0 && grantResults [0]==
                        PackageManager.PERMISSION_GRANTED){
                    setMultiShowImages();
                }else{
                    Toast.makeText(this, "PERMISSION DENIED....",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    */

    //Mapping the image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    // imageButton where you want to set image
                    Glide.with(getApplicationContext())
                            .load(uri).apply(new RequestOptions()
                            .fitCenter()).into(iv_image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Glide.with(getApplicationContext()).load(image_Uri).apply(new RequestOptions().fitCenter()).into(iv_image);
            }
        }

    }

    private void showCalendar() {
        dateEditText.setInputType(InputType.TYPE_NULL);
        dateEditText.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(ApplicationFormActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> dateEditText
                            .setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            picker.show();
        });

    }

    private void showMaps() {
        Intent intent = new Intent(ApplicationFormActivity.this, MapsActivity.class);
        startActivity(intent);
    }

    private void getCity(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                String city = actualCity(location.getLatitude(), location.getLongitude());
                locationEditText.setText(city);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ApplicationFormActivity.this, "Not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        String city = actualCity(location.getLatitude(), location.getLongitude());
                        locationEditText.setText(city);
                    } catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(ApplicationFormActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ApplicationFormActivity.this, "Permissions not granted", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private String actualCity (double latitude, double longitude){
        String cityName = "";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 10);
            if (addresses.size() > 0){
                for (Address address: addresses){
                    if(address.getLocality() != null && address.getLocality().length() > 0){
                        cityName = address.getLocality();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cityName;

    }

}
