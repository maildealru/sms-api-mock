package ru.maildeal.smsapimock.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class Config {
    private final TGApiFeignClient tgApiFeignClient;
    private final MyTeamApiFeignClient myTeamApiFeignClient;

    @Value("${MESSANGER:*}")
    private String messanger;

    @Value("${TG_CHAT_ID:}")
    private String tgChatId;

    @Value("${TG_BOT_TOKEN:}")
    private String tgBotToken;

    @Value("${MYTEAM_CHAT_ID:}")
    private String myTeamChatId;

    @Value("${MYTEAM_BOT_TOKEN:}")
    private String myTeamBotToken;

    @Autowired
    public Config(TGApiFeignClient tgApiFeignClient,
                  MyTeamApiFeignClient myTeamApiFeignClient) {
        this.tgApiFeignClient = tgApiFeignClient;
        this.myTeamApiFeignClient = myTeamApiFeignClient;
    }

    @Bean
    Notifier getNotifier() {
        return switch (this.messanger) {
            case "TG" -> this.getTGNotifier();
            case "MYTEAM" -> this.getMyTeamNotifier();
            default -> this.getAllNotifier();
        };
    }

    private TGNotifier getTGNotifier() {
        return new TGNotifier(
                this.tgApiFeignClient,
                this.tgChatId, this.tgBotToken
        );
    }

    private MyTeamNotifier getMyTeamNotifier() {
        return new MyTeamNotifier(
                this.myTeamApiFeignClient,
                this.myTeamChatId, this.myTeamBotToken
        );
    }

    private AllNotifier getAllNotifier() {
        return new AllNotifier(
                this.getTGNotifier(),
                this.getMyTeamNotifier()
        );
    }
}
