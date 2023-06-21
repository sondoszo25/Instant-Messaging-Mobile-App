package com.example.mtkdem2_targel3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
       Button buttonexit=findViewById(R.id.btnlogout);

    }
}