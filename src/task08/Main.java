package task08;

import java.util.List;
import model.Course;

public class Main {

    static void legacyImport(LegacyCourseRepository repo, List<Course> incoming) {
        for (Course course : incoming) {
            repo.save(course);
        }
        System.out.println("  imported, total = " + repo.findAll().size());
    }

    static void importCourses(CourseRepository repo, List<Course> incoming) {
        incoming.forEach(repo::save);
        System.out.println("  imported, total = " + repo.findAll().size());
    }

    static void printCatalog(CourseReader reader) {
        for (Course course : reader.findAll()) {
            System.out.println("  " + course.code() + " " + course.title());
        }
    }

    public static void main(String[] args) {
        List<Course> incoming = List.of(new Course("JAVA-101", "Основы Java", 48), new Course("OOP-201", "ООП", 36));

        System.out.println("До: ReadOnly extends CourseRepository");
        legacyImport(new LegacyCourseRepository(), incoming);
        try {
            legacyImport(new LegacyReadOnlyCourseRepository(List.of()), incoming);
        } catch (UnsupportedOperationException e) {
            System.out.println("  клиент сломался на подтипе: " + e);
        }

        System.out.println("После: CourseReader <- CourseRepository");
        InMemoryCourseRepository repo = new InMemoryCourseRepository();
        importCourses(repo, incoming);
        ReadOnlyCourseCatalog catalog = new ReadOnlyCourseCatalog(repo.findAll());
        System.out.println(" printCatalog(repo):");
        printCatalog(repo);
        System.out.println(" printCatalog(catalog):");
        printCatalog(catalog);
        System.out.println(" catalog.findByCode(\"OOP-201\") = " + catalog.findByCode("OOP-201").map(Course::title).orElse("-"));
        System.out.println(" importCourses(catalog, ...) не компилируется: ReadOnlyCourseCatalog не CourseRepository");
    }
}
