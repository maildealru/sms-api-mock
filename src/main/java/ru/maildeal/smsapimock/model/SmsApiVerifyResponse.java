package ru.maildeal.smsapimock.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SmsApiVerifyResponse {
    @JsonProperty(value = "status")
    String status = "OK";

    @JsonProperty(value = "session_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String sessionId = null;

    @JsonProperty(value = "routes")
    String[] routes = {"sms", "callui", "push"};

    @JsonProperty(value = "checks")
    String[] checks = {"sms", "callui", "push"};

    @JsonProperty(value = "verify_code")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String code = null;

    @JsonProperty(value = "code_length")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Integer codeLength = null;

    @JsonProperty(value = "code_type")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String codeType = null;

    @JsonProperty(value = "modified_phone_number")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String modifiedPhoneNumber = null;
}
