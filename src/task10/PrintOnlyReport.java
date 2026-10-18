package task10;

public class PrintOnlyReport implements Printable, Loggable {

    @Override
    public String describe() {
        return Printable.super.describe();
    }
}
