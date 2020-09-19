package ru.maildeal.smsapimock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maildeal.smsapimock.model.SmsApiPhoneInfoResponse;

@RestController
public class PhoneInfoController {
    @GetMapping("/info")
    public ResponseEntity<SmsApiPhoneInfoResponse> getInfo(@RequestParam(name = "phone") String phone) {
        return ResponseEntity.ok(new SmsApiPhoneInfoResponse().setPhone(phone));
    }
}
