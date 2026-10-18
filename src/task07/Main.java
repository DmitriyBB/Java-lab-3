package task07;

public class Main {

    public static void main(String[] args) {
        System.out.println("Порядок A: Logging(Metrics(Retry(Email)))");
        MetricsSender metricsA = new MetricsSender(new RetryingSender(new EmailSender(2), 3));
        NotificationSender a = new LoggingSender(metricsA);
        a.send("anna@uni.ru", "Вы записаны");
        System.out.println("  metrics: " + metricsA.report());

        System.out.println();
        System.out.println("Порядок B: Retry(Logging(Metrics(Email)))");
        MetricsSender metricsB = new MetricsSender(new EmailSender(2));
        NotificationSender b = new RetryingSender(new LoggingSender(metricsB), 3);
        b.send("anna@uni.ru", "Вы записаны");
        System.out.println("  metrics: " + metricsB.report());

        System.out.println();
        System.out.println("A: снаружи видна одна логическая отправка, ретраи скрыты внутри.");
        System.out.println("B: каждая попытка видна в логе и метриках, видно 2 сбоя SMTP.");
    }
}
