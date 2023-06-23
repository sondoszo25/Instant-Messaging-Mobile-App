package com.example.mtkdem2_targel3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ContactsRepository {

    private ContactListData contactListData;
    private ProfileAPI profileAPI;

    public Token getToken() {
        return token;
    }

    public ContactsRepository(Token token) {
        this.contactListData = new ContactListData();
        profileAPI= new ProfileAPI(token);
    }

    public void setToken(Token token) {
        this.token = token;
    }

    private Token token;
    public void add(Contacts contact) {
        profileAPI.addcontact(contact);
        profileAPI.getcontacts(contactListData);
    }




    public void delete(Contacts contact) {
        profileAPI.delete(contact);
        profileAPI.getcontacts(contactListData);
    }

    class ContactListData extends MutableLiveData<List<Contacts>>
    {
        public ContactListData()
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
            profileAPI.getcontacts(this);
        }
    }



    public LiveData<List<Contacts>> getAll()
    {
        return this.contactListData;
    }
}
