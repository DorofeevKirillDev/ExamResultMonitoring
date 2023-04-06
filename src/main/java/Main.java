
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SitePageUpdater updater = new SitePageUpdater("");
        String recipients = "recip@gmail.com";
        String message = "mesasge";
        MailNotificator mailNotificator = new MailNotificator();
        mailNotificator.setRamblerSMTPProperties();
        mailNotificator.authenticate("login", "password");
        TelegramNotificator telegramNotificator = new TelegramNotificator("BOT_TOKEN");
        while(true) {
            updater.checkForUpdates(new Notificator[] {mailNotificator, telegramNotificator}, recipients, message);
            Thread.sleep(60000);
        }
    }
}

