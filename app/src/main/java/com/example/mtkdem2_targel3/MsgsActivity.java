package com.example.mtkdem2_targel3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MsgsActivity extends AppCompatActivity {
    private MessageViewModel messageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msgs);
        FriendProfile friendProfile=FriendProfile.getInstance();
        String username=getIntent().getStringExtra("username");
        String token = getIntent().getStringExtra("token");
        int id= friendProfile.getId();
        String dis=friendProfile.getUsername();
        String photo=friendProfile.getPic();
        TextView textViewname=findViewById(R.id.contactname);
       textViewname.setText(dis);
        ImageView imgview=findViewById(R.id.Photocontact);
        if (photo == null || photo.equals("data:image/*;base64,")) {
            imgview.setImageResource(R.drawable.dimg);
        } else {
            String base64Image = photo.substring(photo.indexOf(",") + 1);
            byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            imgview.setImageBitmap(decodedBitmap);
        }
        int nightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isNightModeEnabled = nightMode == Configuration.UI_MODE_NIGHT_YES;

        if (isNightModeEnabled) {

            findViewById(R.id.lstMsg).setBackgroundResource(R.drawable.blackbackgound);
        } else {
            findViewById(R.id.lstMsg).setBackgroundResource(R.drawable.backgroundchatapp);
        }





        FloatingActionButton backButton=findViewById(R.id.backbtn);
        backButton.setOnClickListener(view ->{
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("token", token);
            i.putExtra("username", username);
            startActivity(i);
        });
        messageViewModel=new ViewModelProvider(this).get(MessageViewModel.class);
        messageViewModel.setTokenandid(new Token(token),id);
        messageviewsingleton.getInstance().setMessageViewModel(messageViewModel);
        RecyclerView lstMessages = findViewById(R.id.lstMsg);
        MessageListAdapter messageListAdapter = new MessageListAdapter(this);
        messageListAdapter.setMe(username);
        lstMessages.setAdapter(messageListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lstMessages.setLayoutManager(layoutManager);
        messageViewModel.get().observe(this,messages -> {
            messageListAdapter.setMessages(messages);
            lstMessages.scrollToPosition(messageListAdapter.getItemCount() - 1);

        });

        Button btnSend=findViewById(R.id.btnSend);
        btnSend.setOnClickListener(view ->{
            EditText msgEditText = findViewById(R.id.etMessage);
           String msg = msgEditText.getText().toString();
           msgEditText.setText("");
           sendMsg sendMsg=new sendMsg(msg);
           if(msg != null && !(msg.equals(""))) {
               messageViewModel.add(sendMsg);
               lstMessages.scrollToPosition(messageListAdapter.getItemCount() - 1);

           }
        });

    }
}
