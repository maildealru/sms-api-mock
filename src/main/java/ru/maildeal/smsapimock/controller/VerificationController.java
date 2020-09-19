package ru.maildeal.smsapimock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.maildeal.smsapimock.model.SmsApiAttemptResponse;
import ru.maildeal.smsapimock.model.SmsApiVerifyResponse;
import ru.maildeal.smsapimock.service.VerificationService;

@RestController
public class VerificationController {
    private final VerificationService verificationService;

    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping("/verify")
    public ResponseEntity<SmsApiVerifyResponse> verify(
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "user_ip") String userIp,
            @RequestParam(name = "routes", required = false) String routes,
            @RequestParam(name = "session_id", required = false) String sessId,
            @RequestParam(name = "verify_code", required = false, defaultValue = "") String code,
            @RequestParam(name = "code_length", required = false, defaultValue = "4") Integer codeLen,
            @RequestParam(name = "language", required = false, defaultValue = "EN") String language,
            @RequestParam(name = "test", required = false, defaultValue = "0") String test
    ) {
        if (!test.equals("1"))
            code =this.verificationService.startVerification(phone, sessId, userIp, code, codeLen);

        var r =
                new SmsApiVerifyResponse()
                        .setSessionId(sessId)
                        .setCodeType("numeric")
                        .setCode(code)
                        .setCodeLength(code.length())
                        .setModifiedPhoneNumber(phone);
        if (routes != null && !routes.isEmpty()) {
            var arr = routes.split(",");
            r.setRoutes(arr).setChecks(arr);
        }

        return ResponseEntity.ok(r);
    }

    @GetMapping("/attempt")
    public ResponseEntity<SmsApiAttemptResponse> attempt(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "user_ip") String userIp,
            @RequestParam(name = "session_id", required = false) String sessId
    ) {
        var phone = this.verificationService.
                completeVerification(code, sessId, userIp);

        var r = new SmsApiAttemptResponse();
        if (phone.isPresent()) {
            r.setModifiedPhoneNumber(phone.get());
        } else {
            r.setStatus(SmsApiAttemptResponse.STATUS_ERROR);
            r.setDetailedStatus("INVALID VERIFICATION CODE");
        }

        return ResponseEntity.ok(r);
    }
}
