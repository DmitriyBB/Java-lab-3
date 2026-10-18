package task14;

public class Online implements DeliveryFormat {

    @Override
    public String name() {
        return "Online";
    }

    @Override
    public String where() {
        return "Zoom";
    }

    @Override
    public int extraCostPerWeek() {
        return 0;
    }
}
