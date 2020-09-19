package ru.maildeal.smsapimock.service;

import java.util.Optional;

public interface VerificationService {
    String startVerification(
            String phone, String sessId, String userIp,
            String code, Integer codeLen
    );
    Optional<String> completeVerification(
            String code, String sessId, String userIp
    );
}
