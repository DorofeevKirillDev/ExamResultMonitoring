import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;


public class SitePageUpdater {
    private final String url;
    private final String storedContent;

    public SitePageUpdater(String url) {
        this.url = url;
        this.storedContent = getContent();
    }

    public void checkForUpdates(Notificator notificator, String recipients,
                                String message) {
        String currentContent = getContent();
        if (!currentContent.equals(storedContent)) {
            if (notificator.sendNotification(recipients, message)) {
                System.out.println("Massage sent");
            }
        }
    }

    public void checkForUpdates(Notificator[] notificators, String recipients,
                                String message) {
        String currentContent = getContent();
        if (!currentContent.equals(storedContent)) {
            for (var notificator : notificators) {
                if (notificator.sendNotification(recipients, message)) {
                    System.out.println("Massage sent");
                }
            }
        }
    }

    private String getContent() {
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.body().text();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}