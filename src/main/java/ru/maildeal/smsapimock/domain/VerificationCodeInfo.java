package ru.maildeal.smsapimock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VerificationCodeInfo {
    String code;
    String phone;
    String sessId;
    String userIp;
}
