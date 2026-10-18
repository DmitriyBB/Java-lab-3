package task04;

public class Main {

    static void trySend(AbstractNotificationSender sender, String recipient, String message) {
        try {
            sender.send(recipient, message);
        } catch (IllegalArgumentException e) {
            System.out.println("отказ: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AbstractNotificationSender[] senders = {new EmailSender(), new ConsoleSender()};
        for (AbstractNotificationSender sender : senders) {
            trySend(sender, "  anna@uni.ru ", "Вы записаны на курс");
            trySend(sender, "operator", "Новая заявка");
            trySend(sender, "   ", "пусто");
        }
    }
}
