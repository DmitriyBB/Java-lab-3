interface Printable {
    default String describe() {
        return "Printable";
    }
}

interface Loggable {
    default String describe() {
        return "Loggable";
    }
}

class Report implements Printable, Loggable {
}
