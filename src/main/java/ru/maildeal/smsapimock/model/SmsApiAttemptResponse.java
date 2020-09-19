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
public class SmsApiAttemptResponse {
    @JsonProperty(value = "status")
    String status = STATUS_OK;

    public static final String STATUS_OK = "OK";
    public static final String STATUS_ERROR = "ERROR";

    @JsonProperty(value = "detailed_status")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String detailedStatus = null;

    @JsonProperty(value = "modified_phone_number")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    String modifiedPhoneNumber = null;
}
