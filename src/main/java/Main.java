
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SitePageUpdater updater = new SitePageUpdater("http://mph1.phys.spbu.ru/~ivanov/index.html");
        int counter = 1;
        String recipients = "recip@gmail.com";
        String message = "mesasge";
        MailNotificator mailNotificator = new MailNotificator();
        mailNotificator.setRamblerSMTPProperties();
        mailNotificator.authenticate("login", "password");
        TelegramNotificator telegramNotificator = new TelegramNotificator();
        while(true) {
            updater.checkForUpdates(new Notificator[] {mailNotificator, telegramNotificator}, recipients, message);
            Thread.sleep(60000);
            System.out.println("iterations: " + counter++);
        }
    }
}

