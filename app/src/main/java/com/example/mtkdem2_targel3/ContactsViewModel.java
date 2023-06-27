package com.example.mtkdem2_targel3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    private ContactsRepository contactsRepository;

    private LiveData<List<Contacts>> contacts;
    private Token token;
    public ContactsViewModel() {
    }

    public void setToken(Token token) {
        this.token = token;
        contactsRepository = new ContactsRepository(token);
        contacts = contactsRepository.getAll();
    }

    public LiveData<List<Contacts>> get(){ return contacts;}

    public void add(Sender addcontact){
        contactsRepository.add(addcontact);
    }

    public void delete(int id){
        contactsRepository.delete(id);
    }

    public void reload(){
        contactsRepository.reload();
    }

}
