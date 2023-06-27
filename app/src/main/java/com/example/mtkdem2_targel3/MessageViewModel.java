package com.example.mtkdem2_targel3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MessageViewModel extends ViewModel {

    private MessageRepository messageRepository;

    private LiveData<List<Message>> Messages;
    private Token token;
    public MessageViewModel() {
    }

    public void setTokenandid(Token token,int id) {
        this.token = token;
        messageRepository = new MessageRepository(token,id);
        Messages = messageRepository.getAll();
    }

    public LiveData<List<Message>> get(){ return Messages;}


    public void add(sendMsg msg){
        messageRepository.add(msg);
    }

    public void reload(){
        messageRepository.reload();
    }
}
