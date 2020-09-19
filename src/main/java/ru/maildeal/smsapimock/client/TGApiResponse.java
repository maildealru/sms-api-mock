package ru.maildeal.smsapimock.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TGApiResponse {
    @JsonProperty(value = "ok", required = true)
    Boolean ok;
}
