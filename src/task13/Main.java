package task13;

public class Main {

    public static void main(String[] args) {
        String text = "Вы записаны на курс Основы Java, начало в понедельник";

        System.out.println("До: type code + switch");
        System.out.println("  " + new LegacyNotifier("email").send("anna@uni.ru", text));
        System.out.println("  " + new LegacyNotifier("sms").send("+7900", text));
        System.out.println("  " + new LegacyNotifier("sms-long").send("+7900", text));

        System.out.println("После: строка конфигурации разбирается один раз");
        String[] config = {"email:CourseHub", "sms:20", "sms:70", "console"};
        String[] addresses = {"anna@uni.ru", "+7900", "+7900", "admin"};
        for (int i = 0; i < config.length; i++) {
            NotificationChannel channel = Channels.fromConfig(config[i]);
            System.out.printf("  %-16s %-16s %s%n", config[i], channel.getClass().getSimpleName(), channel.send(addresses[i], text));
        }
        System.out.println("sms:20 и sms:70 — один класс SmsChannel с разным параметром, а не два подкласса.");
    }
}
