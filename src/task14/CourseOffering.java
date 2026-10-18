package task14;

public record CourseOffering(String title, int weeks, DeliveryFormat format, PaymentPolicy payment) {

    public String describe() {
        return String.format("%-12s %-10s %-18s %-24s итого %d", title, format.name(), format.where(), payment.name(),
                payment.total(weeks, format.extraCostPerWeek()));
    }
}
