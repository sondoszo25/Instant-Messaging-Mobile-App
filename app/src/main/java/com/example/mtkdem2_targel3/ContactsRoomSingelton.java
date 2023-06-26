package com.example.mtkdem2_targel3;

public class ContactsRoomSingelton {
    private static ContactsRoomSingelton instance;
    private ContactsDao contactsDao;
    private ContactsAppDB db;

    private ContactsRoomSingelton() {
    }

    public static ContactsRoomSingelton getInstance() {
        if (instance == null) {
            instance = new ContactsRoomSingelton();
        }
        return instance;
    }

    public ContactsDao getContactsDao() {
        return contactsDao;
    }

    public void setContactsDao(ContactsDao contactsDao) {
        this.contactsDao = contactsDao;
    }

    public ContactsAppDB getDb() {
        return db;
    }

    public void setDb(ContactsAppDB db) {
        this.db = db;
    }
}
