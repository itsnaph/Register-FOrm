package com.example.registerform;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editTxtName, editTxtEmail, editTxtPassword, editTxtConfirmPassword;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnConPassword, txtGender, txtCountry, txtLicense;

    private ConstraintLayout parent;
    private RadioGroup rgGender;
    private Spinner spinnerCountry;
    private CheckBox agreementCheck;
    private Button btnRegister, btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initViews();
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Error: Button Unavailable!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               initRegister(); 
            }
        });
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initRegister() {
        Log.d(TAG,"init Register: Started");
        
        if(validateData()){
            if(agreementCheck.isChecked()){
               showSnackBar();
            }else{
                Toast.makeText(this, "Please accept the license", Toast.LENGTH_SHORT).show();
            }
            
        }
    }

    private void showSnackBar() {
        Log.d(TAG,"show Snack Bar: Started");
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnName.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnConPassword.setVisibility(View.GONE);

        String name=editTxtName.getText().toString();
        String email=editTxtEmail.getText().toString();
        String password=editTxtPassword.getText().toString();
        String confirmPassword=editTxtConfirmPassword.getText().toString();
        String gender=" ";
        String country=spinnerCountry.getSelectedItem().toString();
        switch(rgGender.getCheckedRadioButtonId()){
            case R.id.radioBtnMale:
                gender="Male";
                break;
            case R.id.radioBtnFemale:
                gender="Female";
                break;
            case R.id.radioBtnOther:
                gender="Other";
                break;
            default:
                gender="Unkhown";
                break;

        }

        String snackText="Name: "+ name+"\n"+
                "Email: "+email+"\n"+
                "Gender: "+gender+"\n"+
                "Country: "+country;

        Log.d(TAG,"show Snack Bar: "+snackText);

        Snackbar.make(parent, "Registration Successful", Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editTxtName.setText("");
                        editTxtEmail.setText("");
                        editTxtPassword.setText("");
                        editTxtConfirmPassword.setText("");
                        rgGender.clearCheck();
                        spinnerCountry.setSelection(0);
                    }
                }).show();

    }

    private boolean validateData() {
        Log.d(TAG,"validate Data: Started");
        if(editTxtName.getText().toString().isEmpty()){
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Name is required");
            return false;
        }
        if(editTxtEmail.getText().toString().isEmpty()){
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Email is required");
            return false;
        }
        if(editTxtPassword.getText().toString().isEmpty()){
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Password is required");
            return false;
        }
        if(!editTxtPassword.getText().toString().equals(editTxtConfirmPassword.getText().toString())){
            txtWarnConPassword.setVisibility(View.VISIBLE);
            txtWarnConPassword.setText("Password does not match");
            return false;

        }
        return true;


    }

    private void initViews() {
        Log.d(TAG,"init Views: Started");
        editTxtName = findViewById(R.id.editTxtName);
        editTxtEmail = findViewById(R.id.editTxtEmail);
        editTxtPassword = findViewById(R.id.editTxtPassword);
        editTxtConfirmPassword = findViewById(R.id.editTxtConfirmPassword);
        
        txtWarnEmail=findViewById(R.id.txtWarnEmail);
        txtWarnName=findViewById(R.id.txtWarnName);
        txtWarnPassword=findViewById(R.id.txtWarnPassword);
        txtWarnConPassword=findViewById(R.id.txtWarnConPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnUpload=findViewById(R.id.btnUpload);

        parent = findViewById(R.id.main);

        rgGender = findViewById(R.id.rgGender);

        spinnerCountry = findViewById(R.id.spinnerCountry);

        agreementCheck = findViewById(R.id.agreementCheck);



    }
}