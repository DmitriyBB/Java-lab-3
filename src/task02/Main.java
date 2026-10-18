package task02;

public class Main {

    static int failures;

    static void check(String call, String predicted, String actual) {
        boolean ok = predicted.equals(actual);
        if (!ok) {
            failures++;
        }
        System.out.printf("%-28s predicted %-32s actual %-32s %s%n", call, predicted, actual, ok ? "OK" : "MISS");
    }

    public static void main(String[] args) {
        NotificationSender base = new NotificationSender();
        NotificationSender emailAsBase = new EmailSender();
        NotificationSender consoleAsBase = new ConsoleSender();
        EmailSender email = new EmailSender();
        ConsoleSender console = new ConsoleSender();
        Object textAsObject = "hi";
        Integer number = 42;

        check("base.send(\"hi\")", "NotificationSender.send(String)", base.send("hi"));
        check("base.send(textAsObject)", "NotificationSender.send(Object)", base.send(textAsObject));

        check("emailAsBase.send(\"hi\")", "NotificationSender.send(String)", emailAsBase.send("hi"));
        check("emailAsBase.send(textAsObj)", "EmailSender.send(Object)", emailAsBase.send(textAsObject));
        check("emailAsBase.send(42)", "EmailSender.send(Object)", emailAsBase.send(number));
        check("email.send(42)", "EmailSender.send(Integer)", email.send(number));
        check("email.send(\"hi\")", "NotificationSender.send(String)", email.send("hi"));

        check("consoleAsBase.send(\"hi\")", "ConsoleSender.send(String)", consoleAsBase.send("hi"));
        check("consoleAsBase.send(textAs)", "NotificationSender.send(Object)", consoleAsBase.send(textAsObject));
        check("console.send(textAsObject)", "NotificationSender.send(Object)", console.send(textAsObject));

        System.out.println();
        System.out.println("misses: " + failures);
        System.out.println("Шаг 1, компиляция (overload): по типу ссылки и типам аргументов выбирается сигнатура.");
        System.out.println("Шаг 2, выполнение (override): для выбранной сигнатуры берётся версия из класса объекта.");
    }
}
