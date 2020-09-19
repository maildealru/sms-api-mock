package ru.maildeal.smsapimock.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maildeal.smsapimock.client.Notifier;

@Service
public class MessageServiceImpl implements MessageService {
    private final Notifier notifier;

    @Autowired
    public MessageServiceImpl(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void sendTextMessage(String phone, String userIp, String text) {
        var message = this.makeMessage(phone, text, userIp);
        this.notifier.sendMessage(message);
    }

    private String makeMessage(String phone, String text, String userIp) {
        return String.format("\uD83D\uDD14 MESSAGE: phone=%s, text=%s, ip=%s", phone, text, userIp);
    }
}
