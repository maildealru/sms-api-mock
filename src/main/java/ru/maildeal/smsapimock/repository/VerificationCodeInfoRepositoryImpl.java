package ru.maildeal.smsapimock.repository;

import org.springframework.stereotype.Repository;
import ru.maildeal.smsapimock.domain.VerificationCodeInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Repository
public class VerificationCodeInfoRepositoryImpl implements VerificationCodeInfoRepository {
    private final Map<String, VerificationCodeInfo> map = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public void storeVerificationCodeInfo(VerificationCodeInfo info) {
        var wLock = this.rwLock.writeLock();
        wLock.lock();
        try {
            this.map.put(info.getCode(), info);
        } finally {
            wLock.unlock();
        }
    }

    @Override
    public Optional<VerificationCodeInfo> fetchVerificationCodeInfoByCode(String code) {
        var rLock = this.rwLock.readLock();
        rLock.lock();
        try {
            if (!this.map.containsKey(code))
                return Optional.empty();
            return Optional.of(this.map.get(code));
        } finally {
            rLock.unlock();
        }
    }

    @Override
    public void deleteVerificationCodeInfoByCode(String code) {
        var wLock = this.rwLock.writeLock();
        wLock.lock();
        try {
            this.map.remove(code);
        } finally {
            wLock.unlock();
        }
    }
}
