package com.example.mtkdem2_targel3;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    private final int GRC=1000;
    private MyProfile myProfile;
    private String base64Image;

    private String password;
    private String username;
    private String displayName;
    private String profilePic="data:image/*;base64,";
    private String confirmpassword;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textView = findViewById(R.id.loginlink);
        textView.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
        Button buttonRegister = findViewById(R.id.Registerbutton);
        Button btnUpload = findViewById(R.id.photobutton);
        ImageView getimg=findViewById(R.id.photoview);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getphoto=new Intent(Intent.ACTION_PICK);
                getphoto.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(getphoto,GRC);
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the user input values
                EditText passwordEditText = findViewById(R.id.Passwordid);
                password = passwordEditText.getText().toString();
                EditText ussernameEditText = findViewById(R.id.userid);
                username = ussernameEditText.getText().toString();
                EditText displaynameEditText = findViewById(R.id.displayname);
                displayName = displaynameEditText.getText().toString();
                EditText confrimEditText = findViewById(R.id.Passwordconfirmid);
                confirmpassword = confrimEditText.getText().toString();
                if (!password.equals(confirmpassword)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("Password Mismatch");
                    builder.setMessage("The password and confirm password do not match.");

                    // Set the OK button
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    // Create and show the alert dialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return; // Return without continuing further
                }
                myProfile = new MyProfile(username, password, displayName, profilePic);

                // Create an alert dialog builder
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Registration");

                // Perform the registration logic
                ProfileAPI profileAPI = new ProfileAPI();
                profileAPI.postUsername(myProfile, new RegistrationCallback() {
                    @Override
                    public void onRegistrationSuccess() {
                        builder.setMessage("Registration successful!");
                        // Set the OK button
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        // Create and show the alert dialog
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                    @Override
                    public void onRegistrationFailure() {
                        builder.setMessage("Registration failed.");
                        // Set the OK button
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        // Create and show the alert dialog
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GRC) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                ImageView imageView = findViewById(R.id.photoview);
                profilePic = selectedImageUri.toString();
                imageView.setImageURI(selectedImageUri);

                // Convert the selected image to Base64
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    byte[] imageBytes = new byte[inputStream.available()];
                    inputStream.read(imageBytes);
                    inputStream.close();
                    profilePic = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}