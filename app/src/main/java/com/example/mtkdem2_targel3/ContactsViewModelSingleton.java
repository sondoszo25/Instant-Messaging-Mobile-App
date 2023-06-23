package com.example.mtkdem2_targel3;

public class ContactsViewModelSingleton {
    private static ContactsViewModelSingleton instance;
    private ContactsViewModel contactsViewModel;

    private ContactsViewModelSingleton() {
    }

    public static ContactsViewModelSingleton getInstance() {
        if (instance == null) {
            instance = new ContactsViewModelSingleton();
        }
        return instance;
    }

    public ContactsViewModel getContactsViewModel() {
        return contactsViewModel;
    }

    public void setContactsViewModel(ContactsViewModel contactsViewModel) {
        this.contactsViewModel = contactsViewModel;
    }
}
