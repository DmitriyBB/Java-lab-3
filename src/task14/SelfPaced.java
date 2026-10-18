package task14;

public class SelfPaced implements DeliveryFormat {

    @Override
    public String name() {
        return "SelfPaced";
    }

    @Override
    public String where() {
        return "видеозаписи";
    }

    @Override
    public int extraCostPerWeek() {
        return -100;
    }
}
