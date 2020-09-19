package ru.maildeal.smsapimock.client;

class TGNotifier implements Notifier {
    private final TGApiFeignClient apiClient;

    private final String chatId;
    private final String botToken;


    public TGNotifier(TGApiFeignClient apiClient,
                      String chatId, String botToken) {
        this.chatId = chatId;
        this.botToken = botToken;
        this.apiClient = apiClient;
    }

    @Override
    public void sendMessage(String message) {
        var r = this.apiClient.
                sendMessage(this.botToken, this.chatId, message);
        if (!r.getOk()) {
            throw new RuntimeException("Telegram API response is error");
        }
    }
}

class MyTeamNotifier implements Notifier {
    private final MyTeamApiFeignClient apiClient;

    private final String chatId;
    private final String botToken;


    public MyTeamNotifier(MyTeamApiFeignClient apiClient,
                          String chatId, String botToken) {
        this.chatId = chatId;
        this.botToken = botToken;
        this.apiClient = apiClient;
    }

    @Override
    public void sendMessage(String message) {
        var r = this.apiClient.
                sendMessage(this.botToken, this.chatId, message);
        if (!r.getOk()) {
            throw new RuntimeException("MyTeam API response is error");
        }
    }
}

class AllNotifier implements Notifier {
    private final TGNotifier tgNotifier;
    private final MyTeamNotifier myTeamNotifier;

    public AllNotifier(TGNotifier tgNotifier,
                       MyTeamNotifier myTeamNotifier) {
        this.tgNotifier = tgNotifier;
        this.myTeamNotifier = myTeamNotifier;
    }

    @Override
    public void sendMessage(String message) {
        this.tgNotifier.sendMessage(message);
        this.myTeamNotifier.sendMessage(message);
    }
}
