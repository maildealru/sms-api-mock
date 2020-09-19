package ru.maildeal.smsapimock.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SmsApiPhoneInfoResponse {
    @JsonProperty(value = "status")
    String status = "OK";

    @JsonProperty(value = "info")
    SmsApiPhoneInfo phoneInfo;

    public SmsApiPhoneInfoResponse setPhone(String value) {
        this.phoneInfo = new SmsApiPhoneInfo().setPhone(value);
        return this;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
class SmsApiPhoneInfo {
    @JsonProperty(value = "phone")
    String phone;
}
