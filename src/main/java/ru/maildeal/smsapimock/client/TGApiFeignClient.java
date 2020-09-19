package ru.maildeal.smsapimock.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "tgclient", url = "https://api.telegram.org")
public interface TGApiFeignClient {
    @GetMapping(value = "/bot{botToken}/sendMessage")
    @ResponseBody TGApiResponse sendMessage(
            @PathVariable("botToken") String botToken,
            @RequestParam("chat_id") String chatId,
            @RequestParam("text") String message
    );
}
