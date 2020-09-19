package ru.maildeal.smsapimock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maildeal.smsapimock.client.Notifier;
import ru.maildeal.smsapimock.domain.VerificationCodeInfo;
import ru.maildeal.smsapimock.repository.VerificationCodeInfoRepository;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class VerificationServiceImpl implements VerificationService {
    private final Notifier notifier;
    private final VerificationCodeInfoRepository codeInfoRepository;

    @Autowired
    public VerificationServiceImpl(
            Notifier notifier, VerificationCodeInfoRepository codeInfoRepository
    ) {
        this.notifier = notifier;
        this.codeInfoRepository = codeInfoRepository;
    }

    @Override
    public String startVerification(
            String phone, String sessId, String userIp,
            String code, Integer codeLen
    ) {
        if (code == null || code.isEmpty()) {
            code = this.genCode(codeLen);
        }

        var info = new VerificationCodeInfo()
                .setCode(code)
                .setPhone(phone)
                .setSessId(sessId)
                .setUserIp(userIp);
        this.codeInfoRepository.storeVerificationCodeInfo(info);

        var message = this.makeVerificationMessage(phone, code, userIp);
        this.notifier.sendMessage(message);

        return code;
    }

    @Override
    public Optional<String> completeVerification(
            String code, String sessId, String userIp
    ){
        var info = this.codeInfoRepository.fetchVerificationCodeInfoByCode(code);
        if (info.isEmpty())
            return Optional.empty();

        var infoValue = info.get();
        if (infoValue.getUserIp() != null && !infoValue.getUserIp().equals(userIp)) {
            return Optional.empty();
        }
        if (infoValue.getSessId() != null && !infoValue.getSessId().equals(sessId))
            return Optional.empty();

        this.codeInfoRepository.deleteVerificationCodeInfoByCode(code);
        return Optional.of(infoValue.getPhone());
    }

    private String genCode(int codeLen) {
        var bytes = new byte[codeLen];
        new SecureRandom().nextBytes(bytes);

        var sb = new StringBuilder(codeLen);
        for (int i = 0; i < codeLen; ++i) {
            sb.append((char) (Math.abs(bytes[i] % 10) + '0'));
        }

        return sb.toString();
    }

    private String makeVerificationMessage(String phone, String code, String userIp) {
        return String.format("\uD83D\uDD14 VERIFICATION: phone=%s, code=%s, ip=%s", phone, code, userIp);
    }
}
