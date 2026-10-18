package task14;

public interface PaymentPolicy {

    String name();

    int total(int weeks, int extraPerWeek);
}
