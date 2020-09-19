package ru.maildeal.smsapimock.service;

public interface MessageService {
    void sendTextMessage(String phone, String userIp, String text);
}
