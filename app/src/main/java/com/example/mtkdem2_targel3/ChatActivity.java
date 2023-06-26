package com.example.mtkdem2_targel3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChatActivity extends AppCompatActivity {
    private ContactsViewModel contactsViewModel;
   private ContactsAppDB db;
   private ContactsDao contactsDao;
    private MessageAppDB dbb;
    private MessageDao MessageDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Token token = new Token( getIntent().getStringExtra("token"));
        String username=getIntent().getStringExtra("username");
        ProfileAPI profileAPI = new ProfileAPI();
        TextView mename=findViewById(R.id.mename);
        ImageView imgview=findViewById(R.id.mepic);

        dbb= Room.databaseBuilder(getApplicationContext(),MessageAppDB.class,"MessageDBDB").allowMainThreadQueries().build();
        MessageDao=dbb.messageDao();
        MessageRoomSingelton messageRoomSingelton=MessageRoomSingelton.getInstance();
        messageRoomSingelton.setDb(dbb);
        messageRoomSingelton.setContactsDao(MessageDao);


        db= Room.databaseBuilder(getApplicationContext(),ContactsAppDB.class,"ContactsDB").allowMainThreadQueries().build();
        contactsDao=db.contactsDao();
        ContactsRoomSingelton contactsRoomSingelton=ContactsRoomSingelton.getInstance();
        contactsRoomSingelton.setDb(db);
        contactsRoomSingelton.setContactsDao(contactsDao);
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
        contactsViewModel=new ViewModelProvider(this).get(ContactsViewModel.class);
        contactsViewModel.setToken(token);
        ContactsViewModelSingleton contactsViewModelSingleton=ContactsViewModelSingleton.getInstance();
        contactsViewModelSingleton.setContactsViewModel(contactsViewModel);
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
        adapter.setOnItemClickListener(new ContactsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Contacts clickedContact = adapter.getContacts().get(position);

                // Create an intent to navigate to the new activity
                Intent intent = new Intent(ChatActivity.this, MsgsActivity.class);
                // Pass any necessary data to the new activity
                intent.putExtra("token", token.getToken());
                intent.putExtra("username", username);
                FriendProfile friendProfile=FriendProfile.getInstance();
                friendProfile.setId(clickedContact.getId());
                friendProfile.setPic(clickedContact.getUser().getProfilePic());
                friendProfile.setUsername(clickedContact.getUser().getDisplayName());

                // Start the new activity2
                startActivity(intent);
            }
        });
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager((this)));
        contactsViewModel.get().observe(this,contacts -> {
            adapter.setContacts(contacts);
        });


    }
}