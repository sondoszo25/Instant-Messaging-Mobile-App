package com.example.mtkdem2_targel3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.List;

public class ContactsRepository {

    private ContactListData contactListData;
    private ProfileAPI profileAPI;
    private ContactsDao contactsDao;
    private ContactsAppDB db;
    private int flag=0;
    private boolean flagupdate;

    public Token getToken() {
        return token;
    }

    public ContactsRepository(Token token) {
        this.contactListData = new ContactListData();
        profileAPI= new ProfileAPI(token);
        db=ContactsRoomSingelton.getInstance().getDb();
        contactsDao=ContactsRoomSingelton.getInstance().getContactsDao();
        flagupdate=true;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    private Token token;
    public void add(Sender addcontact) {
        profileAPI.addcontact(addcontact,contactListData);
        flagupdate=true;
    }




    public void delete(int id) {
        flagupdate=true;
        profileAPI.deletecontact(id,contactListData);
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
                      if(!flagupdate) {
                          new Thread(() -> {
                              contactListData.postValue(contactsDao.index());
                          }).start();
                      }
                      else{
                          profileAPI.getcontacts(this);
                      }
        }
    }



    public LiveData<List<Contacts>> getAll()
    {
        return this.contactListData;
    }
}
