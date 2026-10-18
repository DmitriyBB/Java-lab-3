package task10;

public class Report implements Printable, Loggable {

    @Override
    public String describe() {
        return "Report[" + Printable.super.describe() + " + " + Loggable.super.describe() + "]";
    }
}
