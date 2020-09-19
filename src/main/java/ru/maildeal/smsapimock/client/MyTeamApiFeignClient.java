package ru.maildeal.smsapimock.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "myteamclient", url = "https://api.internal.myteam.mail.ru")
public interface MyTeamApiFeignClient {
    @GetMapping(value = "/bot/v1/messages/sendText")
    @ResponseBody TGApiResponse sendMessage(
            @RequestParam("token") String token,
            @RequestParam("chatId") String chatId,
            @RequestParam("text") String message
    );
}
