package it.mauxilium.MauxKafkaConsumer.business.model;

import java.util.LinkedList;

public class MessageStorage {

    private static MessageStorage instance = new MessageStorage();
    private final LinkedList<MessageModel> msgList = new LinkedList<>();

    private MessageStorage() {
    }

    public static MessageStorage getInstance() {
        return instance;
    }

    public void addMsg(MessageModel msg) {
        msgList.add(msg);
    }

    public int getNumOfMessages() {
        return msgList.size();
    }
}
