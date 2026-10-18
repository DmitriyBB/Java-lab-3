package task14;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static void printAll(List<DeliveryFormat> formats, List<PaymentPolicy> payments) {
        for (DeliveryFormat format : formats) {
            for (PaymentPolicy payment : payments) {
                System.out.println("  " + new CourseOffering("Основы Java", 8, format, payment).describe());
            }
        }
    }

    public static void main(String[] args) {
        List<DeliveryFormat> formats = new ArrayList<>(List.of(new Online(), new Classroom("101"), new Hybrid("101")));
        List<PaymentPolicy> payments = new ArrayList<>(List.of(new Free(), new Fixed(10000), new Subscription(1500)));

        System.out.println("3 формата x 3 оплаты = 9 комбинаций из 6 классов:");
        printAll(formats, payments);

        formats.add(new SelfPaced());
        payments.add(new Installments(new Fixed(10000), 3));
        System.out.println("Добавлены SelfPaced и Installments (2 новых класса, старые не менялись): 16 комбинаций");
        printAll(formats, payments);
    }
}
