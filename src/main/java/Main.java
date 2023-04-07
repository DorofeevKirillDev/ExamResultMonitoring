
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SitePageUpdater updater = new SitePageUpdater("url.com");
        String recipients = "recip@gmail.com";
        String message = "mesasge";
        MailNotificator mailNotificator = new MailNotificator();
        mailNotificator.setRamblerSMTPProperties();
        mailNotificator.authenticate("login", "password");
        TelegramNotificator telegramNotificator = new TelegramNotificator("botToken", "botName");
        while(true) {
            updater.checkForUpdates(new Notificator[] {mailNotificator, telegramNotificator}, recipients, message);
            Thread.sleep(60000);
        }
    }
}

