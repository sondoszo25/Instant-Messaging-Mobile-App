package com.example.mtkdem2_targel3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addActivity extends AppCompatActivity {
    private ContactsViewModel contactsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button cancelButton = findViewById(R.id.cancelButton);
        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        String username = intent.getStringExtra("username");
        contactsViewModel = ContactsViewModelSingleton.getInstance().getContactsViewModel();
        cancelButton.setOnClickListener(view ->{
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("token", token);
            i.putExtra("username", username);
            startActivity(i);
        });
        Button addcontact=findViewById(R.id.addButton);
        addcontact.setOnClickListener(view ->{
            EditText userEditText = findViewById(R.id.nameEditText);
            String user = userEditText.getText().toString();
            contactsViewModel.add(new Sender(user));
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("token", token);
            i.putExtra("username", username);
            startActivity(i);
        });

    }
}