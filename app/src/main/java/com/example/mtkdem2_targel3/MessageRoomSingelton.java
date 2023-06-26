package com.example.mtkdem2_targel3;

public class MessageRoomSingelton {
    private static MessageRoomSingelton instance;
    private MessageDao messageDao;
    private MessageAppDB db;

    private MessageRoomSingelton() {
    }

    public static MessageRoomSingelton getInstance() {
        if (instance == null) {
            instance = new MessageRoomSingelton();
        }
        return instance;
    }

    public MessageDao getContactsDao() {
        return messageDao;
    }

    public void setContactsDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public MessageAppDB getDb() {
        return db;
    }

    public void setDb(MessageAppDB db) {
        this.db = db;
    }

}
