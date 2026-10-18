package task11;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Point p = new Point(1, 2);
        ColoredPoint cp = new ColoredPoint(1, 2, "red");
        System.out.println("1) instanceof в иерархии:");
        System.out.println("   p.equals(cp)  = " + p.equals(cp));
        System.out.println("   cp.equals(p)  = " + cp.equals(p) + "  <- симметричность нарушена");
        Set<Point> set = new HashSet<>();
        set.add(p);
        System.out.println("   set{p}.contains(cp) = " + set.contains(cp));

        StrictPoint sp = new StrictPoint(1, 2);
        StrictColoredPoint scp = new StrictColoredPoint(1, 2, "red");
        System.out.println("2) strict class equality (getClass):");
        System.out.println("   sp.equals(scp) = " + sp.equals(scp) + ", scp.equals(sp) = " + scp.equals(sp));
        System.out.println("   new StrictPoint(1,2){}.equals(sp) = " + new StrictPoint(1, 2) {}.equals(sp) + "  <- даже пустой подкласс не равен");

        PointValue pv = new PointValue(1, 2);
        ColoredPointValue cpv = new ColoredPointValue(pv, "red");
        System.out.println("3) композиция, final record:");
        System.out.println("   pv.equals(cpv) = " + pv.equals(cpv) + ", cpv.equals(pv) = " + cpv.equals(pv));
        System.out.println("   cpv.point().equals(pv) = " + cpv.point().equals(pv) + "  <- сравнение координат явно");
        System.out.println("   cpv.equals(new ColoredPointValue(new PointValue(1,2), \"red\")) = "
                + cpv.equals(new ColoredPointValue(new PointValue(1, 2), "red")));
    }
}
