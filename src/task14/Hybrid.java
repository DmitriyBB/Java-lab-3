package task14;

public class Hybrid implements DeliveryFormat {

    private final Online online = new Online();
    private final Classroom classroom;

    public Hybrid(String room) {
        this.classroom = new Classroom(room);
    }

    @Override
    public String name() {
        return "Hybrid";
    }

    @Override
    public String where() {
        return online.where() + " + " + classroom.where();
    }

    @Override
    public int extraCostPerWeek() {
        return classroom.extraCostPerWeek() / 2;
    }
}
