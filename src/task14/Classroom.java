package task14;

public class Classroom implements DeliveryFormat {

    private final String room;

    public Classroom(String room) {
        this.room = room;
    }

    @Override
    public String name() {
        return "Classroom";
    }

    @Override
    public String where() {
        return "ауд. " + room;
    }

    @Override
    public int extraCostPerWeek() {
        return 500;
    }
}
