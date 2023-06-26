package com.example.mtkdem2_targel3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

public class MessageRepository {
    private MessageRepository.MessageListData messageListData;
    private ProfileAPI profileAPI;
    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Token getToken() {
        return token;
    }

    public MessageRepository(Token token,int id) {
        this.id=id;
        this.messageListData = new MessageRepository.MessageListData();
        profileAPI= new ProfileAPI(token);

    }

    public void setToken(Token token) {
        this.token = token;

    }

    private Token token;
    public void add(sendMsg msg) {
        profileAPI.sendMessages(messageListData,id,msg);
    }





    class MessageListData extends MutableLiveData<List<Message>>
    {
        public MessageListData()
        {
            super();

        }

        @Override
        protected void onActive() {
            super.onActive();
            /*
            new Thread(() ->{
                //contactListData.postValue(dao.get());
            }).start();

             */
            profileAPI.getMessages(this,id);
        }
    }



    public LiveData<List<Message>> getAll()
    {
        return this.messageListData;

    }
}
