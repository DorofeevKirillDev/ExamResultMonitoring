
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SitePageUpdater updater1 = new SitePageUpdater("http://mph1.phys.spbu.ru/~ivanov/index.html");
        SitePageUpdater updater2 = new SitePageUpdater("http://mph1.phys.spbu.ru/~ivanov/3kypc/Course3.html");
        int counter = 1;
        String recipients = "kirigdbchb@gmail.com, polina.teslina@yandex.ru";
        String message = """
                    Check your exam results""";
        MailNotificator mailNotificator = new MailNotificator();
        mailNotificator.setRamblerSMTPProperties();
        mailNotificator.authenticate("claude.vidal.hx4mb@rambler.ru", "cm6Gk7SGi7p9");
        TelegramNotificator telegramNotificator = new TelegramNotificator();
        //telegramNotificator.sendNotification("260682954", "test");
        while(true) {
            updater1.checkForUpdates(new Notificator[] {mailNotificator, telegramNotificator}, recipients, message);
            updater2.checkForUpdates(new Notificator[] {mailNotificator, telegramNotificator}, recipients, message);
            //updater.checkForUpdates(telegramNotificator, recipients, message);
            Thread.sleep(60000);
            System.out.println("iterations: " + counter++);
        }
    }
}

