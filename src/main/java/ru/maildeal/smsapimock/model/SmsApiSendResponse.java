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
public class SmsApiSendResponse {
    @JsonProperty(value = "status")
    String status = "OK";

    @JsonProperty(value = "text")
    String text;
}
