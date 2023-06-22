package com.example.mtkdem2_targel3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.apache.http.params.CoreConnectionPNames;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Token token = new Token( getIntent().getStringExtra("token"));
        String username=getIntent().getStringExtra("username");
        ProfileAPI profileAPI = new ProfileAPI();
        TextView mename=findViewById(R.id.mename);
            ImageView imgview=findViewById(R.id.mepic);
           profileAPI.getuser(token, username, new MyProfileCallback() {
               @Override
               public void onSuccess(MyProfile myProfile) {
                   mename.setText(myProfile.getDisplayName());
                   if (myProfile.getProfilePic() == null || myProfile.getProfilePic().equals("data:image/*;base64,")) {
                       imgview.setImageResource(R.drawable.dimg);
                   } else {
                       String base64Image = myProfile.getProfilePic().substring(myProfile.getProfilePic().indexOf(",") + 1);
                       byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
                       Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                       imgview.setImageBitmap(decodedBitmap);
                   }
               }

           @Override
           public void onFailure(int statusCode) {
           }

           @Override
           public void onError(Throwable t) {
           }
       });
        FloatingActionButton btnexit=findViewById(R.id.btnlogout);
        btnexit.setOnClickListener(view ->{
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        });

        FloatingActionButton btnadd=findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(view ->{
            Intent intent = new Intent(this, addActivity.class);
            intent.putExtra("token", token.getToken());
            intent.putExtra("username", username);
            startActivity(intent);
        });


        FloatingActionButton deleteadd=findViewById(R.id.btndelete);
        deleteadd.setOnClickListener(view ->{
            Intent intent = new Intent(this, deleteActivity.class);
            intent.putExtra("token", token.getToken());
            intent.putExtra("username", username);
            startActivity(intent);
        });

        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager((this)));
        List<Contacts> contacts = new ArrayList<>();
        MyProfile myProfile=new MyProfile("sonis1","aa","sonis1",null);
        Message message=new Message(1,"13.4",new Sender("moom"),"hi sonis");
        MyProfile myProfile2=new MyProfile("sonis12","aa","sonis12",null);
        Message message2=new Message(1,"13.4",new Sender("moom"),"hi sonis");
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        contacts.add(new Contacts(2,myProfile,message));
        contacts.add(new Contacts(2,myProfile2,null));
        adapter.setContacts(contacts);
    }
}