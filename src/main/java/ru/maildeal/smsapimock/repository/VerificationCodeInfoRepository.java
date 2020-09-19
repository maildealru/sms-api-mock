package ru.maildeal.smsapimock.repository;

import ru.maildeal.smsapimock.domain.VerificationCodeInfo;

import java.util.Optional;

public interface VerificationCodeInfoRepository {
    void storeVerificationCodeInfo(VerificationCodeInfo info);
    Optional<VerificationCodeInfo> fetchVerificationCodeInfoByCode(String code);
    void deleteVerificationCodeInfoByCode(String code);
}
