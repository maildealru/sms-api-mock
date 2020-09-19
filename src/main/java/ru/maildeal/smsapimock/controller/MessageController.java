package ru.maildeal.smsapimock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maildeal.smsapimock.model.SmsApiSendResponse;
import ru.maildeal.smsapimock.service.MessageService;

@RestController
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/send")
    public ResponseEntity<SmsApiSendResponse> verify(
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "text") String text,
            @RequestParam(name = "user_ip") String userIp,
            @RequestParam(name = "test", required = false, defaultValue = "0") String test
    ) {
        if (!test.equals("1"))
            this.messageService.sendTextMessage(phone, userIp, text);
        return ResponseEntity.ok(new SmsApiSendResponse().setText(text));
    }
}
