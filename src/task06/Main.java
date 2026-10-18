package task06;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        BadCourse b1 = new BadCourse("JAVA-101");
        BadCourse b2 = new BadCourse("JAVA-101");
        Object b2AsObject = b2;
        System.out.println("BadCourse без @Override компилируется, но это перегрузка, а не переопределение:");
        System.out.println("  b1.equals(b2)            = " + b1.equals(b2) + "   вызван equals(BadCourse)");
        System.out.println("  b1.equals((Object) b2)   = " + b1.equals(b2AsObject) + "  вызван Object.equals(Object)");
        System.out.println("  List.of(b1).contains(b2) = " + List.of(b1).contains(b2));
        Set<BadCourse> badSet = new HashSet<>(List.of(b1, b2));
        System.out.println("  HashSet size             = " + badSet.size() + " (hashcode() никто не вызывает)");
        System.out.println("  println(b1)              = " + b1 + " (tostring() тоже мимо)");

        GoodCourse g1 = new GoodCourse("JAVA-101");
        GoodCourse g2 = new GoodCourse("JAVA-101");
        System.out.println("GoodCourse с @Override:");
        System.out.println("  List.of(g1).contains(g2) = " + List.of(g1).contains(g2));
        System.out.println("  HashSet size             = " + new HashSet<>(List.of(g1, g2)).size());
        System.out.println("  println(g1)              = " + g1);

        Sender sender = new SmsSender();
        System.out.println("Исправленная иерархия Sender/SmsSender:");
        System.out.println("  " + sender.send("+7900", "привет"));
        System.out.println("  priority = " + sender.priority());
        SmsSender copy = new SmsSender().copy();
        System.out.println("  covariant copy() -> " + copy.getClass().getSimpleName());
    }
}
