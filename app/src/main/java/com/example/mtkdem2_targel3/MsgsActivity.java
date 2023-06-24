package com.example.mtkdem2_targel3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MsgsActivity extends AppCompatActivity {

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
        FloatingActionButton backButton=findViewById(R.id.backbtn);
        backButton.setOnClickListener(view ->{
            Intent i = new Intent(this, ChatActivity.class);
            i.putExtra("token", token);
            i.putExtra("username", username);
            startActivity(i);
        });
    }
}
