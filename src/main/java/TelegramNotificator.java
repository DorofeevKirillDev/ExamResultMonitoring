import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramNotificator extends TelegramLongPollingBot implements Notificator{
    private final String botUsername;

    public TelegramNotificator(String botToken, String botUsername) {
        super(botToken);
        this.botUsername = botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            System.out.println("started");
            switch (messageText){
                case "/start":
                    sendMessage(chatId + "", "Bot is active");
                    break;
                default: // TODO
            }
        }
    }

    public void sendMessage(String chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        try {
            execute(message);
        } catch (Exception e) {
            // TODO: Handle exception
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }


    @Override
    public boolean sendNotification(String chatId, String TextMessage) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(TextMessage);
        try {
            execute(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
