package com.example.mtkdem2_targel3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    //private ContactsRepository contactsRepository;
    private LiveData<List<Contacts>> contacts;

   /* public ContactsViewModel(ContactsRepository contactsRepository, LiveData<List<Contacts>> contacts) {
        contactsRepository = new ContactsRepository();
        contacts = contactsRepository.getAll();
    }*/

   public LiveData<List<Contacts>> get(){ return contacts;}
/*
    public void add(Contacts contact){
        contactsRepository.add(contact);
    }

    public void delete(Contacts contact){
        contactsRepository.delete(contact);
    }

    public void reload(){
        contactsRepository.reload();
    }*/
}
