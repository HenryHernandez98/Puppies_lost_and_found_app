package sigaplication.nicadeveloper.com.sig_aplication.ui.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

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
        locationEditText.setOnClickListener(v -> showMaps());

    }

    private void initViews(){
        //Select multiImages option
        iv_image = findViewById(R.id.iv_image);
        addImages = findViewById(R.id.add_button);
        //Calendar
        dateEditText = findViewById(R.id.dog_date_edit_text);
        //maps
        locationButton = findViewById(R.id.cityButton);
        locationEditText = findViewById(R.id.dog_city_edit_text);
    }

    private void setShowImages(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);

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
        if (resultCode == RESULT_OK){
            if (data != null) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    // imageButton where you want to set image
                    Glide.with(getApplicationContext())
                            .load(uri).apply(new RequestOptions()
                            .fitCenter()).into(iv_image);
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }else{
                Glide.with(getApplicationContext()).load(image_Uri).apply(new RequestOptions().fitCenter()).into(iv_image);
            }
        }

    }

    private void showCalendar(){
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
}
