package task10;

public class Main {

    public static void main(String[] args) {
        Report report = new Report();
        Printable asPrintable = report;
        Loggable asLoggable = report;
        System.out.println("report.describe()      = " + report.describe());
        System.out.println("asPrintable.describe() = " + asPrintable.describe());
        System.out.println("asLoggable.describe()  = " + asLoggable.describe());
        System.out.println("PrintOnlyReport        = " + new PrintOnlyReport().describe());
        System.out.println("Без override класс не компилируется: см. broken/task10.");
    }
}
