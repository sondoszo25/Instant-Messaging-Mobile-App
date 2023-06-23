package com.example.mtkdem2_targel3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import java.util.List;

public class deleteActivity extends AppCompatActivity {
    private ContactsViewModel contactsViewModel;
    private int iddd=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Button cancelButton = findViewById(R.id.cancelgButton);
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



        Button deletecontact=findViewById(R.id.GarbageButton);
        deletecontact.setOnClickListener(view ->{
            EditText userEditText = findViewById(R.id.nameEditTextG);
            String user = userEditText.getText().toString();
            LiveData<List<Contacts>> contacts=  contactsViewModel.get();
            Contacts temp=null;
            contacts.observe(this, contactList -> {
                for (Contacts cont : contactList) {
                    if (cont.getUser().getUsername().equals(user)) {
                        iddd = cont.getId();
                        break;
                    }
                }
            });

            contactsViewModel.delete(iddd);
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("token", token);
            i.putExtra("username", username);
            startActivity(i);
        });

    }
}